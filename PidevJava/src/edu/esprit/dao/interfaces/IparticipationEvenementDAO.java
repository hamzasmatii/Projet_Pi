/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Participation_evenement;
import edu.esprit.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author Khaled
 */
public interface IparticipationEvenementDAO {
    
    
    public void insererParticipation(Participation_evenement p);
    public void updateParticipation(Participation_evenement p);
    public void deleteParticipation(Participation_evenement p);
    List<Evenement> fetchParticipationByUser(int id);
    List<Participation_evenement> fetchParticipationByEvent(int id);
    boolean verifEvenementUser(Evenement e , Utilisateur u );
    boolean fetchUpcomingEvents(Utilisateur u);
    
}
