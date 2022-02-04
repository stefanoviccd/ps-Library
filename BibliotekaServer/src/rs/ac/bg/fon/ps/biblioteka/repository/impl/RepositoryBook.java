/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import java.io.IOException;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.so.bookCategory.GetBookCategoryIdSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryBook implements DbRepository<Book, Long> {

    RepositoryAuthor repositoryAuthor = new RepositoryAuthor();
    RepositoryBookCategory repositoryBookCategory = new RepositoryBookCategory();
    Statement statement;
    PreparedStatement ps;
    private DatabaseBroker databaseBroker;

    public RepositoryBook() {
        repositoryAuthor = new RepositoryAuthor();
        repositoryBookCategory = new RepositoryBookCategory();
        databaseBroker=new DatabaseBroker();

    }

    @Override
    public List<Book> getAll() throws Exception {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM knjiga ORDER BY naziv ASC";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Book k = new Book();
            k.setBookid(rs.getLong("id"));
            k.setBookName(rs.getString("naziv"));
            k.setIssueDate(rs.getInt("godinaIzdanja"));
            k.setNumberInStock(rs.getInt("kolicina"));
            Long pisacId = rs.getLong("pisacId");
            Long kategorijaId = rs.getLong("kategorijaId");
            query = "SELECT * FROM autor WHERE id=" + pisacId;
            List<Author> authors = repositoryAuthor.getByQuery(query);
            if (authors.size() == 0) {
                throw new Exception("Nastala je greska kod prikljucivanja autora knjizi.");
            }
            Author author = authors.get(0);
            BookCategory category = BookCategory.valueOf(repositoryBookCategory.getCategoryName(kategorijaId));
            k.setAuthor(author);
            k.setBookCategory(category);
            books.add(k);

        }
        statement.close();
        return books;

    }

    @Override
    public void add(Book t) throws Exception {
       
        boolean status = updateIfExists(t);
        if (status == false) {
            databaseBroker.add(t);
            
        }

    }

    @Override
    public void delete(Book t) throws Exception {
                databaseBroker.delete(t);
       // deleteRents(t);
        /*String query = "DELETE FROM knjiga WHERE id=" + t.getBookid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);

        statement.close();
       try {
            removeAuthor(t.getAuthor());
        } catch (Exception e) {
            System.out.println("Autor nije obrisan.");
        }
*/

    }

    @Override
    public void edit(Book oldBook, Book newBook) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> getByQuery(String query) throws Exception {
        List<Book> books = new ArrayList<>();

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Book k = new Book();
            k.setBookid(rs.getLong("id"));
            k.setBookName(rs.getString("naziv"));
            k.setIssueDate(rs.getInt("godinaIzdanja"));
            k.setNumberInStock(rs.getInt("kolicina"));
            Long authorId = rs.getLong("pisacId");
            Long categoryId = rs.getLong("kategorijaId");
            String queryAutor = "SELECT * FROM autor WHERE id=" + authorId;
            List<Author> authors = repositoryAuthor.getByQuery(queryAutor);
            if (authors.size() == 0) {
                throw new Exception();
            }
            Author autor = authors.get(0);

            BookCategory category = BookCategory.valueOf(repositoryBookCategory.getCategoryName(categoryId));
            k.setAuthor(autor);
            k.setBookCategory(category);

            books.add(k);

        }
        return books;

    }

    /*public void checkIfRentsExist(Book book) throws SQLException, Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE knjigaId=" + book.getBookid() + " AND datumVracanja IS NULL";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        if (rs.next()) {
            throw new Exception("Primerci knjige su zaduzeni. Nije moguce dovrsiti operaciju brisanja.");
        }

    }*/

    /*private void removeAuthor(Author author) throws SQLException, Exception {
        boolean exist = checkIfBooksExist(author);
        if (exist) {
            throw new Exception("Knjige ovog autora postoje na stanju. Nije moguce dovrsiti operaciju brisanja.");
        } else {
            String query = "DELETE FROM autor WHERE id=" + author.getAuthorId();
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
        }

    }*/

    public boolean checkIfBooksExist(Author author) throws SQLException {
        boolean exist = false;
        String query = "SELECT * from knjiga WHERE pisacId=" + author.getAuthorId();
        try {
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            exist = true;
        }
        rs.close();
        statement.close();
        return exist;

    }

   /* private void deleteRents(Book t) throws SQLException {
        RepositoryRent r = new RepositoryRent();
        r.deleteBookRents(t);
    }*/

    private boolean updateIfExists(Book t) throws Exception {
        Long categoryId = (Long) new GetBookCategoryIdSO().execute(t.getBookCategory().toString());
        String query = "SELECT * FROM knjiga WHERE naziv='" + t.getBookName() + "' AND godinaIzdanja=" + t.getIssueDate() + " AND kategorijaId=" + categoryId + " AND pisacId=" + t.getAuthor().getAuthorId();
        List<Book> dbBooks = getByQuery(query);
        if (dbBooks.size() > 0) {
            Book dbBook = dbBooks.get(0);
            int newAmount=dbBook.getNumberInStock()+t.getNumberInStock();
            query = "UPDATE knjiga SET kolicina= " +newAmount + " WHERE id=" + dbBook.getBookid();
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            statement.execute(query);
            statement.close();
            return true;
        }
        return false;
    }

    public void updateBookCount(Book b, int i) throws SQLException, IOException {
        int num = b.getNumberInStock() + i;
        String updateBookCount = "UPDATE knjiga SET kolicina= " + num + " WHERE id=" + b.getBookid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(updateBookCount);
            }
}
