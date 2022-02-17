/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;
import edu.esprit.entities.Livre;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author aziz karoui
 */
public interface ILivre {
    
    void insertLivre(Livre depot) ;

    void updateLivre(Livre d);

    void deleteLivre(int id);

    Livre findLivreById(int id);

    List<Livre> DisplayAllLivre();
}
