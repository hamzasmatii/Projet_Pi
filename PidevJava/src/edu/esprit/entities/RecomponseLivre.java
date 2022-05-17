/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.List;

/**
 *
 * @author Hamza
 */
public class RecomponseLivre {
    private int id_livre,id_recomponse,quantite;
    
    private Recompense recompense;

    public RecomponseLivre(int id_livre, int id_recomponse, int quantite) {
        this.id_livre = id_livre;
        this.id_recomponse = id_recomponse;
        this.quantite = quantite;
    }

    public RecomponseLivre(int id_recomponse, int quantite) {
        this.id_recomponse = id_recomponse;
        this.quantite = quantite;
    }

    public RecomponseLivre() {
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public int getId_recomponse() {
        return id_recomponse;
    }

    public void setId_recomponse(int id_recomponse) {
        this.id_recomponse = id_recomponse;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Recompense getRecompense() {
        return recompense;
    }

    public void setRecompense(Recompense recompense) {
        this.recompense = recompense;
    }

    @Override
    public String toString() {
        return "RecomponseLivre{" + "id_livre=" + id_livre + ", id_recomponse=" + id_recomponse + ", quantite=" + quantite + ", recompense=" + recompense + '}';
    }

   


    

    
}
