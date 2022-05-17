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
public class AchatRecomponse {
    private int id_recomponse,id_utilisateur,quantite;
    private Recompense recompense;

    public AchatRecomponse(int id_recomponse, int id_utilisateur, int quantite) {
        this.id_recomponse = id_recomponse;
        this.id_utilisateur = id_utilisateur;
        this.quantite = quantite;
    }

    public AchatRecomponse(int id_utilisateur, int quantite) {
        this.id_utilisateur = id_utilisateur;
        this.quantite = quantite;
    }

    public AchatRecomponse() {
    }

    public int getId_recomponse() {
        return id_recomponse;
    }

    public void setId_recomponse(int id_recomponse) {
        this.id_recomponse = id_recomponse;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Recompense getRecompense() {
        return recompense;
    }

    public void setRecompense(Recompense recompense) {
        this.recompense = recompense;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "AchatRecomponse{" + "id_recomponse=" + id_recomponse + ", id_utilisateur=" + id_utilisateur + ", quantite=" + quantite + ", recompense=" + recompense + '}';
    }

    
    
    
}
