/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import java.io.IOException;
import rs.ac.bg.fon.ps.biblioteka.controller.Controller;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryUser implements DbRepository<User, Long> {

    private Statement statement;
    private PreparedStatement ps;

    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        String upit = "SELECT * FROM clan ORDER BY ime ASC";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setPhoneNumber(rs.getString(4));
            user.setAddress(rs.getString(5));
            List<UserCategory> categories = Controller.getInstance().getUserCategories();

            for (UserCategory uc : categories) {
                if (uc.getUserCategoryId().equals(rs.getLong(6))) {
                    user.setUserCategory(uc);
                    break;
                }

            }
            UserCard userCard = Controller.getInstance().getUserCardById(rs.getLong(7));
            user.setUsercard(userCard);
            users.add(user);

        }
        statement.close();
        rs.close();
        return users;
    }

    @Override
    public void add(User t) throws Exception {
        String query = "INSERT INTO clanskaKarta (brojClanskeKarte, datumIzdavanja, datumIsteka) VALUES (?,?,?)";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, t.getUsercard().getCardNumber());
        ps.setDate(2, Date.valueOf(t.getUsercard().getIssueDate()));
        ps.setDate(3, Date.valueOf(t.getUsercard().getExpiryDate()));
        ps.executeUpdate();
        ResultSet ckKey = ps.getGeneratedKeys();
        Long userCardId = null;
        if (ckKey.next()) {
            userCardId = ckKey.getLong(1);
        }

        query = "SELECT id FROM kategorijaclanova WHERE naziv='" + t.getUserCategory().getName() + "'";

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        Long categoryId = null;
        while (rs.next()) {
            categoryId = rs.getLong(1);
        }

        query = "INSERT INTO clan (ime, prezime, brojTelefona, adresa,kategorijaId, clanskaKartaId) VALUES (?,?,?,?,?,?)";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setString(1, t.getName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getPhoneNumber());
        ps.setString(4, t.getAddress());
        ps.setLong(5, categoryId);
        ps.setLong(6, userCardId);
        ps.executeUpdate();
        ps.close();
        rs.close();
        ckKey.close();
        statement.close();

    }

    @Override
    public void edit(User oldU, User newU) throws Exception {
        String category = newU.getUserCategory().toString();
        String query = "SELECT id FROM kategorijaclanova WHERE naziv='" + newU.getUserCategory().getName() + "'";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        Long categoryId = null;
        while (rs.next()) {
            categoryId = rs.getLong(1);
        }

        String queryUser = "UPDATE clan SET ime=?, prezime=?, brojTelefona=?, adresa=?, kategorijaid=? WHERE id=?";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(queryUser);
        ps.setString(1, newU.getName());
        ps.setString(2, newU.getLastName());
        ps.setString(3, newU.getPhoneNumber());
        ps.setString(4, newU.getAddress());
        ps.setLong(5, categoryId);
        ps.setLong(6, oldU.getUserId());
        ps.executeUpdate();
        ps.close();
        if (!oldU.getUsercard().getCardNumber().equals(newU.getUsercard().getCardNumber())) {
            updateCardNumber(oldU.getUsercard(), newU.getUsercard());
        }

    }

    @Override
    public void delete(User t) throws Exception {
        String upit = "DELETE FROM clan WHERE id=" + t.getUserId();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(upit);
        statement.close();

    }

    public List<User> getusersByUserCard(String cardNumber) throws Exception {
        List<User> users = new ArrayList<>();
        String query = "SELECT c.id as id, c.ime as ime, c.prezime as prezime, c.brojTelefona as bt, c.adresa as adresa, c.kategorijaId as kategorija, c.clanskaKartaId as ck FROM clan c INNER JOIN clanskakarta ck ON (c.clanskaKartaId=ck.id) WHERE ck.brojClanskeKarte='" + cardNumber + "'";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setPhoneNumber(rs.getString(4));
            user.setAddress(rs.getString(5));
            List<UserCategory> categories = Controller.getInstance().getUserCategories();
            for (UserCategory kc : categories) {
                if (kc.getUserCategoryId().equals(rs.getLong(6))) {
                    user.setUserCategory(kc);
                    break;
                }

            }
            UserCard card = Controller.getInstance().getUserCardById(rs.getLong(7));
            user.setUsercard(card);
            users.add(user);

        }
        statement.close();
        rs.close();
        return users;
    }

    @Override
    public List<User> getByQuery(String query) throws Exception {
        List<User> users = new ArrayList<>();
        User c;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            c = new User();
            c.setUserId(rs.getLong(1));
            c.setName(rs.getString(2));
            c.setLastName(rs.getString(3));
            c.setPhoneNumber(rs.getString(4));
            c.setAddress(rs.getString(5));
            List<UserCategory> categories = Controller.getInstance().getUserCategories();

            for (UserCategory kc : categories) {
                if (kc.getUserCategoryId().equals(rs.getLong(6))) {
                    c.setUserCategory(kc);
                    break;
                }

            }
            UserCard card = Controller.getInstance().getUserCardById(rs.getLong(7));
            c.setUsercard(card);
            users.add(c);

        }
        statement.close();
        rs.close();

        return users;
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
        String query = "INSERT INTO iznajmljivanje (clanId, knjigaId,datumIznajmljivanja) VALUES (?,?,?)";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setLong(1, u.getUserId());
        ps.setLong(2, b.getBookid());
        ps.setDate(3, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();
        updateBookCount(b, -1);

    }

    private void updateBookCount(Book b, int value) throws Exception {
        int num = b.getNumberInStock() + value;
        String updateBookCount = "UPDATE knjiga SET kolicina= " + num + " WHERE id=" + b.getBookid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(updateBookCount);
    }

    public void restoreBook(Rent rental) throws Exception {
        String query = "UPDATE iznajmljivanje SET datumVracanja=? WHERE id=" + rental.getId();
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setDate(1, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();
        updateBookCount(rental.getBook(), +1);
        //commit();
    }

    public List<Rent> getRents() throws Exception {
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

    public boolean checkIfExists(User user) throws Exception {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getLastName().equals(user.getLastName()) && u.getAddress().equals(user.getAddress()) && u.getPhoneNumber().equals(user.getPhoneNumber()) && u.getUserCategory().equals(user.getUserCategory()) && u.getUsercard().getCardNumber().equals(user.getUsercard().getCardNumber())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfRentsExist(User user) throws Exception {
        List<Rent> rents = getUserRents(user);
        if (rents.size() > 0) {
            return true;
        }
        return false;

    }

    private void updateCardNumber(UserCard oldCard, UserCard newCard) throws SQLException {
        String query = "UPDATE clanskakarta SET brojClanskeKarte=? AND datumIzdavanja=? and datumIsteka=? WHERE brojClanskeKarte=?";
        try {
            ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        } catch (IOException ex) {
            Logger.getLogger(RepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.setString(1, newCard.getCardNumber());
        ps.setDate(2, Date.valueOf(newCard.getIssueDate()));
        ps.setDate(3, Date.valueOf(newCard.getExpiryDate()));
        ps.setString(4, oldCard.getCardNumber());
        ps.executeUpdate();
        ps.close();

    }

    public boolean checkIfExists(User user, boolean includeUserCard) throws Exception {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getLastName().equals(user.getLastName()) && u.getAddress().equals(user.getAddress()) && u.getPhoneNumber().equals(user.getPhoneNumber()) && u.getUserCategory().equals(user.getUserCategory())) {
                return true;
            }
        }
        return false;

    }

}
