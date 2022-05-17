/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.IBlog;
import edu.esprit.dao.interfaces.IBlog;
import edu.esprit.entities.Blog;
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
public class  BlogDAO implements IBlog {
    private Connection connection;

    public BlogDAO() {
        connection = MyConnection.getInstance();
    }

    @Override
    public void insertBlog(Blog blog) {
        String requete = "insert into blog (titre_blog,sujet_blog,photo_blog,id_utilisateur,like_blog,dislike_blog,date_blog	) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, blog.getTitre_blog());
            ps.setString(2, blog.getSujet_blog());
            ps.setString(3, blog.getPhoto_blog());
            ps.setInt(4, blog.getId_utilisateur());
            ps.setInt(5, blog.getLike_blog());
            ps.setInt(6, blog.getDislike_blog());
            ps.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));;
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateBlog(Blog b) {
         String requete = "update blog set titre_blog=?, sujet_blog=?, photo_blog=?,id_utilisateur=?,like_blog=?,dislike_blog=?,date_blog=? where id_blog=?";
        try {
            System.out.println(b);
            
            
                  PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, b.getTitre_blog());
            ps.setString(2, b.getSujet_blog());
            ps.setString(3, b.getPhoto_blog());
            ps.setInt(4, b.getId_utilisateur());
            ps.setInt(5, b.getLike_blog());
            ps.setInt(6, b.getDislike_blog());  
            ps.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));;
            ps.setInt(8, b.getId_blog());
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }

        
    }

    @Override
    public void deleteBlog(int id) {
         String requete = "delete from blog where id_blog=?";
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
    public List<Blog> DisplayAllBlog() {
        
     List<Blog> listeblog = new ArrayList<Blog>();

        String requete = "select * from blog ";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Blog blog = new Blog();
                blog.setId_blog(resultat.getInt(1));
                blog.setTitre_blog(resultat.getString(2));
                blog.setSujet_blog(resultat.getString(3));
                blog.setPhoto_blog(resultat.getString(4));
                blog.setId_utilisateur(resultat.getInt(5));
                blog.setLike_blog(resultat.getInt(6));
                blog.setDislike_blog(resultat.getInt(7));
                blog.setDate_blog(resultat.getString(8));

                listeblog.add(blog);
            }
            return listeblog;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
   
    
private static BlogDAO BlogDAO;

    public static BlogDAO getInstance() {
        if (BlogDAO == null) {
            BlogDAO = new BlogDAO();
        }
        return BlogDAO;
    }
    
}
