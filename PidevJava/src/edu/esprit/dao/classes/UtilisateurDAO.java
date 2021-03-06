/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.esprit.dao.interfaces.IUtilisateur;
import edu.esprit.entities.Utilisateur;
import edu.esprit.util.MyConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hrouz
 */
public class UtilisateurDAO implements IUtilisateur {
    
    private Connection connection;

    public UtilisateurDAO() {
        connection = MyConnection.getInstance();
    }
    
    
    public void insertUtilisateur(Utilisateur u) {
        String req = "INSERT INTO `utilisateur` (`nom_utilisateur`, `date_naissance_utilisateur`,`photo_utilisateur`, `type_utilisateur`, `solde_utilisateur` ,`email_utilisateur`) "
                + "VALUES (?,?,?,?,?,?) ";
        try {
            PreparedStatement us = connection.prepareStatement(req);
            us.setString(1, u.getNom_utilisateur());
            us.setDate(2, u.getDate_naissance_utilisateur());
            us.setString(3, u.getPhoto_utilisateur());
            us.setInt(4, u.getType_utilisateur());
            us.setInt(5, u.getSolde_utilisateur());
            us.setString(6, u.getEmail_utilisateur());
            us.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateUtilisateur(edu.esprit.entities.Utilisateur u) {
        String req = "update utilisateur set nom_utilisateur=? , date_naissance_utilisateur=? , photo_utilisateur=? , type_utilisateur=? ,solde_utilisateur=?,email_utilisateur=? where id_utilisateur=?  ";
        try {
            PreparedStatement us = connection.prepareStatement(req);
            us.setString(1, u.getNom_utilisateur());
            us.setDate(2, u.getDate_naissance_utilisateur());
            us.setString(3, u.getPhoto_utilisateur());
            us.setInt(4, u.getType_utilisateur());
            us.setInt(5, u.getSolde_utilisateur());
            us.setString(6, u.getEmail_utilisateur());
            us.setInt(7, u.getId_utilisateur());
            

            us.executeUpdate();
            System.out.println("Mise ?? jour effectu??e avec succ??s");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise ?? jour " + ex.getMessage());
        }
    }
    //karoui
    public void updateLivreEvaluationP(int id,int solde) {
        String req = "update utilisateur set solde_utilisateur=solde_utilisateur-? where id_utilisateur=? ";
       try {
            PreparedStatement us = connection.prepareStatement(req);
            us.setInt(1,solde);
            us.setInt(2,id);
            

            us.executeUpdate();
            System.out.println("achat avec succ??s");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur achat " + ex.getMessage());
        }
    }
    //karoui
    
     public void deleteUtilisateur(int id) {
        String requete = "delete from utilisateur where id_utilisateur=?";
        try {
            PreparedStatement us = connection.prepareStatement(requete);
            us.setInt(1, id);
            us.executeUpdate();
            System.out.println("utilisateur supprim??e");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
     
     public edu.esprit.entities.Utilisateur findUtilisateurtById(int id) {
        edu.esprit.entities.Utilisateur utilisateur = new edu.esprit.entities.Utilisateur();
        String requete = "select * from utilisateur where id_utilisateur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                utilisateur.setId_utilisateur(resultat.getInt(1));
                utilisateur.setNom_utilisateur(resultat.getString(2));
                utilisateur.setDate_naissance_utilisateur(resultat.getDate(3));
                utilisateur.setPhoto_utilisateur(resultat.getString(4));
                utilisateur.setType_utilisateur(resultat.getInt(5));
                utilisateur.setSolde_utilisateur(resultat.getInt(6));
                utilisateur.setEmail_utilisateur(resultat.getString(7));
            }
            return utilisateur;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }
     
     public edu.esprit.entities.Utilisateur findUtilisateurtByMail(String mail) {
        edu.esprit.entities.Utilisateur utilisateur = new edu.esprit.entities.Utilisateur();
        utilisateur.setType_utilisateur(0);
        String requete = "select * from utilisateur where email_utilisateur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, mail);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                utilisateur.setId_utilisateur(resultat.getInt(1));
                utilisateur.setNom_utilisateur(resultat.getString(2));
                utilisateur.setDate_naissance_utilisateur(resultat.getDate(3));
                utilisateur.setPhoto_utilisateur(resultat.getString(4));
                utilisateur.setType_utilisateur(resultat.getInt(5));
                utilisateur.setSolde_utilisateur(resultat.getInt(6));
                utilisateur.setEmail_utilisateur(resultat.getString(7));
            }
            System.out.println(utilisateur);
            return utilisateur;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }
     
     public List<edu.esprit.entities.Utilisateur> DisplayAllUtilisateurs() {

        List<edu.esprit.entities.Utilisateur> listedepots = new ArrayList<edu.esprit.entities.Utilisateur>();

        String requete = "select * from utilisateur";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                edu.esprit.entities.Utilisateur utilisateur = new edu.esprit.entities.Utilisateur();
                utilisateur.setId_utilisateur(resultat.getInt(1));
                utilisateur.setNom_utilisateur(resultat.getString(2));
                utilisateur.setDate_naissance_utilisateur(resultat.getDate(3));
                utilisateur.setPhoto_utilisateur(resultat.getString(4));
                utilisateur.setType_utilisateur(resultat.getInt(5));
                utilisateur.setSolde_utilisateur(resultat.getInt(6));
                utilisateur.setEmail_utilisateur(resultat.getString(7));

                listedepots.add(utilisateur);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
     
    public List<edu.esprit.entities.Utilisateur> filterUtilisateurs(String filter , String key) {

        List<edu.esprit.entities.Utilisateur> listedepots = new ArrayList<edu.esprit.entities.Utilisateur>();

        String requete = "select * from utilisateur where ? like ? ";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                edu.esprit.entities.Utilisateur utilisateur = new edu.esprit.entities.Utilisateur();
                utilisateur.setId_utilisateur(resultat.getInt(1));
                utilisateur.setNom_utilisateur(resultat.getString(2));
                utilisateur.setDate_naissance_utilisateur(resultat.getDate(3));
                utilisateur.setPhoto_utilisateur(resultat.getString(4));
                utilisateur.setType_utilisateur(resultat.getInt(5));
                utilisateur.setSolde_utilisateur(resultat.getInt(6));
                utilisateur.setEmail_utilisateur(resultat.getString(7));

                listedepots.add(utilisateur);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
     
    
}
