/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;
import edu.esprit.entities.Evenement;
import java.util.List;

/**
 *
 * @author Khaled
 */
public interface IevenementDAO {
    
    void insertEvenement(Evenement e );
    void deleteEvenement(int id);
    void updateEvenement(Evenement e);
    List<Evenement> fetchEvenement();
    Evenement fetchEvenementById(int id);
    
    
}
