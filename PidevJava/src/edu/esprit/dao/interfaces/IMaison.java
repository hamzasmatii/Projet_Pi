/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Maison;
import java.util.List;

/**
 *
 * @author Haythem
 */
public interface IMaison {
     void insertMaison(Maison maison);

    void updateMaison(Maison m);

    void deleteMaison(int id);
//
//    
    List<Maison> DisplayAllRecompense();
}
