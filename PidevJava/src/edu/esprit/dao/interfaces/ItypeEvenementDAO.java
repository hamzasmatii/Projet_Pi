/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Type_evenement;
import java.util.List;

/**
 *
 * @author Khaled
 */
public interface ItypeEvenementDAO {
    
    public void insererTypeEvenement(Type_evenement t);
    public void updateTypeEvenment(Type_evenement t);
    public void deleteTypeEvenement(Type_evenement t);
    public List<Type_evenement> fetchTypeEvenment();
    
}
