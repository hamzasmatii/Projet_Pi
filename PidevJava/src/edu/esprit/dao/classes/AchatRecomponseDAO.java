/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;



import edu.esprit.dao.interfaces.IAchatRecomponseDAO;
import edu.esprit.entities.AchatRecomponse;
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
public class AchatRecomponseDAO implements IAchatRecomponseDAO {
     private Connection connection;

    public AchatRecomponseDAO() {
        connection = MyConnection.getInstance();
    }

    @Override
    public void insertAchatRecomponse(AchatRecomponse achatrecomponse) {
        String requete = "insert into achat_recomponse (id_recomponse,id_utilisateur,quantite) values (?,?,?)";
                try {
                    PreparedStatement ps = connection.prepareStatement(requete);
                    ps.setInt(1, achatrecomponse.getId_recomponse());
                    ps.setInt(2, achatrecomponse.getId_utilisateur());
                    ps.setInt(3, achatrecomponse.getQuantite());
                    


                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(AchatRecomponseDAO.class.getName()).log(Level.SEVERE, null, ex);
                }    
    }

    @Override
    public void updateAchatRecomponse(AchatRecomponse a) {
        String requete = "update achat_recomponse set id_recomponse=?, quantite=? where id_utilisateur=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, a.getId_recomponse());
            ps.setInt(2, a.getQuantite());
            ps.setInt(3, a.getId_utilisateur());
            
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteAchatRecomponse(int id) {
               String requete = "delete from achat_recomponse where id_utilisateur=?";
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
    public List<AchatRecomponse> DisplayAllAchatRecomponse() {
                List<AchatRecomponse> listeachatrecomponse = new ArrayList<AchatRecomponse>();

        String requete = "select * from achat_recomponse";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                AchatRecomponse achatrecomponse = new AchatRecomponse();
                achatrecomponse.setId_recomponse(resultat.getInt(1));
                achatrecomponse.setId_utilisateur(resultat.getInt(2));
                achatrecomponse.setQuantite(resultat.getInt(3));
                

                listeachatrecomponse.add(achatrecomponse);
            }
            return listeachatrecomponse;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
    
    private static AchatRecomponseDAO achatrecomponseDAO;

    public static AchatRecomponseDAO getInstance() {
        if (achatrecomponseDAO == null) {
            achatrecomponseDAO = new AchatRecomponseDAO();
        }
        return achatrecomponseDAO;
    }
    
}
