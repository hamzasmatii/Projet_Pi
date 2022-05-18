/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.gui.*;
import edu.esprit.dao.classes.JetonDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.dao.interfaces.IJetonDAO;

import edu.esprit.entities.Jeton;
import edu.esprit.entities.Utilisateur;
import edu.esprit.util.Statics;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class ItemJetonController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label descriptionjeton;
    @FXML
    private Label prixjeton;
    @FXML
    private Label quantitejeton;
    
    private Jeton jeton;
    private int id_pack;
    private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button suprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button acheter;
   
    //private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Statics.currentUser.getType_utilisateur()!=4)
        {
            suprimer.setVisible(false);
            modifier.setVisible(false);
        }
        else
        {
            acheter.setVisible(false);
        }
    }    

    @FXML
    private void ModifIerJeton(ActionEvent event) throws IOException {
        //switching scenes "modifier "
        
        try {
            
            FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ModifierJeton.fxml"));
            Parent root1 =  (Parent) fxmlloader.load();
            ModifierJetonController jetoncontroller = fxmlloader.getController();
            jetoncontroller.setData(jeton);
             //root = FXMLLoader.load(getClass().getResource("../view/ModifierJeton.fxml"));
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root1));  
            stage2.show();
//            Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierJetonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/JetonFXML.fxml"));
        Pane pnlJeton = loader.load();
        BorderPane homePane = (BorderPane) modifier.getScene().getRoot();
        homePane.setCenter(pnlJeton);
        
    }

    @FXML
    private void SupprimerJeton(ActionEvent event ) throws IOException {
        IJetonDAO jetondao = JetonDAO.getInstance();
//        jetondao.insertJeton(jeton);
        jetondao.deleteJeton(jeton.getId_pack());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Jeton is added successfully!");
        alert.show();
        
        //actualiser
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/JetonFXML.fxml"));
        Pane pnlJeton = loader.load();
        BorderPane homePane = (BorderPane) suprimer.getScene().getRoot();
        homePane.setCenter(pnlJeton);
 
    }
    public void setData(Jeton jeton)
    {
        this.jeton = jeton;
        id_pack = jeton.getId_pack();
        descriptionjeton.setText(jeton.getDescription_pack());
        prixjeton.setText(String.valueOf(jeton.getPrix_pack()));
        quantitejeton.setText(String.valueOf(jeton.getQuantie_pack()));
        
        
        
    }
    public void actualise() throws IOException
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/JetonFXML.fxml"));
        Pane pnlJeton = loader.load();
        BorderPane homePane = (BorderPane) modifier.getScene().getRoot();
        homePane.setCenter(pnlJeton);
    }
     

    @FXML
    private void Acheter(ActionEvent event) {
        int quantite = jeton.getQuantie_pack();
        int solde = Statics.currentUser.getSolde_utilisateur();
        Utilisateur tempuser= Statics.currentUser; 
        tempuser.setSolde_utilisateur(solde+quantite);
        UtilisateurDAO us = new UtilisateurDAO();
        us.updateUtilisateur(tempuser);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success");
                alert2.setContentText("Achat effectuée");
                alert2.show();
    }
    
}
