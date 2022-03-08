/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.LoginDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.entities.Login;
import edu.esprit.entities.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hrouz
 */
public class BlockUserController implements Initializable {
    
    
    @FXML
    private TableView<Login> tableView;
    @FXML
    private TableColumn<Login, String> id_utilisateur;
    @FXML
    private TableColumn<Login, String> nom_utilisateur;
    @FXML
    private TableColumn<Login, String> date_naissance_utilisateur;
    @FXML
    private TableColumn<Login, String> photo_utilisateur;
    @FXML
    private TableColumn<Login, String> type_utilisateur;
    @FXML
    private TableColumn<Login, String> solde_utilisateur;
    @FXML
    private TableColumn<Login, String> email_utilisateur;
    @FXML
    private TableColumn<Login, Boolean> blocked_login;
    @FXML
    private Button unblockBtn;
    @FXML
    private Button blockBtn;
    @FXML
    private Label nomLabel;
    @FXML
    private Label mailLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        blockBtn.setDisable(true);
        unblockBtn.setDisable(true);
        nomLabel.setVisible(false);
        mailLabel.setVisible(false);
        id_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getId_utilisateur())));

        nom_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getNom_utilisateur())));

        date_naissance_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getDate_naissance_utilisateur())));

        photo_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getPhoto_utilisateur())));

        type_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(getType(cellData.getValue().getUtilisateur().getType_utilisateur()))));

        solde_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getSolde_utilisateur())));

        email_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getEmail_utilisateur())));
        
        blocked_login.setCellValueFactory(new PropertyValueFactory<Login, Boolean>("blocked_login"));

        LoginDAO ls = new LoginDAO();
        List<Login> listeutilisateurs = ls.DisplayAllLogins();
        System.out.println(listeutilisateurs);
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
    }    

       private Object getType(int i) {
                    switch(i){
                           case 1: 
                               return String.valueOf("Utilisateur");
                                        
                         case 2:
                            return String.valueOf("Ecrivain");                                      
                           case 3:
                            return  String.valueOf("Admin Maison Edition");                                        
                              default:
                              return String.valueOf("Admin");
                                       
                                  }    
                                            }
                            
                        
                            
    @FXML
    private void unblockUser(ActionEvent event) {
        
        LoginDAO ls = new LoginDAO();
        Boolean test =ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), false);
        if(test)
        {blockBtn.setDisable(true);
        unblockBtn.setDisable(true);
        nomLabel.setVisible(false);
        mailLabel.setVisible(false);
        List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
        }
        
        
    }

    @FXML
    private void blockUser(ActionEvent event) {
        LoginDAO ls = new LoginDAO();
        Boolean test =ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), true);
        if(test)
        {blockBtn.setDisable(true);
        unblockBtn.setDisable(true);
        nomLabel.setVisible(false);
        mailLabel.setVisible(false);
        List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
        }
        
    }

    @FXML
    private void selectRow(MouseEvent event) {
        nomLabel.setVisible(true);
        nomLabel.setText(tableView.getSelectionModel().getSelectedItem().getUtilisateur().getNom_utilisateur());
        mailLabel.setVisible(true);
        mailLabel.setText(tableView.getSelectionModel().getSelectedItem().getUtilisateur().getEmail_utilisateur());
        if(tableView.getSelectionModel().getSelectedItem().getBlocked_login())
        {blockBtn.setDisable(true);
        unblockBtn.setDisable(false);
        }
        else{
        blockBtn.setDisable(false);
        unblockBtn.setDisable(true);
        }
    }
    
        

}
