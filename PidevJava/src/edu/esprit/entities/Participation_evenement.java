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
    private int id_utilisateur;
    private int id_evenement;
    private Date date_participation;

    public Participation_evenement(){}
    public Participation_evenement(int id_utilisateur, int id_evenement, Date date_participation) {
        this.id_utilisateur = id_utilisateur;
        this.id_evenement = id_evenement;
        this.date_participation = date_participation;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public Date getDate_participation() {
        return date_participation;
    }

    public void setDate_participation(Date date_participation) {
        this.date_participation = date_participation;
    }
    
}
