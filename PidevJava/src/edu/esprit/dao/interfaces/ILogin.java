/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Login;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Hrouz
 */
public interface ILogin {

    void insertLogin(Login depot);

    void updateLogin(Login l);

    void deleteLogin(int id);

    Login findLoginById(int id);

    List<Login> DisplayAllLogins();
}
