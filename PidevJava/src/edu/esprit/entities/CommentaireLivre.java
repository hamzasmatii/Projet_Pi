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
public class CommentaireLivre {

    private int id_commentaire, id_utilisateur, id_livre;
    private String contenu_commentaire;
    private Date date_commentaire;
    private Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public CommentaireLivre(int id_commentaire, int id_utilisateur, int id_livre, String contenu_commentaire, Date date_commentaire) {
        this.id_commentaire = id_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id_livre = id_livre;
        this.contenu_commentaire = contenu_commentaire;
        this.date_commentaire = date_commentaire;
    }

    @Override
    public String toString() {
        return "CommentaireLivre{" + "id_commentaire=" + id_commentaire + ", id_utilisateur=" + id_utilisateur + ", id_livre=" + id_livre + ", contenu_commentaire=" + contenu_commentaire + ", date_commentaire=" + date_commentaire + '}';
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

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

    public String getContenu_commentaire() {
        return contenu_commentaire;
    }

    public void setContenu_commentaire(String contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    public CommentaireLivre(int id_commentaire, int id_livre, String contenu_commentaire, Date date_commentaire, Utilisateur utilisateur) {
        this.id_commentaire = id_commentaire;
        this.id_livre = id_livre;
        this.contenu_commentaire = contenu_commentaire;
        this.date_commentaire = date_commentaire;
        this.utilisateur = utilisateur;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public CommentaireLivre(int id_utilisateur,String contenu_commentaire, int id_livre, Date date_commentaire) {
        this.id_utilisateur = id_utilisateur;
        this.id_livre = id_livre;
        this.contenu_commentaire = contenu_commentaire;
        this.date_commentaire = date_commentaire;
    }

    public CommentaireLivre() {
        this.id_utilisateur=1;
        this.contenu_commentaire="";
        this.id_livre=1;
        this.date_commentaire=null;
    }

    public CommentaireLivre(int id_commentaire, int id_utilisateur, int id_livre, String contenu_commentaire, Date date_commentaire, Utilisateur utilisateur) {
        this.id_commentaire = id_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id_livre = id_livre;
        this.contenu_commentaire = contenu_commentaire;
        this.date_commentaire = date_commentaire;
        this.utilisateur = utilisateur;
    }
}
