/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.ILivre;
import edu.esprit.entities.CategorieLivre;
import edu.esprit.entities.Livre;
import edu.esprit.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.util.MyConnection;
import edu.esprit.util.Statics;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author aziz karoui
 */
public class LivreDAO implements ILivre {
    
  private Connection connection;

    public LivreDAO() {
        connection = MyConnection.getInstance();
       
    }

// ObservableList<LivreDAO>list=FXCollections.observableArrayList();

    
public void insertLivre(Livre l) {
        String req = "INSERT INTO `livre` (`titre_livre`, `description_livre`,`date_publication_livre`, `photo_livre`, `contenu_livre`, `prix_livre`, `evalution_livre`, `id_ecrivain_livre`,`id_categorie_livre`) "
                + "VALUES (?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement usl = connection.prepareStatement(req);
            usl.setString(1, l.getTitre_livre());
            
            usl.setString(2, l.getDescription_livre());
            usl.setDate(3, l.getDate_publication_livre());
            
            usl.setString(4, l.getPhoto_livre());
            usl.setString(5, l.getContenu_livre());
            usl.setInt(6, l.getPrix_livre());
            usl.setInt(8, l.getId_ecrivain_livre());
            usl.setInt(7, l.getEvalution_livre());
            usl.setInt(9, l.getId_categorie_livre());

            usl.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLivre(edu.esprit.entities.Livre l) {
        String req = "update livre set titre_livre=? , description_livre=? , date_publication_livre=? ,photo_livre=? ,contenu_livre=? ,prix_livre=?,evalution_livre=?,id_ecrivain_livre=?  where id_livre=?";
        try {
            PreparedStatement usl = connection.prepareStatement(req);
            usl.setString(1, l.getTitre_livre());
           
            usl.setString(2, l.getDescription_livre());
            usl.setDate(3, l.getDate_publication_livre());
            usl.setString(4, l.getPhoto_livre());
            usl.setString(5, l.getContenu_livre());
           
             usl.setInt(6, l.getPrix_livre());
             
            usl.setInt(7, l.getEvalution_livre());
            usl.setInt(8, l.getId_ecrivain_livre());
            usl.setInt(9, l.getId_livre());

            usl.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    
     public void deleteLivre(int id) {
        String requete = "delete from livre where id_livre=?";
        try {
            PreparedStatement us = connection.prepareStatement(requete);
            us.setInt(1, id);
            us.executeUpdate();
            System.out.println("livre supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
     
     public edu.esprit.entities.Livre findLivreById(int id) {
        edu.esprit.entities.Livre livre = new edu.esprit.entities.Livre();
        String requete = "select * from livre where id_livre=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                livre.setId_livre(resultat.getInt(1));
                livre.setTitre_livre(resultat.getString(2));
                livre.setDescription_livre(resultat.getString(3));
                livre.setDate_publication_livre(resultat.getDate(4));
                livre.setPhoto_livre(resultat.getString(5));
                livre.setContenu_livre(resultat.getString(6));
                                livre.setPrix_livre(resultat.getInt(7));
                
                livre.setEvalution_livre(resultat.getInt(8));
                livre.setId_ecrivain_livre(resultat.getInt(9));

            }
            return livre;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }
     
     public List<edu.esprit.entities.Livre> DisplayAllLivre() {

        List<edu.esprit.entities.Livre> listedepots = new ArrayList<edu.esprit.entities.Livre>();

        String requete = "select L.*,C.*,U.* from livre L join categorie_livre C on L.id_categorie_livre=C.id_categorie_livre join utilisateur U on U.id_utilisateur=L.id_ecrivain_livre";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                edu.esprit.entities.Livre livre = new edu.esprit.entities.Livre();
                CategorieLivre categorielivre = new CategorieLivre();
                Utilisateur utilisateur = new Utilisateur();
                livre.setId_livre(resultat.getInt(1));
                livre.setTitre_livre(resultat.getString(2));
                livre.setDescription_livre(resultat.getString(3));
                livre.setDate_publication_livre(resultat.getDate(4));
                livre.setPhoto_livre(resultat.getString(5));
                livre.setContenu_livre(resultat.getString(6));
                livre.setPrix_livre(resultat.getInt(7));
                
                livre.setEvalution_livre(resultat.getInt(8));
                livre.setId_ecrivain_livre(resultat.getInt(9));
                livre.setId_categorie_livre(resultat.getInt(11));
                categorielivre.setId_categorie_livre(resultat.getInt(10));
                categorielivre.setLibelle(resultat.getString(12));
                utilisateur.setNom_utilisateur(resultat.getString(14));
                livre.setUtilisateur(utilisateur);
                livre.setCategorieLivre(categorielivre);
                System.out.println(livre);
                listedepots.add(livre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }  
     public List<edu.esprit.entities.Livre> DisplayAllLivreByCategorie(CategorieLivre l) {

        List<edu.esprit.entities.Livre> listedepots = new ArrayList<edu.esprit.entities.Livre>();

      String requete = "select L.*,C.*,U.* from livre L join categorie_livre C on L.id_categorie_livre=C.id_categorie_livre join utilisateur U on L.id_ecrivain_livre=U.id_utilisateur where C.id_categorie_livre= ?";
               // String requete="select * from livre where id_categorie_livre=? ";
      try {
            PreparedStatement statement = connection.prepareStatement(requete);
            statement.setInt(1, l.getId_categorie_livre());
            ResultSet resultat = statement.executeQuery();

            while (resultat.next()) {
               edu.esprit.entities.Livre livre = new edu.esprit.entities.Livre();
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId_utilisateur(resultat.getInt(13));
                utilisateur.setNom_utilisateur(resultat.getString(14));
                utilisateur.setDate_naissance_utilisateur(resultat.getDate(15));
                utilisateur.setPhoto_utilisateur(resultat.getString(16));
                utilisateur.setType_utilisateur(resultat.getInt(17));
                utilisateur.setSolde_utilisateur(resultat.getInt(18));
              
                livre.setId_livre(resultat.getInt(1));
                livre.setTitre_livre(resultat.getString(2));
                livre.setDescription_livre(resultat.getString(3));
                livre.setDate_publication_livre(resultat.getDate(4));
                livre.setPhoto_livre(resultat.getString(5));
                livre.setContenu_livre(resultat.getString(6));
                livre.setPrix_livre(resultat.getInt(7));
                livre.setEvalution_livre(resultat.getInt(8));
                livre.setId_ecrivain_livre(resultat.getInt(9));
                livre.setCategorieLivre(l);
                livre.setUtilisateur(utilisateur);
        
                System.out.println(livre);
                listedepots.add(livre);
                System.out.println("-------------------"+livre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }    
     
     
     
     public List<edu.esprit.entities.Livre> DisplayAllLivreByDate() {

        List<edu.esprit.entities.Livre> listedepots = new ArrayList<edu.esprit.entities.Livre>();

        String requete = "select L.*,C.* from livre L join categorie_livre C where L.id_categorie_livre=C.id_categorie_livre ORDER BY date_publication_livre DESC";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                edu.esprit.entities.Livre livre = new edu.esprit.entities.Livre();
                CategorieLivre categorielivre = new CategorieLivre();
                livre.setId_livre(resultat.getInt(1));
                livre.setTitre_livre(resultat.getString(2));
                livre.setDescription_livre(resultat.getString(3));
                livre.setDate_publication_livre(resultat.getDate(4));
                livre.setPhoto_livre(resultat.getString(5));
                livre.setContenu_livre(resultat.getString(6));
                livre.setPrix_livre(resultat.getInt(7));
                
                livre.setEvalution_livre(resultat.getInt(8));
                livre.setId_ecrivain_livre(resultat.getInt(9));
                livre.setId_categorie_livre(resultat.getInt(11));
                categorielivre.setId_categorie_livre(resultat.getInt(10));
                categorielivre.setLibelle(resultat.getString(12));
                livre.setCategorieLivre(categorielivre);
                System.out.println(livre);
                listedepots.add(livre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }    
     
    public List<edu.esprit.entities.Livre> FiltreLivre(String filtre , String key) {
        
        System.out.println(key);
        List<Livre> listedepots = new ArrayList<Livre>();
        String requete = "select * from livre where `"+filtre+"` like '"+key+"%' ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
       //      ps.setString(1,filtre);
         //    ps.setString(2, key );
             System.out.println(ps);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                edu.esprit.entities.Livre livre = new edu.esprit.entities.Livre();
                livre.setId_livre(resultat.getInt(1));
                livre.setTitre_livre(resultat.getString(2));
                livre.setDescription_livre(resultat.getString(3));
                livre.setDate_publication_livre(resultat.getDate(4));
                livre.setPhoto_livre(resultat.getString(5));
                livre.setContenu_livre(resultat.getString(6));
                                livre.setPrix_livre(resultat.getInt(7));
                
                livre.setEvalution_livre(resultat.getInt(8));
                livre.setId_ecrivain_livre(resultat.getInt(9));

                listedepots.add(livre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }  
     
    
    
}
