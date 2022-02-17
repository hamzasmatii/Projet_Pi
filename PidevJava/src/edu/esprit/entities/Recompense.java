/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Hamza
 */
public class Recompense {
    private int id_recomponse , prix_recomponse	;
    private String nom_recomponse,description_recomponse,photo_recomponse;

    public Recompense(int id_recomponse, int prix_recomponse, String nom_recomponse, String description_recomponse, String photo_recomponse) {
        this.id_recomponse = id_recomponse;
        this.prix_recomponse = prix_recomponse;
        this.nom_recomponse = nom_recomponse;
        this.description_recomponse = description_recomponse;
        this.photo_recomponse = photo_recomponse;
    }

    public Recompense(int prix_recomponse, String nom_recomponse, String description_recomponse, String photo_recomponse) {
        this.prix_recomponse = prix_recomponse;
        this.nom_recomponse = nom_recomponse;
        this.description_recomponse = description_recomponse;
        this.photo_recomponse = photo_recomponse;
    }

    public Recompense() {
    }

    public int getId_recomponse() {
        return id_recomponse;
    }

    public void setId_recomponse(int id_recomponse) {
        this.id_recomponse = id_recomponse;
    }

    public int getPrix_recomponse() {
        return prix_recomponse;
    }

    public void setPrix_recomponse(int prix_recomponse) {
        this.prix_recomponse = prix_recomponse;
    }

    public String getNom_recomponse() {
        return nom_recomponse;
    }

    public void setNom_recomponse(String nom_recomponse) {
        this.nom_recomponse = nom_recomponse;
    }

    public String getDescription_recomponse() {
        return description_recomponse;
    }

    public void setDescription_recomponse(String description_recomponse) {
        this.description_recomponse = description_recomponse;
    }

    public String getPhoto_recomponse() {
        return photo_recomponse;
    }

    public void setPhoto_recomponse(String photo_recomponse) {
        this.photo_recomponse = photo_recomponse;
    }

    @Override
    public String toString() {
        return "Recompense{" + "id_recomponse=" + id_recomponse + ", prix_recomponse=" + prix_recomponse + ", nom_recomponse=" + nom_recomponse + ", description_recomponse=" + description_recomponse + ", photo_recomponse=" + photo_recomponse + '}';
    }
    
    

    
}
