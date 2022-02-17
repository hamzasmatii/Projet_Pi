/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

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
import edu.esprit.util.MyDB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author aziz karoui
 */
public class CategorieLivreDAO implements ICategorieLivre {
        Connection connexion;
    Statement stm;

    public CategorieLivreDAO() {
        connexion = MyDB.getInstance().getConnexion();
    }

    public void insertCategorieLivre(CategorieLivre l) {
        String req = "INSERT INTO `categorie_livre` (`id_categorie_livre`,`libelle`) "
                + "VALUES (?,?) ";
        try {
            PreparedStatement ls = connexion.prepareStatement(req);
            ls.setInt(1, l.getId_categorie_livre());
            ls.setString(2, l.getLibelle());
            ls.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCategorieLivre(CategorieLivre l) {
        String req = "update categorie_livre set libelle=? where id_categorie_livre=?";
        try {
            PreparedStatement ls = connexion.prepareStatement(req);
            ls.setInt(2, l.getId_categorie_livre());
            ls.setString(1, l.getLibelle());
 

            ls.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void deleteCategorieLivre(int id) {
        String requete = "delete from categorie_livre where id_categorie_livre=?";
        try {
            PreparedStatement ls = connexion.prepareStatement(requete);
            ls.setInt(1, id);
            ls.executeUpdate();
            System.out.println("categorie_livre supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public CategorieLivre findCategorieLivreById(int id) {
        CategorieLivre categorielivre = new CategorieLivre();
        String requete = "select * from categorie_livre where id_categorie_livre=?";
        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                categorielivre.setId_categorie_livre(resultat.getInt(1));
                categorielivre.setLibelle(resultat.getString(2));

            }
            return categorielivre;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    public List<CategorieLivre> DisplayAllCategorieLivre() {

        List<CategorieLivre> listedepots = new ArrayList<CategorieLivre>();

        String requete = "select * from categorie_livre";
        try {
            Statement statement = connexion
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CategorieLivre categorielivre = new CategorieLivre();
                categorielivre.setId_categorie_livre(resultat.getInt(1));
                categorielivre.setLibelle(resultat.getString(2));

                listedepots.add(categorielivre);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
}
