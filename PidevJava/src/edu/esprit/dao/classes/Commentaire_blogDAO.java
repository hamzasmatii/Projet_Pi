/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;
import edu.esprit.dao.interfaces.ICommentaire_blog;
import edu.esprit.dao.interfaces.ICommentaire_blog;
import edu.esprit.entities.Commentaire_blog;
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
import java.sql.Date;
/**
 *
 * @author user
 */
public class Commentaire_blogDAO implements ICommentaire_blog {

    private Connection connection;
    
    public Commentaire_blogDAO() {
        connection = MyConnection.getInstance();
    }
    
    @Override
    public void insertCommentaire_blog(Commentaire_blog commentaire) {
       String requete = "insert into commentaire_blog (id_utilisateur,id_blog,date_commentaire,contenu_commentaire) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,commentaire.getId_utilisateur());
            ps.setInt(2, commentaire.getId_blog());
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));;
            ps.setString(4, commentaire.getContenu_commentaire());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateCommentaire_Blog(Commentaire_blog c) {
       String requete = "update commentaire_blog set id_utilisateur=?, id_blog=?, date_commentaire=?,contenu_commentaire=? where id_commentaire=?"; 
     try {
            PreparedStatement ps = connection.prepareStatement(requete);
             ps.setInt(1,c.getId_utilisateur());
            ps.setInt(2, c.getId_blog());
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));;
            ps.setString(4, c.getContenu_commentaire());
            ps.setInt(5, c.getId_commentaire());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteCommentaire_blog(int id) {
          String requete = "delete from commentaire_blog where id_commentaire=?";
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
    public List<Commentaire_blog> DisplayAllCommentaire_blog() {
       List<Commentaire_blog> listecommentaire_blog = new ArrayList<Commentaire_blog>();

        String requete = "select * from commentaire_blog";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Commentaire_blog commentaire_blog = new Commentaire_blog();
                commentaire_blog.setId_commentaire(resultat.getInt(1));
                commentaire_blog.setId_blog(resultat.getInt(2));
                commentaire_blog.setId_utilisateur(resultat.getInt(3));
                commentaire_blog.setContenu_commentaire(resultat.getString(4));
                commentaire_blog.setDate_commentaire(resultat.getString(5));
               

                listecommentaire_blog.add(commentaire_blog);
            }
            return listecommentaire_blog;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
   
    
private static Commentaire_blogDAO Commentaire_blogDAO;

    public static Commentaire_blogDAO getInstance() {
        if (Commentaire_blogDAO == null) {
            Commentaire_blogDAO = new Commentaire_blogDAO();
        }
        return Commentaire_blogDAO;
    }
     
}
    

