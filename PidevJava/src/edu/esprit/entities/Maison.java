/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Haythem
 */
public class Maison {

    public Maison(int id_maison_editio, String adresse_maison_edition, String description_maison_edition, String nom_maison_edition) {
        this.id_maison_editio = id_maison_editio;
        this.adresse_maison_edition = adresse_maison_edition;
        this.description_maison_edition = description_maison_edition;
        this.nom_maison_edition = nom_maison_edition;
    }
   private int  id_maison_editio ,id_admin_maison_edition;
   private String adresse_maison_edition,photo_maison_edition,description_maison_edition,nom_maison_edition;

    public Maison(int id_maison_editio, int id_admin_maison_edition, String adresse_maison_edition, String photo_maison_edition, String description_maison_edition, String nom_maison_edition) {
        this.id_maison_editio = id_maison_editio;
        this.id_admin_maison_edition = id_admin_maison_edition;
        this.adresse_maison_edition = adresse_maison_edition;
        this.photo_maison_edition = photo_maison_edition;
        this.description_maison_edition = description_maison_edition;
        this.nom_maison_edition = nom_maison_edition;
    }

    public Maison(int id_admin_maison_edition, String adresse_maison_edition, String photo_maison_edition, String description_maison_edition, String nom_maison_edition) {
        this.id_admin_maison_edition = id_admin_maison_edition;
        this.adresse_maison_edition = adresse_maison_edition;
        this.photo_maison_edition = photo_maison_edition;
        this.description_maison_edition = description_maison_edition;
        this.nom_maison_edition = nom_maison_edition;
    }

    public Maison() {
    }

    public int getId_maison_editio() {
        return id_maison_editio;
    }

    public void setId_maison_editio(int id_maison_editio) {
        this.id_maison_editio = id_maison_editio;
    }

    public int getId_admin_maison_edition() {
        return id_admin_maison_edition;
    }

    public void setId_admin_maison_edition(int id_admin_maison_edition) {
        this.id_admin_maison_edition = id_admin_maison_edition;
    }

    public String getAdresse_maison_edition() {
        return adresse_maison_edition;
    }

    public void setAdresse_maison_edition(String adresse_maison_edition) {
        this.adresse_maison_edition = adresse_maison_edition;
    }

    public String getPhoto_maison_edition() {
        return photo_maison_edition;
    }

    public void setPhoto_maison_edition(String photo_maison_edition) {
        this.photo_maison_edition = photo_maison_edition;
    }

    @Override
    public String toString() {
        return "Maison{" + "id_maison_editio=" + id_maison_editio + ", id_admin_maison_edition=" + id_admin_maison_edition + ", adresse_maison_edition=" + adresse_maison_edition + ", photo_maison_edition=" + photo_maison_edition + ", description_maison_edition=" + description_maison_edition + ", nom_maison_edition=" + nom_maison_edition + '}';
    }

    public String getDescription_maison_edition() {
        return description_maison_edition;
    }

    public void setDescription_maison_edition(String description_maison_edition) {
        this.description_maison_edition = description_maison_edition;
    }

    public String getNom_maison_edition() {
        return nom_maison_edition;
    }

    public void setNom_maison_edition(String nom_maison_edition) {
        this.nom_maison_edition = nom_maison_edition;
    }
   
}
