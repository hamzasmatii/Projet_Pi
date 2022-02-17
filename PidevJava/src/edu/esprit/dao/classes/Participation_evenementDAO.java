/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IparticipationEvenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Participation_evenement;
import edu.esprit.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Khaled
 */
public class Participation_evenementDAO implements IparticipationEvenementDAO {
    
    
     Connection connection;
    
    public Participation_evenementDAO(){
        connection=MyDB.getInstance().getConnexion();
    } 

    @Override
    public void insererParticipation(Participation_evenement p) {
       String query="INSERT INTO participation_evenement (id_utilisateur,id_evenement) VALUES (?,?)";
             try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, p.getId_utilisateur());
            ps.setInt(2, p.getId_evenement());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateParticipation(Participation_evenement p) {
        String query = "update participation_evenement set date_participation=?, where id_utilisateur=? and id_evenement=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, p.getDate_participation().toString());
            ps.setInt(2, p.getId_utilisateur());
            ps.setInt(3, p.getId_evenement());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteParticipation(Participation_evenement p) {
        String query="DELETE FROM participation_evenement where id_evenement=? and id_utilisateur=?";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,p.getId_evenement());
            ps.setInt(2,p.getId_utilisateur());
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Evenement> fetchParticipationByUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participation_evenement> fetchParticipationByEvent(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
