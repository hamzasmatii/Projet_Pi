/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author user
 */
public class Blog {
    private int id_blog	,id_utilisateur	,like_blog,dislike_blog;
    private String titre_blog,sujet_blog,photo_blog,date_blog;

    public Blog(int id_blog, int id_utilisateur, int like_blog, int dislike_blog, String titre_blog, String sujet_blog, String photo_blog, String date_blog) {
        this.id_blog = id_blog;
        this.id_utilisateur = id_utilisateur;
        this.like_blog = like_blog;
        this.dislike_blog = dislike_blog;
        this.titre_blog = titre_blog;
        this.sujet_blog = sujet_blog;
        this.photo_blog = photo_blog;
        this.date_blog = date_blog;
    }

    public Blog(int id_utilisateur, int like_blog, int dislike_blog, String titre_blog, String sujet_blog, String photo_blog, String date_blog) {
        this.id_utilisateur = id_utilisateur;
        this.like_blog = like_blog;
        this.dislike_blog = dislike_blog;
        this.titre_blog = titre_blog;
        this.sujet_blog = sujet_blog;
        this.photo_blog = photo_blog;
        this.date_blog = date_blog;
    }

    public Blog() {
    }

    public int getId_blog() {
        return id_blog;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

   
    public String getTitre_blog() {
        return titre_blog;
    }

    public void setTitre_blog(String titre_blog) {
        this.titre_blog = titre_blog;
    }

    public String getSujet_blog() {
        return sujet_blog;
    }

    public void setSujet_blog(String sujet_blog) {
        this.sujet_blog = sujet_blog;
    }

    public String getPhoto_blog() {
        return photo_blog;
    }

    public void setPhoto_blog(String photo_blog) {
        this.photo_blog = photo_blog;
    }

    public String getDate_blog() {
        return date_blog;
    }

    public void setDate_blog(String date_blog) {
        this.date_blog = date_blog;
    }

    public int getLike_blog() {
        return like_blog;
    }

    public void setLike_blog(int like_blog) {
        this.like_blog = like_blog;
    }

    public int getDislike_blog() {
        return dislike_blog;
    }

    public void setDislike_blog(int dislike_blog) {
        this.dislike_blog = dislike_blog;
    }

    @Override
    public String toString() {
        return "Blog{" + "id_blog=" + id_blog + ", id_utilisateur=" + id_utilisateur + ", like_blog=" + like_blog + ", dislike_blog=" + dislike_blog + ", titre_blog=" + titre_blog + ", sujet_blog=" + sujet_blog + ", photo_blog=" + photo_blog + ", date_blog=" + date_blog + '}';
    }

  
}
