/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Khaled
 */
public class Type_evenement {
    private int id_type_evenement;
    private String libelle_type_evenement;
    
    
    
    public Type_evenement(){}
    public Type_evenement(int id_type_evenement, String libelle_type_evenement) {
        this.id_type_evenement = id_type_evenement;
        this.libelle_type_evenement = libelle_type_evenement;
    }

    public Type_evenement(String libelle_type_evenement) {
        this.libelle_type_evenement = libelle_type_evenement;
    }
    

    public int getId_type_evenement() {
        return id_type_evenement;
    }

    public void setId_type_evenement(int id_type_evenement) {
        this.id_type_evenement = id_type_evenement;
    }

    public String getLibelle_type_evenement() {
        return libelle_type_evenement;
    }

    public void setLibelle_type_evenement(String libelle_type_evenement) {
        this.libelle_type_evenement = libelle_type_evenement;
    }
    
    
}
