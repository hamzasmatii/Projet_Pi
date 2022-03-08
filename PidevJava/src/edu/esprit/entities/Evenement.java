/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;
import java.sql.Timestamp;


/**
 *
 * @author Khaled
 */
public class Evenement {
    private int id_evenement;
    private String image;
    private String titre_evenement;
    private String adresse_evenement;
    private String description_evenement;
    private Date date_creation_evenement;
    private Timestamp  date_evenement;
    private Type_evenement type_evenements;
    private Utilisateur utilisateur;
    
   public Evenement(){}

    public Evenement(int id_evenement, String titre_evenement, String adresse_evenement, String description_evenement, Date date_creation_evenement, Timestamp date_evenement, Type_evenement type_evenements, Utilisateur id_utilisateur,String image) {
        this.id_evenement = id_evenement;
        this.titre_evenement = titre_evenement;
        this.adresse_evenement = adresse_evenement;
        this.description_evenement = description_evenement;
        this.date_creation_evenement = date_creation_evenement;
        this.date_evenement = date_evenement;
        this.type_evenements = type_evenements;
        this.utilisateur = id_utilisateur;
        this.image=image;
    }

    public Evenement(int id_evenement, String titre_evenement, String adresse_evenement, String description_evenement, Timestamp date_evenement, Type_evenement type_evenements, Utilisateur id_utilisateur) {
        this.id_evenement = id_evenement;
        this.titre_evenement = titre_evenement;
        this.adresse_evenement = adresse_evenement;
        this.description_evenement = description_evenement;
        this.date_evenement = date_evenement;
        this.type_evenements = type_evenements;
        this.utilisateur = id_utilisateur;
    }

    public Evenement(String titre_evenement, String adresse_evenement, String description_evenement, Timestamp date_evenement, Type_evenement type_evenements, Utilisateur id_utilisateur,String image) {
        this.titre_evenement = titre_evenement;
        this.adresse_evenement = adresse_evenement;
        this.description_evenement = description_evenement;
        this.date_evenement = date_evenement;
        this.type_evenements = type_evenements;
        this.utilisateur = id_utilisateur;
        this.image = image;
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

    public Timestamp getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Timestamp date_evenement) {
        this.date_evenement = date_evenement;
    }

    public Type_evenement getType_evenements() {
        return type_evenements;
    }

    public void setType_evenements(Type_evenement type_evenements) {
        this.type_evenements = type_evenements;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur id_utilisateur) {
        this.utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", titre_evenement=" + titre_evenement + ", adresse_evenement=" + adresse_evenement + ", description_evenement=" + description_evenement + ", date_creation_evenement=" + date_creation_evenement + ", date_evenement=" + date_evenement + ", type_evenements=" + type_evenements + ", id_utilisateur=" + utilisateur + '}';
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    
 
    
    
   
    
    
    
    
}
