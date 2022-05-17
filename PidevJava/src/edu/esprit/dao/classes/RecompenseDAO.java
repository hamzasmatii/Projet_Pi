/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IRecompenseDAO;
import edu.esprit.entities.Recompense;
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
 * @author Hamza
 */
public class RecompenseDAO  implements IRecompenseDAO {
     private Connection connection;

    public RecompenseDAO() {
        connection = MyConnection.getInstance();
    }

    @Override
    public void insertRecompense(Recompense recompense) {
        String requete = "insert into recomponse (nom_recomponse,description_recomponse,photo_recomponse,prix_recomponse) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, recompense.getNom_recomponse());
            ps.setString(2, recompense.getDescription_recomponse());
            ps.setString(3, recompense.getPhoto_recomponse());
            ps.setInt(4, recompense.getPrix_recomponse());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RecompenseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
   public void updateRecompense(Recompense r) {
       String requete = "update recomponse set nom_recomponse=?, description_recomponse=?, photo_recomponse=?,prix_recomponse=? where id_recomponse=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, r.getNom_recomponse());
            ps.setString(2, r.getDescription_recomponse());
            ps.setString(3, r.getPhoto_recomponse());
            ps.setInt(4, r.getPrix_recomponse());
            ps.setInt(5, r.getId_recomponse());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }

    }

    @Override
    public void deleteRecompense(int id) {
        String requete = "delete from recomponse where id_recomponse=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    
    public List<Recompense> DisplayAllRecompense() {
        List<Recompense> listerecompense = new ArrayList<Recompense>();

        String requete = "select * from recomponse";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Recompense recompense = new Recompense();
                recompense.setId_recomponse(resultat.getInt(1));
                recompense.setNom_recomponse(resultat.getString(2));
                recompense.setDescription_recomponse(resultat.getString(3));
                recompense.setPhoto_recomponse(resultat.getString(4));
                recompense.setPrix_recomponse(resultat.getInt(5));

                listerecompense.add(recompense);
            }
            return listerecompense;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
 
    
private static RecompenseDAO recompenseDAO;

    public static RecompenseDAO getInstance() {
        if (recompenseDAO == null) {
            recompenseDAO = new RecompenseDAO();
        }
        return recompenseDAO;
    }

    @Override
    public Recompense findRecompenseById(int idr) {
       
        Recompense recomponse = new Recompense();
        String requete = "select * from recomponse where id_recomponse=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idr);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                recomponse.setId_recomponse(resultat.getInt(1));
                recomponse.setNom_recomponse(resultat.getString(2));
                recomponse.setDescription_recomponse(resultat.getString(3));
                recomponse.setPhoto_recomponse(resultat.getString(4));
                recomponse.setPrix_recomponse(resultat.getInt(5));
                

                
            }
            return recomponse;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }    

    
    
}
