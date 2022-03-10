/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.LoginDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Login;
import edu.esprit.entities.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private TableColumn<Login, String> blocked_message;
    @FXML
    private TableColumn<Login, Date> blocked_duree;
    @FXML
    private Button unblockBtn;
    @FXML
    private Button blockBtn;
    @FXML
    private Label nomLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private TextArea blocked_messageInput;
    @FXML
    private Button saveBtn;
    private int selectedid;
    @FXML
    private Button jour1Btn;
    @FXML
    private Button jour3Btn;
    @FXML
    private Button jour7Btn;
    @FXML
    private Button jour14Btn;
    @FXML
    private Label dureeText;
    @FXML
    private Label dureeInput;
    @FXML
    private TextField searchInput;
List<Login> listeutilisateurs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        disableInputs();
        id_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getId_utilisateur())));

        nom_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getNom_utilisateur())));

        date_naissance_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getDate_naissance_utilisateur())));

        photo_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getPhoto_utilisateur())));

        type_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(getType(cellData.getValue().getUtilisateur().getType_utilisateur()))));

        solde_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getSolde_utilisateur())));

        email_utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getEmail_utilisateur())));
        
        blocked_login.setCellValueFactory(new PropertyValueFactory<Login, Boolean>("blocked_login"));
        blocked_message.setCellValueFactory(new PropertyValueFactory<Login, String>("blocked_message"));

        blocked_duree.setCellValueFactory(new PropertyValueFactory<Login, Date>("blocked_duree"));

        LoginDAO ls = new LoginDAO();
         listeutilisateurs = ls.DisplayAllLogins();
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
        {
            ls.updateBlockMessage(selectedid, "");
            disableInputs();
        ls.updateBlockDuree(selectedid,null);
        List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
        }
        
        
    }

    @FXML
    private void blockUser(ActionEvent event) {
        LoginDAO ls = new LoginDAO();
        Boolean test =ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), true);
        if(test)
        {
            ls.updateBlockMessage(selectedid, blocked_messageInput.getText());
            disableInputs();
        List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
        }
        
    }

    @FXML
    private void selectRow(MouseEvent event) {
        disableInputs();
        selectedid=tableView.getSelectionModel().getSelectedItem().getUtilisateur().getId_utilisateur();
        saveBtn.setDisable(false);
        nomLabel.setVisible(true);
        nomLabel.setText(tableView.getSelectionModel().getSelectedItem().getUtilisateur().getNom_utilisateur());
        mailLabel.setVisible(true);
        mailLabel.setText(tableView.getSelectionModel().getSelectedItem().getUtilisateur().getEmail_utilisateur());
        jour1Btn.setDisable(false);
        jour3Btn.setDisable(false);
        jour7Btn.setDisable(false);
        jour14Btn.setDisable(false);
        blocked_messageInput.setText(tableView.getSelectionModel().getSelectedItem().getBlocked_message());
        
        
        
        if(tableView.getSelectionModel().getSelectedItem().getBlocked_login())
        {
            System.out.println(tableView.getSelectionModel().getSelectedItem().getBlocked_duree()!=null);
            if(tableView.getSelectionModel().getSelectedItem().getBlocked_duree()!=null)
            {
                dureeInput.setVisible(true);
                dureeText.setVisible(true);
                dureeInput.setText(tableView.getSelectionModel().getSelectedItem().getBlocked_duree().toString());
                blockBtn.setDisable(true);
                unblockBtn.setDisable(false);
            
            }
            else{
                dureeInput.setVisible(true);
            dureeInput.setText("Bloqué définitivement");
            blockBtn.setDisable(true);
            unblockBtn.setDisable(false);
            }
        }
        else{
        blockBtn.setDisable(false);
        unblockBtn.setDisable(true);
        }
        
        
    }

    @FXML
    private void chageBlockedMessage(ActionEvent event) {
        System.out.println(selectedid);
        LoginDAO ls = new LoginDAO();
        Boolean test=ls.updateBlockMessage(selectedid, blocked_messageInput.getText());
        if(test)
        {
        ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), true);
        List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
        disableInputs();
        
        }
        
    }
    
    @FXML
    private void jour1Block(ActionEvent event) {
        System.out.println(new java.sql.Date(System.currentTimeMillis()+ 24*60*60*1000));
        LoginDAO ls = new LoginDAO();
        Boolean test= ls.updateBlockDuree(selectedid, new java.sql.Date(System.currentTimeMillis()+ 24*60*60*1000));
        if(test)
        {
            ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), true);
            ls.updateBlockMessage(selectedid, blocked_messageInput.getText());
            List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
            disableInputs();
           
        }
   
    }

    @FXML
    private void jour3Block(ActionEvent event) {
        System.out.println(new java.sql.Date(System.currentTimeMillis()+ 3*24*60*60*1000));
        LoginDAO ls = new LoginDAO();
        Boolean test= ls.updateBlockDuree(selectedid, new java.sql.Date(System.currentTimeMillis()+ 3*24*60*60*1000));
        if(test)
        {
            ls.updateBlockMessage(selectedid, blocked_messageInput.getText());
            ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), true);
            List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
            disableInputs();
        }
    }

    @FXML
    private void jour7Block(ActionEvent event) {
        System.out.println(new java.sql.Date(System.currentTimeMillis()+ 7*24*60*60*1000));
        LoginDAO ls = new LoginDAO();
        Boolean test= ls.updateBlockDuree(selectedid, new java.sql.Date(System.currentTimeMillis()+ 7*24*60*60*1000));
        if(test)
        {
            ls.updateBlockMessage(selectedid, blocked_messageInput.getText());
            ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), true);
            List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
            disableInputs();
        }
    }

    @FXML
    private void jour14Block(ActionEvent event) {
        System.out.println(new java.sql.Date(System.currentTimeMillis()+ 14*24*60*60*1000));
        LoginDAO ls = new LoginDAO();
        Boolean test= ls.updateBlockDuree(selectedid, new java.sql.Date(System.currentTimeMillis()+ 14*24*60*60*1000));
        if(test)
        {
            ls.updateBlockMessage(selectedid, blocked_messageInput.getText());
            ls.blockMail(tableView.getSelectionModel().getSelectedItem().getEmail_login(), true);
            List<Login> listeutilisateurs = ls.DisplayAllLogins();
        tableView.setItems(FXCollections.observableList(listeutilisateurs));
            disableInputs();
        }
    }
    
    private void disableInputs()
    {
        blockBtn.setDisable(true);
        unblockBtn.setDisable(true);
        nomLabel.setVisible(false);
        mailLabel.setVisible(false);
        saveBtn.setDisable(true);
        blocked_messageInput.setText(null);
        jour1Btn.setDisable(true);
        jour3Btn.setDisable(true);
        jour7Btn.setDisable(true);
        jour14Btn.setDisable(true);
        dureeInput.setVisible(false);
        dureeInput.setText("");
        dureeText.setVisible(false);
    }
    @FXML
    private void search(KeyEvent event) {
      String word=searchInput.getText();
      List<Login> searchList=listeutilisateurs.stream()
                .filter((e)->{return e.getUtilisateur().getNom_utilisateur().toUpperCase().startsWith(word.toUpperCase());})
                .collect(Collectors.toList());
      tableView.setItems(FXCollections.observableList(searchList));
    }

}
