/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.CategorieLivre;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author aziz karoui
 */
public interface ICategorieLivre {
    
     void insertCategorieLivre(CategorieLivre depot) ;

    void updateCategorieLivre(CategorieLivre d);

    void deleteCategorieLivre(int id);

    CategorieLivre findCategorieLivreById(int id);

    List<CategorieLivre> DisplayAllCategorieLivre();
    
}
