/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IUtilisateur;
import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.dao.interfaces.IparticipationEvenementDAO;
import edu.esprit.dao.interfaces.ItypeEvenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class EvenementDAO implements IevenementDAO{
    Connection connection;
    
    public EvenementDAO(){
        connection=MyConnection.getInstance();
    } 

    @Override
    public void insertEvenement(Evenement e) {
       String query="INSERT INTO evenement (titre_evenement,adresse_evenement,description_evenement,date_evenement,type_evenement,id_utilisateur,image) VALUES (?,?,?,?,?,?,?)";
             try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, e.getTitre_evenement());
            ps.setString(2, e.getAdresse_evenement());
            ps.setString(3, e.getDescription_evenement());
            ps.setTimestamp(4, e.getDate_evenement());
            ps.setInt(5, e.getType_evenements().getId_type_evenement());
            ps.setInt(6, e.getUtilisateur().getId_utilisateur());
            ps.setString(7, e.getImage());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void deleteEvenement(int id) {
        String query="DELETE FROM evenement where id_evenement=?";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void updateEvenement(Evenement e) {
        String query = "update evenement set description_evenement=?, date_evenement=?, adresse_evenement=? where id_evenement=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, e.getDescription_evenement());
            ps.setTimestamp(2, e.getDate_evenement());
            ps.setString(3, e.getAdresse_evenement());
            ps.setInt(4, e.getId_evenement());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
        
    }

    @Override
    public List<Evenement> fetchEvenement() {
       List<Evenement> listeEvenement = new ArrayList<Evenement>();

        String requete = "select * from evenement";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            ItypeEvenementDAO typeEvenementDAO= new Type_evenementDAO();
            IUtilisateur utilisateurDAO= new UtilisateurDAO();
            while (resultat.next()) {
                Evenement e = new Evenement ();
                e.setId_evenement(resultat.getInt(1));
                e.setTitre_evenement(resultat.getString(2));
                e.setAdresse_evenement(resultat.getString(3));
                e.setDescription_evenement(resultat.getString(4));
                e.setDate_creation_evenement((resultat.getDate(5)));
                e.setDate_evenement((resultat.getTimestamp(6)));
                int idTypeEvenement=resultat.getInt(7);
                e.setType_evenements(typeEvenementDAO.fetchTypeEvenementById(idTypeEvenement));
                e.setUtilisateur(utilisateurDAO.findUtilisateurtById(resultat.getInt(8)));
                listeEvenement.add(e);
                System.out.println(e);
            }
            return listeEvenement;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Evenement fetchEvenementById(int id) {
        String requete ="select * from evenement where id_evenement= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            statement.setInt(1,id);
            ResultSet resultat = statement.executeQuery();
            Evenement e = new Evenement ();
            ItypeEvenementDAO typeEvenementDAO= new Type_evenementDAO();
            IUtilisateur utilisateurDAO= new UtilisateurDAO();
            while (resultat.next()) {
                e.setId_evenement(resultat.getInt(1));
                e.setTitre_evenement(resultat.getString(2));
                e.setAdresse_evenement(resultat.getString(3));
                e.setDescription_evenement(resultat.getString(4));
                 e.setDate_creation_evenement((resultat.getDate(5)));
                 e.setDate_evenement((resultat.getTimestamp(6)));
                e.setType_evenements(typeEvenementDAO.fetchTypeEvenementById(resultat.getInt(7)));
                e.setUtilisateur(utilisateurDAO.findUtilisateurtById(resultat.getInt(8)));
                System.out.println(e);
            }
            return e;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des evenement 2 " + ex.getMessage());
            return null;
        }
        
    }
    
    @Override
    public List<Evenement> fetchPopularEvents() {
      IparticipationEvenementDAO pdao=new Participation_evenementDAO();
      String query ="select count(*) , e.* from participation_evenement p , evenement e where e.id_evenement=p.id_evenement GROUP by p.id_evenement order by count(*) desc LIMIT 3";
      Statement statement;
        try {
            statement = connection.createStatement();
      ResultSet resultat;
       
            resultat = statement.executeQuery(query);
        
      ItypeEvenementDAO typeEvenementDAO= new Type_evenementDAO();
      IUtilisateur utilisateurDAO= new UtilisateurDAO();
      List<Evenement> listeEvenement = new ArrayList<Evenement>();
         while (resultat.next()) {
                Evenement e = new Evenement ();
                e.setId_evenement(resultat.getInt(2));
                e.setTitre_evenement(resultat.getString(3));
                e.setAdresse_evenement(resultat.getString(4));
                e.setDescription_evenement(resultat.getString(5));
                e.setDate_creation_evenement((resultat.getDate(6)));
                e.setDate_evenement((resultat.getTimestamp(7)));
                int idTypeEvenement=resultat.getInt(8);
                e.setType_evenements(typeEvenementDAO.fetchTypeEvenementById(idTypeEvenement));
                e.setUtilisateur(utilisateurDAO.findUtilisateurtById(resultat.getInt(8)));
                listeEvenement.add(e);
                System.out.println(e);
            }
        
      return listeEvenement ;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
        
    }

    
    

