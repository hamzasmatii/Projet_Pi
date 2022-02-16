/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Hrouz
 */
public interface IUtilisateur {
    void insertUtilisateur(Utilisateur depot) ;

    void updateUtilisateur(Utilisateur d);

    void deleteUtilisateur(int id);

    Utilisateur findUtilisateurtById(int id);

    List<Utilisateur> DisplayAllUtilisateurs();
}
