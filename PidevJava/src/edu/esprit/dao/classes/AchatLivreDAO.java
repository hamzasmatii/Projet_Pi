/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IAchatLivre;
import edu.esprit.entities.AchatLivre;
import edu.esprit.dao.interfaces.IAchatLivre;
import edu.esprit.entities.AchatLivre;
import edu.esprit.dao.interfaces.ICategorieLivre;
import edu.esprit.dao.interfaces.ILivre;
import edu.esprit.entities.CategorieLivre;
import edu.esprit.entities.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.util.MyConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author aziz karoui
 */
public class AchatLivreDAO implements IAchatLivre {
     private Connection connection;

    public AchatLivreDAO() {
        connection = MyConnection.getInstance();
    }

    public void insertAchatLivre(AchatLivre l) {
        String req = "INSERT INTO `achat_livre` (`id_utilisateur`,`id_livre`,`date_achat`) "
                + "VALUES (?,?,?) ";
        try {
            PreparedStatement ls = connection.prepareStatement(req);
            ls.setInt(1, l.getId_utilisateur());
            ls.setInt(2, l.getId_livre());
            ls.setDate(3, l.getDate_achat());

            ls.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAchatLivre(AchatLivre l) {
        String req = "update achat_livre set date_achat=? where id_utilisateur=? and id_livre=?";
        try {
            PreparedStatement ls = connection.prepareStatement(req);
            ls.setInt(2, l.getId_utilisateur());
            ls.setInt(3, l.getId_livre());
            ls.setDate(1, l.getDate_achat());
 

            ls.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void deleteAchatLivre(int id) {
        String requete = "delete from achat_livre where id_utilisateur=?";
        try {
            PreparedStatement ls = connection.prepareStatement(requete);
            ls.setInt(1, id);
            ls.executeUpdate();
            System.out.println("AchatLivre supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public AchatLivre findAchatLivreById(int id) {
        AchatLivre achatLivre = new AchatLivre();
        String requete = "select * from achat_livre where id_utilisateur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                achatLivre.setId_utilisateur(resultat.getInt(1));
                achatLivre.setId_livre(resultat.getInt(2));
                achatLivre.setDate_achat(resultat.getDate(3));

            }
            return achatLivre;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    public List<AchatLivre> DisplayAllAchatLivre() {

        List<AchatLivre> listedepots = new ArrayList<AchatLivre>();

        String requete = "select * from achat_livre";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                AchatLivre achatLivre = new AchatLivre();
               achatLivre.setId_utilisateur(resultat.getInt(1));
                achatLivre.setId_livre(resultat.getInt(2));
                achatLivre.setDate_achat(resultat.getDate(3));
                listedepots.add(achatLivre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
    

    

}
