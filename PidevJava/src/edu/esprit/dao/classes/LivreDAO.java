/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.ILivre;
import edu.esprit.entities.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.util.MyDB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author aziz karoui
 */
public class LivreDAO implements ILivre {
    
    Connection connexion;
    Statement stm;

    public LivreDAO() {
        connexion = MyDB.getInstance().getConnexion();
    }



public void insertLivre(Livre l) {
        String req = "INSERT INTO `livre` (`titre_livre`, `description_livre`,`date_publication_livre`, `photo_livre`, `contenu_livre`, `prix_livre`, `evalution_livre`, `id_ecrivain_livre`) "
                + "VALUES (?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement usl = connexion.prepareStatement(req);
            usl.setString(1, l.getTitre_livre());
            
            usl.setString(2, l.getDescription_livre());
            usl.setDate(3, l.getDate_publication_livre());
            
            usl.setString(4, l.getPhoto_livre());
            usl.setString(5, l.getContenu_livre());
            usl.setInt(6, l.getPrix_livre());
            usl.setInt(7, l.getId_ecrivain_livre());
            usl.setInt(8, l.getEvalution_livre());

            usl.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLivre(edu.esprit.entities.Livre l) {
        String req = "update livre set titre_livre=? , description_livre=? , date_publication_livre=? ,photo_livre=? ,contenu_livre=? ,prix_livre=?,evalution_livre=?,id_ecrivain_livre=?  where id_livre=?";
        try {
            PreparedStatement usl = connexion.prepareStatement(req);
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
            PreparedStatement us = connexion.prepareStatement(requete);
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
            PreparedStatement ps = connexion.prepareStatement(requete);
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

        String requete = "select * from livre";
        try {
            Statement statement = connexion
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

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
