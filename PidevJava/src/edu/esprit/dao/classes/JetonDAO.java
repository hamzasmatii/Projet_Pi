/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IJetonDAO;
import edu.esprit.entities.Jeton;
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
public class JetonDAO implements IJetonDAO {
     private Connection connection;

    public JetonDAO() {
        connection = MyConnection.getInstance();
    }

    @Override
    public void insertJeton(Jeton jeton) {
        String requete = "insert into pack_jeton (description_pack,quantie_pack,prix_pack) values (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, jeton.getDescription_pack());
            ps.setInt(2, jeton.getQuantie_pack());
            ps.setInt(3, jeton.getPrix_pack());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JetonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateJeton(Jeton j) {
        String requete = "update pack_jeton set description_pack=?, quantie_pack=?, prix_pack=? where id_pack=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, j.getDescription_pack());
            ps.setInt(2, j.getQuantie_pack());
            ps.setInt(3, j.getPrix_pack());
            ps.setInt(4, j.getId_pack());
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteJeton(int id) {
                String requete = "delete from pack_jeton where id_pack=?";
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
    public List<Jeton> DisplayAllJeton() {
                List<Jeton> listejeton = new ArrayList<Jeton>();

        String requete = "select * from pack_jeton";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Jeton jeton = new Jeton();
                jeton.setId_pack(resultat.getInt(1));
                jeton.setPrix_pack((int) resultat.getFloat(4));
                jeton.setDescription_pack(resultat.getString(2));
                jeton.setQuantie_pack(resultat.getInt(3));
                

                listejeton.add(jeton);
            }
            return listejeton;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }

    
    private static JetonDAO jetonDAO;

    public static JetonDAO getInstance() {
        if (jetonDAO == null) {
            jetonDAO = new JetonDAO();
        }
        return jetonDAO;
    }
    
    @Override
    public Jeton findJetonById(int idr) {
        Jeton jeton = new Jeton();
        String requete = "select * from pack_jeton where id_pack=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idr);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                jeton.setQuantie_pack(resultat.getInt(3));
                jeton.setId_pack(resultat.getInt(1));
                jeton.setPrix_pack((int) resultat.getFloat(4));
                jeton.setDescription_pack(resultat.getString(2));
                
                

                
            }
            return jeton;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des jeton " + ex.getMessage());
            return null;
        }
        
    }
}
