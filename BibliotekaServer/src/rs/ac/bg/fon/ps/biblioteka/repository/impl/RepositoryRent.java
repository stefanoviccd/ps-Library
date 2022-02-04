/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;
import rs.ac.bg.fon.ps.biblioteka.controller.Controller;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.repository.Repository;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryRent implements Repository {

    private Statement statement;
    private PreparedStatement ps;
    DatabaseBroker dbBroker;

    public RepositoryRent() {
        dbBroker=new DatabaseBroker();
    }
    

    @Override
    public List<Rent> getAll() throws Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE datumVracanja IS NULL";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));

            r.setRentalDate(rs.getDate(3).toLocalDate());
            Long bookid = rs.getLong(2);
            String bookQuery = "SELECT * FROM knjiga WHERE id=" + bookid;
            Book b = Controller.getInstance().getBooksByQuery(bookQuery).get(0);
            r.setBook(b);
            rents.add(r);

        }
        return rents;
    }

    @Override
    public void add(Object t) throws Exception {
       //TODO: implement later
    }

    @Override
    public void edit(Object stari, Object novi) throws Exception {
        //TODO: implement later
    }

    @Override
    public void delete(Object t) throws Exception {
        //TODO: implement later
    }

    @Override
    public List getByQuery(String query) throws Exception {
         statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));

            r.setRentalDate(rs.getDate(3).toLocalDate());
            Long bookid = rs.getLong(2);
            String bookQuery = "SELECT * FROM knjiga WHERE id=" + bookid;
            Book b = Controller.getInstance().getBooksByQuery(bookQuery).get(0);
            r.setBook(b);
            rents.add(r);

        }
        return rents;
        

    }

    public List<Rent> getUserRents(User u) throws Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE clanId=" + u.getUserId() + " AND datumVracanja IS NULL";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));
            r.setUser(u);
            r.setRentalDate(rs.getDate(3).toLocalDate());
            Long bookid = rs.getLong(2);
            String bookQuery = "SELECT * FROM knjiga WHERE id=" + bookid;
            Book b = Controller.getInstance().getBooksByQuery(bookQuery).get(0);
            r.setBook(b);
            rents.add(r);

        }

        return rents;

    }

    public void rentBook(User u, Book b) throws Exception {
       /* String query = "INSERT INTO iznajmljivanje (clanId, knjigaId,datumIznajmljivanja) VALUES (?,?,?)";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setLong(1, u.getUserId());
        ps.setLong(2, b.getBookid());
        ps.setDate(3, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();*/
       Rent r=new Rent();
       r.setBook(b);
       r.setUser(u);
       r.setRentalDate(LocalDate.now());
       dbBroker.add(r);


    }


    public void restoreBook(Rent rental) throws Exception {
        String query = "UPDATE iznajmljivanje SET datumVracanja=? WHERE id=" + rental.getId();
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setDate(1, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();

    }

    public void deleteBookRents(Book t) throws SQLException {
        String query = "DELETE FROM iznajmljivanje WHERE knjigaId=" + t.getBookid();
        try {
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryRent.class.getName()).log(Level.SEVERE, null, ex);
        }
        statement.execute(query);

    }

    public List<Rent> getAllUserRents(User u) throws SQLException, IOException, Exception {
        String query = "SELECT * FROM iznajmljivanje WHERE clanId=" + u.getUserId();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Rent r = new Rent();
            r.setId(rs.getLong("id"));
            r.setUser(u);
            r.setRentalDate(rs.getDate(3).toLocalDate());
            try {
                 if(rs.getDate(4) !=null) r.setReturnDate(rs.getDate(4).toLocalDate());
                 Long bookid = rs.getLong(2);
            String bookQuery = "SELECT * FROM knjiga WHERE id=" + bookid;
            Book b = Controller.getInstance().getBooksByQuery(bookQuery).get(0);
            r.setBook(b);
            rents.add(r);

            } catch (Exception e) {
                e.printStackTrace();
            }
           
            
            
        }

        return rents;
           }
    

}
