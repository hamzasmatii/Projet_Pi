/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.entities.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hrouz
 */
public class BlockUserController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableView;
    @FXML
    private TableColumn<Utilisateur, Integer> id_utilisateur;
    @FXML
    private TableColumn<Utilisateur, String> nom_utilisateur;
    @FXML
    private TableColumn<Utilisateur, Date> date_naissance_utilisateur;
    @FXML
    private TableColumn<Utilisateur, String> photo_utilisateur;
    @FXML
    private TableColumn<Utilisateur, Integer> type_utilisateur;
    @FXML
    private TableColumn<Utilisateur, Double> solde_utilisateur;
    @FXML
    private TableColumn<Utilisateur, String> email_utilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_utilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id_utilisateur"));
        id_utilisateur.setResizable(false);
        nom_utilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom_utilisateur"));
        nom_utilisateur.setResizable(false);
        date_naissance_utilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, Date>("date_naissance_utilisateur"));
        date_naissance_utilisateur.setResizable(false);
        photo_utilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("photo_utilisateur"));
        photo_utilisateur.setResizable(false);
        type_utilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("type_utilisateur"));
        type_utilisateur.setResizable(false);
        solde_utilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, Double>("solde_utilisateur"));
        solde_utilisateur.setResizable(false);
        email_utilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email_utilisateur"));
        email_utilisateur.setResizable(false);
        UtilisateurDAO us = new UtilisateurDAO();
        List<Utilisateur> listeutilisateurs = us.DisplayAllUtilisateurs();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
    }    
    
        

}
