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
public class Jeton {
    private int   id_pack,quantie_pack,prix_pack;
    private String description_pack;

    public Jeton(int id_pack, int quantie_pack, int prix_pack, String description_pack) {
        this.id_pack = id_pack;
        this.quantie_pack = quantie_pack;
        this.prix_pack = prix_pack;
        this.description_pack = description_pack;
    }

    public Jeton(int quantie_pack, int prix_pack, String description_pack) {
        this.quantie_pack = quantie_pack;
        this.prix_pack = prix_pack;
        this.description_pack = description_pack;
    }

    public Jeton() {
    }

    public int getId_pack() {
        return id_pack;
    }

    

    public void setId_pack(int id_pack) {
        this.id_pack = id_pack;
    }

    public int getQuantie_pack() {
        return quantie_pack;
    }

    public void setQuantie_pack(int quantie_pack) {
        this.quantie_pack = quantie_pack;
    }

    public int getPrix_pack() {
        return prix_pack;
    }

    public void setPrix_pack(int prix_pack) {
        this.prix_pack = prix_pack;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jeton other = (Jeton) obj;
        if (this.id_pack != other.id_pack) {
            return false;
        }
        return true;
    }

    public String getDescription_pack() {
        return description_pack;
    }

    public void setDescription_pack(String description_pack) {
        this.description_pack = description_pack;
    }
    
    @Override
    public String toString() {
        return "Jeton{" + "id_pack=" + id_pack + ", quantie_pack=" + quantie_pack + ", prix_pack=" + prix_pack + ", description_pack=" + description_pack + '}';
    }
    
}
