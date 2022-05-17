/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Contrat;
import java.util.List;

/**
 *
 * @author Haythem
 */
public interface IContrat {
      void insertContrat(Contrat contrat);

    void updateContrat(Contrat c);

    void deleteContrat(int id);
//
//    
    List<Contrat> DisplayAllContrat();
}
