/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Hrouz
 */
public class Login {
    int id_login ;
    String email_login , mdp_login ;
    Utilisateur utilisateur;
    Boolean blocked_login;
    String blocked_message;
    String activation_token;
    Date blocked_duree;

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public Boolean getBlocked_login() {
        return blocked_login;
    }

    public String getBlocked_message() {
        return blocked_message;
    }

    public void setBlocked_message(String blocked_message) {
        this.blocked_message = blocked_message;
    }

    public Date getBlocked_duree() {
        return blocked_duree;
    }

    public void setBlocked_duree(Date blocked_duree) {
        this.blocked_duree = blocked_duree;
    }

    public void setBlocked_login(Boolean blocked_login) {
        this.blocked_login = blocked_login;
    }
    public int getId_login() {
        return id_login;
    }

    public void setId_login(int id_login) {
        this.id_login = id_login;
    }

    public String getEmail_login() {
        return email_login;
    }

    public void setEmail_login(String email_login) {
        this.email_login = email_login;
    }

    public String getMdp_login() {
        return mdp_login;
    }

    public void setMdp_login(String mdp_login) {
        this.mdp_login = mdp_login;
    }

    @Override
    public String toString() {
        if(id_login==0)
        {
            return "Login{" + "Utilisateur=" + utilisateur+"\n" + ", email_login=" + email_login + ", mdp_login=" + mdp_login + ",blocked_login="+blocked_login+",blocked_message="+blocked_message+",blocked_duree="+blocked_duree+ '}';
        }
        else
        {
        return "Login{" + "id_login=" + id_login + ", email_login=" + email_login + ", mdp_login=" + mdp_login + ",blocked_login="+blocked_login+ '}';
    
        }
    }

    public Login(int id_login, String email_login, String mdp_login,String activation_token) {
        this.id_login = id_login;
        this.email_login = email_login;
        this.mdp_login = mdp_login;
        this.activation_token=activation_token;
    }

    public Login() {
        this.id_login = 0;
        this.email_login = "";
        this.mdp_login = "";
        
    }

    public Login(String email_login, String mdp_login,String activation_token) {
        this.email_login = email_login;
        this.mdp_login = mdp_login;
        this.activation_token=activation_token;
    }
    
    public Login(Utilisateur utilisateur, String email_login, String mdp_login , Boolean blocked_login,String blocked_message,Date blocked_duree) {
        this.utilisateur = utilisateur;
        this.email_login = email_login;
        this.mdp_login = mdp_login;
        this.blocked_login=blocked_login;
        this.blocked_message=blocked_message;
        this.blocked_duree=blocked_duree;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
}
