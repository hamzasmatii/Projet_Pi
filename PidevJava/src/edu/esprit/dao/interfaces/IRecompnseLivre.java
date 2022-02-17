/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.RecomponseLivre;
import java.util.List;

/**
 *
 * @author Hamza
 */
public interface IRecompnseLivre {
    
    void insertRecompenseLivre(RecomponseLivre recomponselivre);

    void updateRecompenseLivre(RecomponseLivre r);

    void deleteRecompenseLivre(int id);
//
//    
    List<RecomponseLivre> DisplayAllRecompenseLivre();
    
    
}
