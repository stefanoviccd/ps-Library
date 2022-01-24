/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.user;

import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUser;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class AddUserSO extends AbstractSO {

    RepositoryUser repositoryUser;

    public AddUserSO() {
        repositoryUser = new RepositoryUser();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            User user = (User) param;
            //   checkValueConstraints(user);
            checkStructuralConstraints(user);
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryUser.add((User) param);
        return null;
    }

    private void checkStructuralConstraints(User user) throws Exception {
        boolean exists = checkIfExists(user,false);
        if (exists) {
            throw new Exception("Korisnik postoji.");
        }

    }

    private boolean checkIfExists(User user, boolean includeUserCard) throws Exception {
        try {
            return repositoryUser.checkIfExists(user, includeUserCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja korisnika u bazi.", e);
        }
    }

}
