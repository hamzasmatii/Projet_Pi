/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

/**
 *
 * @author Haythem
 */
import edu.esprit.dao.interfaces.IContrat;
import edu.esprit.entities.Contrat;
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




public class ContratDAO implements IContrat {
    private Connection connection;

    public ContratDAO() {
        connection = MyConnection.getInstance();
         }
   


    @Override
    public void insertContrat(Contrat contrat) {
       String requete = "insert into contrat (duree_contrat,description_contrat,id_maison_edition,id_ecrivain) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setInt(1, contrat.getDuree_contrat());
            ps.setString(2, contrat.getDescription_contrat());
            ps.setInt(3, contrat.getId_maison_edition());
            ps.setInt(4, contrat.getId_ecrivain());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateContrat(Contrat c) {
        String requete = "update contrat set duree_contrat=?, description_contrat=?, id_maison_edition=?,id_ecrivain=? where id_contrat=?";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, c.getDuree_contrat());
            ps.setString(2, c.getDescription_contrat());
            
            ps.setInt(3, c.getId_maison_edition());
            ps.setInt(4, c.getId_ecrivain());
            ps.setInt(5, c.getId_contrat());
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteContrat(int id) {
        String requete = "delete from contrat where id_contrat=?";
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
    public List<Contrat> DisplayAllContrat() {
         List<Contrat> listecontrat = new ArrayList<Contrat>();

        String requete = "select * from contrat";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Contrat contrat = new Contrat();
                contrat.setId_contrat(resultat.getInt(1));
                contrat.setId_ecrivain(resultat.getInt(5));
                contrat.setId_maison_edition(resultat.getInt(4));
                contrat.setDuree_contrat(resultat.getInt(2));
                contrat.setDescription_contrat(resultat.getString(3));
                listecontrat.add(contrat);
            }
            return listecontrat;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
       }     
    }
    
    private static ContratDAO contratDAO;

    public static ContratDAO getInstance() {
        if (contratDAO == null) {
            contratDAO = new ContratDAO();
        }
        return contratDAO;
    }
    
}
