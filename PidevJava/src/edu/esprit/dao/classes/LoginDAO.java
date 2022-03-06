/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.classes;

import edu.esprit.dao.interfaces.ILogin;
import edu.esprit.entities.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.util.MyConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hrouz
 */
public class LoginDAO implements ILogin {

    private Connection connection;

    public LoginDAO() {
        connection = MyConnection.getInstance();
    }

    public void insertLogin(Login l) {
        String req = "INSERT INTO `login` (`id_login`,`email_login`, `mdp_login`) "
                + "VALUES (?,?,?) ";
        try {
            PreparedStatement ls = connection.prepareStatement(req);
            ls.setInt(1, l.getId_login());
            ls.setString(2, l.getEmail_login());
            ls.setString(3, doHashing(l.getMdp_login()));
            ls.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLogin(Login l) {
        String req = "update login set email_login=? , mdp_login=? where id_login=?";
        try {
            PreparedStatement ls = connection.prepareStatement(req);
            ls.setString(1, l.getEmail_login());
            ls.setString(2, l.getMdp_login());
            ls.setInt(3, l.getId_login());

            ls.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void deleteLogin(int id) {
        String requete = "delete from login where id_login=?";
        try {
            PreparedStatement ls = connection.prepareStatement(requete);
            ls.setInt(1, id);
            ls.executeUpdate();
            System.out.println("login supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public Login findLoginById(int id) {
        Login login = new Login();
        String requete = "select * from login where id_login=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            UtilisateurDAO us = new UtilisateurDAO();
            while (resultat.next()) {
                login.setUtilisateur(us.findUtilisateurtById(resultat.getInt(1)));
                login.setEmail_login(resultat.getString(2));
                login.setMdp_login(resultat.getString(3));

            }
            return login;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    public List<Login> DisplayAllLogins() {

        List<Login> listedepots = new ArrayList<Login>();

        String requete = "select * from login";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Login login = new Login();
                login.setId_login(resultat.getInt(1));
                login.setEmail_login(resultat.getString(2));
                login.setMdp_login(resultat.getString(3));

                listedepots.add(login);
            }
            return listedepots;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
    
            public String doHashing (String password) {
         try {
          MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

          messageDigest.update(password.getBytes());

          byte[] resultByteArray = messageDigest.digest();

          StringBuilder sb = new StringBuilder();

          for (byte b : resultByteArray) {
           sb.append(String.format("%02x", b));
          }

          return sb.toString();

         } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
         }

         return "";
        }
    
     public Boolean verifLogin (String email ,String  mdp)
     {
         String hashedMdp= doHashing(mdp); 
         String requete = "select * from login where email_login=? and mdp_login=?";
                 try {
            PreparedStatement ps = connection.prepareStatement(requete);
                     ps.setString(1, email);
                     ps.setString(2, hashedMdp);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                return true ;

            }
            else{
            return false;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return false;
        }
     }
     
     public String changePassword(String oldPassword , String newPassword , String email)
     {
         String hassedNewPassword = doHashing(newPassword);
         if (verifLogin(email,oldPassword))
         {
            String requete = "update login set mdp_login=? where email_login=?";
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                     ps.setString(1, hassedNewPassword);
                     ps.setString(2, email);
                     ps.executeUpdate();
                     return("updated");
                } catch (SQLException ex) {
                //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
                return("\"erreur lors de la mise à jour \" " + ex.getMessage());
                    
                }
         }
         else
         {
         return("Ancien mot de passe est incorrect");
         }
     }
     
     public void forgotPassword(String newPassword , String email)
     {
         String hassedNewPassword = doHashing(newPassword);
            String requete = "update login set mdp_login=? where email_login=?";
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                     ps.setString(1, hassedNewPassword);
                     ps.setString(2, email);
                     ps.executeUpdate();
                     System.out.println("Password Updated");
                } catch (SQLException ex) {
                //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\"erreur lors de la mise à jour \" " + ex.getMessage());
                    
                }
         }
     public void updateMail(Login l  , String email)
     {
            String requete = "update login set email_login=? where id_login=?";
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                     ps.setString(1, email);
                     ps.setInt(2, l.getId_login());
                     ps.executeUpdate();
                     System.out.println("Password Updated");
                } catch (SQLException ex) {
                //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\"erreur lors de la mise à jour \" " + ex.getMessage());
                    
                }
         }
     public void blockMail(String email,Boolean etat)
     {
            String requete = "update login set blocked_login=? where email_login=?";
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                     ps.setBoolean(1, etat);
                     ps.setString(2, email);
                     ps.executeUpdate();
                     System.out.println("Updated");
                } catch (SQLException ex) {
                //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\"erreur lors de la mise à jour \" " + ex.getMessage());
                    
                }
         }
         
}
