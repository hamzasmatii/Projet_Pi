/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Hrouz
 */
public class Login {
    int id_login ;
    String email_login , mdp_login ;

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
        return "Login{" + "id_login=" + id_login + ", email_login=" + email_login + ", mdp_login=" + mdp_login + '}';
    }

    public Login(int id_login, String email_login, String mdp_login) {
        this.id_login = id_login;
        this.email_login = email_login;
        this.mdp_login = mdp_login;
    }

    public Login() {
        this.id_login = 0;
        this.email_login = "";
        this.mdp_login = "";
    }

    public Login(String email_login, String mdp_login) {
        this.email_login = email_login;
        this.mdp_login = mdp_login;
    }
    
}
