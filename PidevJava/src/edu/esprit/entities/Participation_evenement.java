/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Khaled
 */
public class Participation_evenement {
    private Utilisateur utilisateur;
    private Evenement evenement;
    private Date date_participation;

    public Participation_evenement(){}
    public Participation_evenement(Utilisateur id_utilisateur, Evenement id_evenement, Date date_participation) {
        this.utilisateur = id_utilisateur;
        this.evenement = id_evenement;
        this.date_participation = date_participation;
    }

    public Participation_evenement(Utilisateur id_utilisateur, Evenement id_evenement) {
        this.utilisateur = id_utilisateur;
        this.evenement = id_evenement;
    }
    

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur id_utilisateur) {
        this.utilisateur = id_utilisateur;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setId_evenement(Evenement id_evenement) {
        this.evenement = id_evenement;
    }

    public Date getDate_participation() {
        return date_participation;
    }

    public void setDate_participation(Date date_participation) {
        this.date_participation = date_participation;
    }
    
}
