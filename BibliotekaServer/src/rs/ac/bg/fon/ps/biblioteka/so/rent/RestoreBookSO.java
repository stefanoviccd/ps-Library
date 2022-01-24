/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.rent;

import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryRent;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class RestoreBookSO extends AbstractSO {

    RepositoryRent repositoryRent;

    public RestoreBookSO() {
        repositoryRent = new RepositoryRent();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Rent)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");

        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Rent rent = (Rent) param;
        try {
            repositoryRent.restoreBook(rent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return null;
    }

}
