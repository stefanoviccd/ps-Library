/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryBook;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class DeleteBookSO extends AbstractSO{
    private RepositoryBook repositoryBook;

    public DeleteBookSO() {
        repositoryBook=new RepositoryBook();
    }
    

    @Override
    protected void precondition(Object param) throws Exception  {
         if (param == null || !(param instanceof Book)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            Book book = (Book) param;
             try {
                 checkStructuralConstraints(book);
             } catch (Exception ex) {
                 throw new Exception(ex.getMessage());
             }
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Book b=(Book) param;
       repositoryBook.delete(b);
       return null;
    }

    private void checkStructuralConstraints(Book book) throws Exception {
        checkIfRentsExist(book);
    }
    private void checkIfRentsExist(Book book) throws Exception {
        try {
            repositoryBook.checkIfRentsExist(book);
        } catch (Exception ex) {
            throw new Exception("Knjiga je iznajmljena. Ne moze se obrisati.");
        }

    
        }
    
}
