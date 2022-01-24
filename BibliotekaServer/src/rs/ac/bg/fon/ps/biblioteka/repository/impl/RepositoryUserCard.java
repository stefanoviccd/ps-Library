/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.exception.NoUserCartFoundException;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryUserCard implements DbRepository<UserCard, Long> {

    private Statement statement;
    private PreparedStatement pstatement;

    @Override
    public List<UserCard> getAll() throws Exception {
        //TODO: Implement later
        return null;
    }

    @Override
    public void add(UserCard t) throws Exception {
        String query = "INSERT INTO clanskaKarta (brojClanskeKarte, datumIzdavanja, datumIsteka) VALUES (?,?,?)";
        pstatement = DbConnectionFactory.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstatement.setString(1, t.getCardNumber());
        pstatement.setDate(2, Date.valueOf(t.getIssueDate()));
        pstatement.setDate(3, Date.valueOf(t.getExpiryDate()));
        pstatement.executeUpdate();
        ResultSet ckKey = statement.getGeneratedKeys();
        Long cardId = null;
        if (ckKey.next()) {
            cardId = ckKey.getLong(1);
        }
    }

    @Override
    public void delete(UserCard t) throws Exception {
        String query = "DELETE FROM clanskakarta WHERE id=" + t.getId();

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @Override
    public void edit(UserCard oldOne, UserCard newOne) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserCard> getByQuery(String query) throws Exception {
        List<UserCard> cards = new ArrayList<>();
        UserCard card = new UserCard();

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            card.setId(rs.getLong(1));
            card.setCardNumber(rs.getString(2));
            card.setIssueDate(rs.getDate(3).toLocalDate());
            card.setExpiryDate(rs.getDate(4).toLocalDate());
            cards.add(card);

        }
        statement.close();
        if (cards.size() > 0) {
            return cards;
        }
        throw new NoUserCartFoundException("Clanska karta ne postoji!");
    }

}
