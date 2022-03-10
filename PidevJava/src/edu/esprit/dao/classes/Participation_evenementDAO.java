/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.dao.interfaces.IparticipationEvenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Participation_evenement;
import edu.esprit.entities.Type_evenement;
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
 * @author Khaled
 */
public class Participation_evenementDAO implements IparticipationEvenementDAO {
    
    
     Connection connection;
    
    public Participation_evenementDAO(){
        connection=MyConnection.getInstance();
    } 

    @Override
    public void insererParticipation(Participation_evenement p) {
       String query="INSERT INTO participation_evenement (id_utilisateur,id_evenement) VALUES (?,?)";
             try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, p.getUtilisateur().getId_utilisateur());
            ps.setInt(2, p.getEvenement().getId_evenement());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateParticipation(Participation_evenement p) {
        String query = "update participation_evenement set date_participation=? where (id_utilisateur=? and id_evenement=?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, p.getDate_participation().toString());
            ps.setInt(2, p.getUtilisateur().getId_utilisateur());
            ps.setInt(3, p.getEvenement().getId_evenement());
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
           ps.setInt(2, p.getUtilisateur().getId_utilisateur());
            ps.setInt(1, p.getEvenement().getId_evenement());
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Evenement> fetchParticipationByUser(int id) {
        List<Evenement> listeEvenement = new ArrayList<Evenement>();

        String requete = "select * from participation_evenement where id_utilisateur=?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            
            statement.setInt(1, id);
            ResultSet resultat = statement.executeQuery();
           
            IevenementDAO edao=new EvenementDAO();
            while (resultat.next()) {
                Evenement e = edao.fetchEvenementById(resultat.getInt(2));
                listeEvenement.add(e);
                System.out.println(e);
            }
            return listeEvenement;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des evenement " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Participation_evenement> fetchParticipationByEvent(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifEvenementUser(Evenement e, Utilisateur u) {
        String requete = "select * from participation_evenement where id_utilisateur=? and id_evenement=?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            
            statement.setInt(1, u.getId_utilisateur());
            statement.setInt(2,e.getId_evenement());
            ResultSet resultat = statement.executeQuery();
            
            return resultat.last();
            
        
    }    catch (SQLException ex) {
             Logger.getLogger(Participation_evenementDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    return false;
    
    
}

    @Override
    public boolean fetchUpcomingEvents(Utilisateur u) {
      List<Evenement> listeEvenement = new ArrayList<Evenement>();

        String requete = "SELECT e.* , u.* , t.* from participation_evenement p JOIN evenement e on p.id_evenement=e.id_evenement join utilisateur u on p.id_utilisateur=u.id_utilisateur join type_evenement t on e.type_evenement=t.id_type_evenement where e.date_evenement> now() and p.id_utilisateur=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            
            statement.setInt(1, u.getId_utilisateur());
            ResultSet resultat = statement.executeQuery();
            if (resultat.next() == false) { 
                
                return false; 
            }

 
            
            return true;
          
    
            /*while (resultat.next()) { 
                Evenement e=new Evenement();
                e.setId_evenement(resultat.getInt(1));
                e.setTitre_evenement(resultat.getString(2));
                e.setAdresse_evenement(resultat.getString(3));
                e.setDescription_evenement(resultat.getString(4));
                e.setDate_creation_evenement(resultat.getDate(5));
                e.setDate_evenement(resultat.getTimestamp(6));
                e.setImage(resultat.getString(9));
                e.setUtilisateur(u);
                Type_evenement t= new Type_evenement();
                t.setId_type_evenement(resultat.getInt(17));
                t.setLibelle_type_evenement(resultat.getString(17));
                e.setType_evenements(t);
                
                 
                listeEvenement.add(e);
                System.out.println(e);
            }
                return listeEvenement;
                    */
            
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des evenement " + ex.getMessage());
            return false;
        }
    }
    
    
}