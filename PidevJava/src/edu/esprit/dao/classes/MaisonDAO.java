/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;
import edu.esprit.dao.interfaces.IMaison;
import edu.esprit.entities.Maison;
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
 * @author Haythem
 */
public class MaisonDAO implements IMaison {
     private Connection connection;

    public MaisonDAO() {
        connection = MyConnection.getInstance();
    }

    @Override
    public void insertMaison(Maison maison) {
         String requete = "insert into masion_edition (adresse_maison_edition,photo_maison_edition,description_maison_edition,nom_maison_edition,id_admin_maison_edition) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, maison.getAdresse_maison_edition());
            ps.setString(2, maison.getPhoto_maison_edition());
            ps.setString(3, maison.getDescription_maison_edition());
            ps.setString(4, maison.getNom_maison_edition());
            ps.setInt(5, maison.getId_admin_maison_edition());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaisonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateMaison(Maison m) {
        String requete = "update masion_edition set adresse_maison_edition=?, photo_maison_edition=?, description_maison_edition=?,nom_maison_edition=?,id_admin_maison_edition=? where id_maison_edition=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
             ps.setString(1, m.getAdresse_maison_edition());
            ps.setString(2, m.getPhoto_maison_edition());
            ps.setString(3, m.getDescription_maison_edition());
            ps.setString(4, m.getNom_maison_edition());
            ps.setInt(5, m.getId_admin_maison_edition());
            ps.setInt(6, m.getId_maison_editio());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }

    }

    @Override
    public void deleteMaison(int id) {
         String requete = "delete from masion_edition where id_maison_edition=?";
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

    @Override
    public List<Maison> DisplayAllRecompense() {
        List<Maison> listemaison = new ArrayList<Maison>();

        String requete = "select * from masion_edition";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Maison maison = new Maison();
                maison.setId_maison_editio(resultat.getInt(1));
                maison.setAdresse_maison_edition(resultat.getString(2));
                maison.setPhoto_maison_edition(resultat.getString(3));
                maison.setDescription_maison_edition(resultat.getString(4));
                maison.setNom_maison_edition(resultat.getString(5));
                maison.setId_admin_maison_edition(resultat.getInt(6));
                listemaison.add(maison);
            }
            return listemaison;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
       }     
    }
    
private static MaisonDAO maisonDAO;

    public static MaisonDAO getInstance() {
        if (maisonDAO == null) {
            maisonDAO = new MaisonDAO();
        }
        return maisonDAO;
    }
}
