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
import edu.esprit.util.MyDB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hrouz
 */
public class LoginDAO implements ILogin {

    Connection connexion;
    Statement stm;

    public LoginDAO() {
        connexion = MyDB.getInstance().getConnexion();
    }

    public void insertLogin(Login l) {
        String req = "INSERT INTO `login` (`id_login`,`email_login`, `mdp_login`) "
                + "VALUES (?,?,?) ";
        try {
            PreparedStatement ls = connexion.prepareStatement(req);
            ls.setInt(1, l.getId_login());
            ls.setString(2, l.getEmail_login());
            ls.setString(3, l.getMdp_login());
            ls.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLogin(Login l) {
        String req = "update login set email_login=? , mdp_login=? where id_login=?";
        try {
            PreparedStatement ls = connexion.prepareStatement(req);
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
            PreparedStatement ls = connexion.prepareStatement(requete);
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
            PreparedStatement ps = connexion.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                login.setId_login(resultat.getInt(1));
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
            Statement statement = connexion
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
}
