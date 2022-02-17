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
public class Evenement {
    private int id_evenement;
    private String titre_evenement;
    private String adresse_evenement;
    private String description_evenement;
    private Date date_creation_evenement;
    private Date  date_evenement;
    private int type_evenements;
    private int id_utilisateur;
    
   public Evenement(){}

    public Evenement(int id_evenement, String titre_evenement, String adresse_evenement, String description_evenement, Date date_creation_evenement, Date date_evenement, int type_evenements, int id_utilisateur) {
        this.id_evenement = id_evenement;
        this.titre_evenement = titre_evenement;
        this.adresse_evenement = adresse_evenement;
        this.description_evenement = description_evenement;
        this.date_creation_evenement = date_creation_evenement;
        this.date_evenement = date_evenement;
        this.type_evenements = type_evenements;
        this.id_utilisateur = id_utilisateur;
    }

    public Evenement(String titre_evenement, String adresse_evenement, String description_evenement, Date date_evenement, int type_evenements, int id_utilisateur) {
        this.titre_evenement = titre_evenement;
        this.adresse_evenement = adresse_evenement;
        this.description_evenement = description_evenement;
        this.date_evenement = date_evenement;
        this.type_evenements = type_evenements;
        this.id_utilisateur = id_utilisateur;
    }

    

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getTitre_evenement() {
        return titre_evenement;
    }

    public void setTitre_evenement(String titre_evenement) {
        this.titre_evenement = titre_evenement;
    }

    public String getAdresse_evenement() {
        return adresse_evenement;
    }

    public void setAdresse_evenement(String adresse_evenement) {
        this.adresse_evenement = adresse_evenement;
    }

    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public Date getDate_creation_evenement() {
        return date_creation_evenement;
    }

    public void setDate_creation_evenement(Date date_creation_evenement) {
        this.date_creation_evenement = date_creation_evenement;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public int getType_evenements() {
        return type_evenements;
    }

    public void setType_evenements(int type_evenements) {
        this.type_evenements = type_evenements;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
    
    
 
    
    
   
    
    
    
    
}
