/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.AchatLivre;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author aziz karoui
 */
public interface IAchatLivre {
  void insertAchatLivre(AchatLivre depot) ;

    void updateAchatLivre(AchatLivre d);

    void deleteAchatLivre(int id);

    AchatLivre findAchatLivreById(int id);

    List<AchatLivre> DisplayAllAchatLivre();   
}
