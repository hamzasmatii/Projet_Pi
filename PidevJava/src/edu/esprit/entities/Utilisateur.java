/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author macbook
 */
public class Utilisateur {
    
    private int id_utilisateur, type_utilisateur, solde_utilisateur;
    private String nom_utilisateur, photo_utilisateur,email_utilisateur;
    private Date date_naissance_utilisateur;
    
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getType_utilisateur() {
        return type_utilisateur;
    }

    public void setType_utilisateur(int type_utilisateur) {
        this.type_utilisateur = type_utilisateur;
    }

    public int getSolde_utilisateur() {
        return solde_utilisateur;
    }

    public void setSolde_utilisateur(int solde_utilisateur) {
        this.solde_utilisateur = solde_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getPhoto_utilisateur() {
        return photo_utilisateur;
    }

    public void setPhoto_utilisateur(String photo_utilisateur) {
        this.photo_utilisateur = photo_utilisateur;
    }

    public Date getDate_naissance_utilisateur() {
        return date_naissance_utilisateur;
    }

    public void setDate_naissance_utilisateur(Date date_naissance_utilisateur) {
        this.date_naissance_utilisateur = date_naissance_utilisateur;
    }

    public Utilisateur(int id_utilisateur, String nom_utilisateur, Date date_naissance_utilisateur , String photo_utilisateur,int type_utilisateur, int solde_utilisateur , String email_utilisateur) {
        this.id_utilisateur = id_utilisateur;
        this.type_utilisateur = type_utilisateur;
        this.solde_utilisateur = solde_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.photo_utilisateur = photo_utilisateur;
        this.date_naissance_utilisateur = date_naissance_utilisateur;
        this.email_utilisateur=email_utilisateur;
    }
    
    public Utilisateur( String nom_utilisateur, Date date_naissance_utilisateur , String photo_utilisateur,int type_utilisateur, int solde_utilisateur , String email_utilisateur) {
        this.type_utilisateur = type_utilisateur;
        this.solde_utilisateur = solde_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.photo_utilisateur = photo_utilisateur;
        this.date_naissance_utilisateur = date_naissance_utilisateur;
        this.email_utilisateur=email_utilisateur;
        
    }

    public String getEmail_utilisateur() {
        return email_utilisateur;
    }

    public void setEmail_utilisateur(String email_utilisateur) {
        this.email_utilisateur = email_utilisateur;
    }

    public Utilisateur() {
        this.type_utilisateur = 0;
        this.solde_utilisateur = 0;
        this.nom_utilisateur = "";
        this.photo_utilisateur = "";
        this.date_naissance_utilisateur = null;
        this.email_utilisateur=null;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", type_utilisateur=" + type_utilisateur + ", solde_utilisateur=" + solde_utilisateur + ", nom_utilisateur=" + nom_utilisateur + ", photo_utilisateur=" + photo_utilisateur + ", email_utilisateur=" + email_utilisateur + ", date_naissance_utilisateur=" + date_naissance_utilisateur + '}';
    }


    

    
    

}
