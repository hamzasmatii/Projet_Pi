/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.ICommentaireLivre;
import edu.esprit.entities.CommentaireLivre;
import edu.esprit.entities.Utilisateur;
import edu.esprit.util.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aziz karoui
 */
public class CommentaireLivreDAO implements ICommentaireLivre {
           private Connection connection;
           
           public CommentaireLivreDAO() {
        connection = MyConnection.getInstance();
    }

    @Override
    public void insertCommentaireLivre(CommentaireLivre com) {
        
         String req = "INSERT INTO `commentaire_livre` (`id_utilisateur`,`contenu_commentaire`,`id_livre`,`date_commentaire`) "//id_livre
                + "VALUES (?,?) ";
        try {
            PreparedStatement ls = connection.prepareStatement(req);
            ls.setInt(1, com.getId_utilisateur());
            ls.setString(2, com.getContenu_commentaire());
            ls.setInt(3, com.getId_livre());
            ls.setDate(4, com.getDate_commentaire());
            ls.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateCommentaireLivre(CommentaireLivre com) {
String req = "update commentaire_livre set contenu_commentaire=? where id_commentaire=?";
        try {
            PreparedStatement ls = connection.prepareStatement(req);
            ls.setInt(2, com.getId_commentaire());
            ls.setString(1, com.getContenu_commentaire());
 

            ls.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteCommentaireLivre(int id) {
 String requete = "delete from commentaire_livre where id_commentaire=?";
        try {
            PreparedStatement ls = connection.prepareStatement(requete);
            ls.setInt(1, id);
            ls.executeUpdate();
            System.out.println("commentaire supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public CommentaireLivre findCommentaireLivreById(int id) {
CommentaireLivre commentairelivre = new CommentaireLivre();
        String requete = "select * from commentaire_livre where id_commentaire=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                commentairelivre.setId_livre(resultat.getInt(1));
                commentairelivre.setContenu_commentaire(resultat.getString(2));

            }
            return commentairelivre;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<CommentaireLivre> DisplayAllCommentaireLivre() {
        List<CommentaireLivre> listedepots = new ArrayList<CommentaireLivre>();

        String requete = "select * from commentaire_livre";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CommentaireLivre commentairelivre = new CommentaireLivre();
                commentairelivre.setId_utilisateur(resultat.getInt(1));
                commentairelivre.setContenu_commentaire(resultat.getString(2));
                commentairelivre.setId_livre(resultat.getInt(3));
                commentairelivre.setDate_commentaire(resultat.getDate(4));
                

                listedepots.add(commentairelivre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
    
    public List<CommentaireLivre> DisplayAllCommentaireLivreByLivre(int id) {
        List<CommentaireLivre> listedepots = new ArrayList<CommentaireLivre>();

        String requete = "select * from commentaire_livre CL join utilisateur U where CL.id_utilisateur=U.id_utilisateur and  id_livre=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            Utilisateur utilisateur = new Utilisateur();
            while (resultat.next()) {
                utilisateur.setNom_utilisateur(resultat.getString(7));
                CommentaireLivre commentairelivre = new CommentaireLivre();
                commentairelivre.setId_commentaire(resultat.getInt(1));
                commentairelivre.setContenu_commentaire(resultat.getString(3));
                commentairelivre.setId_livre(resultat.getInt(4));
                commentairelivre.setDate_commentaire(resultat.getDate(5));
                commentairelivre.setUtilisateur(utilisateur);
                //System.out.println(commentairelivre);
                listedepots.add(commentairelivre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    }
    

    

