/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author aziz karoui
 */
public class CategorieLivre {
    
    
    private int id_categorie_livre ;
    private String libelle ;

    public int getId_categorie_livre() {
        return id_categorie_livre;
    }

    public void setId_categorie_livre(int id_categorie_livre) {
        this.id_categorie_livre = id_categorie_livre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "CategorieLivre{" + "id_categorie_livre=" + id_categorie_livre + ", libelle=" + libelle + '}';
    }

    public CategorieLivre(int id_categorie_livre, String libelle) {
        this.id_categorie_livre = id_categorie_livre;
        this.libelle = libelle;
    }

    public CategorieLivre(String libelle) {
        this.libelle = libelle;
    }

    public CategorieLivre() {
        this.id_categorie_livre = 0;
        this.libelle = "";
    }
    

    }
    

    
