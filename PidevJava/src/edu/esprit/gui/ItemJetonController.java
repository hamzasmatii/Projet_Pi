/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.gui.*;
import edu.esprit.dao.classes.JetonDAO;
import edu.esprit.dao.interfaces.IJetonDAO;

import edu.esprit.entities.Jeton;
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
import javafx.scene.layout.HBox;
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
    }    

    @FXML
    private void ModifIerJeton(ActionEvent event) {
        //switching scenes "modifier "
        
        try {
            
            FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ModifierJeton.fxml"));
            Parent root =  fxmlloader.load();
            ModifierJetonController jetoncontroller = fxmlloader.getController();
            jetoncontroller.setData(jeton);
             //root = FXMLLoader.load(getClass().getResource("../view/ModifierJeton.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierJetonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("../gui/JetonFXML.fxml"));
        Parent root =  fxmlloader.load();
        JetonFXMLController jetoncontroller = fxmlloader.getController();
        jetoncontroller.afficherJeton();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
	stage.show();
 
    }
    public void setData(Jeton jeton)
    {
        this.jeton = jeton;
        id_pack = jeton.getId_pack();
        descriptionjeton.setText(jeton.getDescription_pack());
        prixjeton.setText(String.valueOf(jeton.getPrix_pack()));
        quantitejeton.setText(String.valueOf(jeton.getQuantie_pack()));
        
        
        
    }

    @FXML
    private void Acheter(ActionEvent event) {
    }
    
}
