/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;
import java.sql.Date;
/**
 *
 * @author aziz karoui
 */
public class Livre {
    
   
    private int id_livre, prix_livre, id_ecrivain_livre, evalution_livre, id_categorie_livre;
    private String titre_livre, description_livre , photo_livre ,contenu_livre ;
    private Date date_publication_livre;
    private CategorieLivre categorieLivre;
    private Utilisateur utilisateur ;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /*public Livre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    public int getId_categorie_livre() {
        return id_categorie_livre;
    }

    public void setId_categorie_livre(int id_categorie_livre) {
        this.id_categorie_livre = id_categorie_livre;
    }
    
    
    
     public int getPrix_livre() {
        return prix_livre;
    }

    public void setPrix_livre(int prix_livre) {
        this.prix_livre = prix_livre;
    }
    
    
    public int getId_ecrivain_livre() {
        return id_ecrivain_livre;
    }

    public void setId_ecrivain_livre(int id_ecrivain_livre) {
        this.id_ecrivain_livre = id_ecrivain_livre;
    }
    
    
    
    public String getTitre_livre() {
        return titre_livre;
    }

    public void setTitre_livre(String titre_livre) {
        this.titre_livre = titre_livre;
    }
    
    
    
    public String getDescription_livre() {
        return description_livre;
    }

    public void setDescription_livre(String description_livre) {
        this.description_livre = description_livre;
    }
    
    
    
    public String getPhoto_livre() {
        return photo_livre;
    }

    public void setPhoto_livre(String photo_livre) {
        this.photo_livre = photo_livre;
    }
    
    
    
    public String getContenu_livre() {
        return contenu_livre;
    }

    public void setContenu_livre(String contenu_livre) {
        this.contenu_livre = contenu_livre;
    }
    
    
    
    public int getEvalution_livre() {
        return evalution_livre;
    }

    public void setEvalution_livre(int evalution_livre) {
        this.evalution_livre = evalution_livre;
    }
    
    
    
     public Date getDate_publication_livre() {
        return date_publication_livre;
    }

    public void setDate_publication_livre(Date date_publication_livre) {
        this.date_publication_livre = date_publication_livre;
    }
    
      public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }
    
    
    
    
    public Livre(int id_livre, String titre_livre, int prix_livre,String description_livre, Date date_publication_livre , String photo_livre,String contenu_livre,int id_ecrivain_livre,int evalution_livre,int id_categorie_livre) {
        this.id_livre = id_livre;
        this.titre_livre = titre_livre;
        this.prix_livre = prix_livre;
        this.description_livre=description_livre;
        this.date_publication_livre = date_publication_livre;
        this.photo_livre = photo_livre;
        this.contenu_livre = contenu_livre;
        this.id_ecrivain_livre = id_ecrivain_livre;
        this.evalution_livre = evalution_livre;
        //id_categorie_livre
        this.id_categorie_livre = id_categorie_livre;



    }
    
    public Livre( String titre_livre,String description_livre,Date date_publication_livre, String photo_livre,String contenu_livre, int prix_livre,int evalution_livre ,int id_ecrivain_livre, int id_categorie_livre) {
        this.titre_livre = titre_livre;
        this.prix_livre = prix_livre;
        this.description_livre=description_livre;
        this.date_publication_livre = date_publication_livre;
        this.photo_livre = photo_livre;
        this.contenu_livre = contenu_livre;
        this.id_ecrivain_livre = id_ecrivain_livre;
        this.evalution_livre = evalution_livre;
        this.id_categorie_livre = id_categorie_livre;

    }

    public Livre() {
        this.titre_livre = "test";
        this.prix_livre = 0;
        this.description_livre = "test";
        this.date_publication_livre = null;
        this.photo_livre = "";
        this.contenu_livre = "";
        this.id_ecrivain_livre = 1;
        this.evalution_livre = 1;  
        this.id_categorie_livre=1;
        
        
        
        
    }

    public CategorieLivre getCategorieLivre() {
        return categorieLivre;
    }

    public void setCategorieLivre(CategorieLivre categorieLivre) {
        this.categorieLivre = categorieLivre;
    }

    public Livre(int id_livre, int prix_livre, int id_ecrivain_livre, int evalution_livre, String titre_livre, String description_livre, String photo_livre, String contenu_livre, Date date_publication_livre, CategorieLivre categorieLivre) {
        this.id_livre = id_livre;
        this.prix_livre = prix_livre;
        this.id_ecrivain_livre = id_ecrivain_livre;
        this.evalution_livre = evalution_livre;
        this.titre_livre = titre_livre;
        this.description_livre = description_livre;
        this.photo_livre = photo_livre;
        this.contenu_livre = contenu_livre;
        this.date_publication_livre = date_publication_livre;
        this.categorieLivre = categorieLivre;
    }

    @Override
    public String toString() {
        return "Livre{" + "id_livre=" + id_livre + ", prix_livre=" + prix_livre + ", id_ecrivain_livre=" + id_ecrivain_livre + ", evalution_livre=" + evalution_livre + ", id_categorie_livre=" + id_categorie_livre + ", titre_livre=" + titre_livre + ", description_livre=" + description_livre + ", photo_livre=" + photo_livre + ", contenu_livre=" + contenu_livre + ", date_publication_livre=" + date_publication_livre + ", categorieLivre=" + categorieLivre +'}';
    }

    public Livre(int id_livre, int prix_livre, int id_ecrivain_livre, int evalution_livre, int id_categorie_livre, String titre_livre, String description_livre, String photo_livre, String contenu_livre, Date date_publication_livre, CategorieLivre categorieLivre, Utilisateur utilisateur) {
        this.id_livre = id_livre;
        this.prix_livre = prix_livre;
        this.id_ecrivain_livre = id_ecrivain_livre;
        this.evalution_livre = evalution_livre;
        this.id_categorie_livre = id_categorie_livre;
        this.titre_livre = titre_livre;
        this.description_livre = description_livre;
        this.photo_livre = photo_livre;
        this.contenu_livre = contenu_livre;
        this.date_publication_livre = date_publication_livre;
        this.categorieLivre = categorieLivre;
        this.utilisateur = utilisateur;
    }
    

    
}
