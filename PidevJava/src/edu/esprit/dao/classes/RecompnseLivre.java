/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IRecompnseLivre;
import edu.esprit.entities.RecomponseLivre;
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
public class RecompnseLivre implements IRecompnseLivre {
     private Connection connection;

    public RecompnseLivre() {
        connection = MyConnection.getInstance();
    }

  

    @Override
    public void insertRecompenseLivre(RecomponseLivre recomponselivre) {
            String requete = "insert into recomponse_livre (id_livre,id_recomponse,quantite) values (?,?,?)";
                try {
                    PreparedStatement ps = connection.prepareStatement(requete);
                    ps.setInt(1, recomponselivre.getId_livre());
                    ps.setInt(2, recomponselivre.getId_recomponse());
                    ps.setInt(3, recomponselivre.getQuantite());
                    


                    ps.executeUpdate();
                } catch (SQLException ex) {//DAO=RecompnseLivre
                    Logger.getLogger(RecompnseLivre.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }

    @Override
    public void updateRecompenseLivre(RecomponseLivre r) {
                String requete = "update recomponse_livre set id_recomponse=?, quantite=? where id_livre=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, r.getId_recomponse());
            ps.setInt(2, r.getQuantite());
            ps.setInt(3, r.getId_livre());
            
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecompenseLivre(int id) {
               String requete = "delete from recomponse_livre where id_livre=?";
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
    public List<RecomponseLivre> DisplayAllRecompenseLivre() {
                List<RecomponseLivre> listerecomponselivre = new ArrayList<RecomponseLivre>();

        String requete = "select * from recomponse_livre";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                RecomponseLivre recomponselivre = new RecomponseLivre();
                recomponselivre.setId_recomponse(resultat.getInt(1));
                recomponselivre.setId_recomponse(resultat.getInt(2));
                recomponselivre.setQuantite(resultat.getInt(3));
                

                listerecomponselivre.add(recomponselivre);
            }
            return listerecomponselivre;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
    
    
    private static RecompnseLivre recompnseLivre;

    public static RecompnseLivre getInstance() {
        if (recompnseLivre == null) {
            recompnseLivre = new RecompnseLivre();
        }
        return recompnseLivre;
    }
}
