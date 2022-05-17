/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author user
 */
public class Commentaire_blog {
   private int id_commentaire,id_utilisateur,id_blog;	
   private String date_commentaire,contenu_commentaire;

    public Commentaire_blog(int id_commentaire, int id_utilisateur, int id_blog, String date_commentaire, String contenu_commentaire) {
        this.id_commentaire = id_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id_blog = id_blog;
        this.date_commentaire = date_commentaire;
        this.contenu_commentaire = contenu_commentaire;
    }

    public Commentaire_blog(int id_utilisateur, int id_blog, String date_commentaire, String contenu_commentaire) {
        this.id_utilisateur = id_utilisateur;
        this.id_blog = id_blog;
        this.date_commentaire = date_commentaire;
        this.contenu_commentaire = contenu_commentaire;
    }

    public Commentaire_blog() {
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

    public int getId_blog() {
        return id_blog;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public String getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(String date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public String getContenu_commentaire() {
        return contenu_commentaire;
    }

    public void setContenu_commentaire(String contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire_blog{" + "id_commentaire=" + id_commentaire + ", id_utilisateur=" + id_utilisateur + ", id_blog=" + id_blog + ", date_commentaire=" + date_commentaire + ", contenu_commentaire=" + contenu_commentaire + '}';
    }
   
   

}
