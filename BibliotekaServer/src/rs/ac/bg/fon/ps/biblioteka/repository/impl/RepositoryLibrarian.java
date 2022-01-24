/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryLibrarian implements DbRepository<Librarian, Long> {

    public List<Librarian> getAll() throws Exception {
        List<Librarian> librarians = new ArrayList<>();
        String query = "SELECT * FROM bibliotekar";
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Librarian b = new Librarian(rs.getLong(1), rs.getString(2), rs.getString(3)
            );
            librarians.add(b);
        }
        return librarians;

    }

    @Override
    public void add(Librarian t) throws Exception {
        //TODO: Implement later
    }

    @Override
    public void edit(Librarian oldOne, Librarian newOne) throws Exception {
        //TODO: Implement later
    }

    @Override
    public void delete(Librarian t) throws Exception {
        //TODO: Implement later
    }

    @Override
    public List<Librarian> getByQuery(String query) throws Exception {
        List<Librarian> librarians = new ArrayList<>();

        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Librarian b = new Librarian(rs.getLong(1), rs.getString(2), rs.getString(3)
            );
            librarians.add(b);
        }
        return librarians;
    }

}
