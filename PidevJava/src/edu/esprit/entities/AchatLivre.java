/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author aziz karoui
 */
public class AchatLivre {
    private int id_utilisateur, id_livre;
    private Date date_achat;

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }

    public AchatLivre(int id_utilisateur, int id_livre, Date date_achat) {
        this.id_utilisateur = id_utilisateur;
        this.id_livre = id_livre;
        this.date_achat = date_achat;
    }

    public AchatLivre(Date date_achat) {
        this.date_achat = date_achat;
    }

    public AchatLivre() {
        this.id_utilisateur = 1;
        this.id_livre = 1;
        this.date_achat = null;
        
    }
    
    
}
