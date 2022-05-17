/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

 
public class Contrat {
    private int id_contrat,id_maison_edition,id_ecrivain,duree_contrat;
private String description_contrat;

    public Contrat(int id_contrat, int id_maison_edition, int id_ecrivain, int duree_contrat, String description_contrat) {
        this.id_contrat = id_contrat;
        this.id_maison_edition = id_maison_edition;
        this.id_ecrivain = id_ecrivain;
        this.duree_contrat = duree_contrat;
        this.description_contrat = description_contrat;
    }

    public Contrat(int id_maison_edition, int id_ecrivain, int duree_contrat, String description_contrat) {
        this.id_maison_edition = id_maison_edition;
        this.id_ecrivain = id_ecrivain;
        this.duree_contrat = duree_contrat;
        this.description_contrat = description_contrat;
    }

    public Contrat() {
    }

   



    @Override
    public String toString() {
        return "Contrat{" + "id_contrat=" + id_contrat + ", id_maison_edition=" + id_maison_edition + ", id_ecrivain=" + id_ecrivain + ", duree_contrat=" + duree_contrat + ", description_contrat=" + description_contrat + '}';
    }

    public int getId_contrat() {
        return id_contrat;
    }

    public void setId_contrat(int id_contrat) {
        this.id_contrat = id_contrat;
    }

    public int getId_maison_edition() {
        return id_maison_edition;
    }

    public void setId_maison_edition(int id_maison_edition) {
        this.id_maison_edition = id_maison_edition;
    }

    public int getId_ecrivain() {
        return id_ecrivain;
    }

    public void setId_ecrivain(int id_ecrivain) {
        this.id_ecrivain = id_ecrivain;
    }

    public int getDuree_contrat() {
        return duree_contrat;
    }

    public void setDuree_contrat(int duree_contrat) {
        this.duree_contrat = duree_contrat;
    }

    public String getDescription_contrat() {
        return description_contrat;
    }

    public void setDescription_contrat(String description_contrat) {
        this.description_contrat = description_contrat;
    }

  

}
