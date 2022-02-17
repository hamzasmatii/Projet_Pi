/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.ItypeEvenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Type_evenement;
import edu.esprit.util.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khaled
 */
public class Type_evenementDAO implements ItypeEvenementDAO {
    
    Connection connection;
    
    public Type_evenementDAO(){
        connection=MyDB.getInstance().getConnexion();
    } 

    @Override
    public void insererTypeEvenement(Type_evenement t) {
        String query="INSERT INTO type_evenement (libelle_type_evenement) VALUES (?)";
             try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, t.getLibelle_type_evenement());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateTypeEvenment(Type_evenement t) {
        String query = "update type_evenement set libelle_type_evenement=?, where id_type_evenement=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, t.getLibelle_type_evenement());
            ps.setInt(2, t.getId_type_evenement());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
       
    }

    @Override
    public void deleteTypeEvenement(Type_evenement t) {
        String query="DELETE FROM type_evenement where id_type_evenement=?";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,t.getId_type_evenement());
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        
    }

    @Override
    public List<Type_evenement> fetchTypeEvenment() {
        List<Type_evenement> listeType = new ArrayList<Type_evenement>();

        String requete = "select * from type_evenement";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                Type_evenement t = new Type_evenement ();
                t.setId_type_evenement(resultat.getInt(1));
                t.setLibelle_type_evenement(resultat.getString(2));
                
                listeType.add(t);
            }
            return listeType;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }
    }
    

