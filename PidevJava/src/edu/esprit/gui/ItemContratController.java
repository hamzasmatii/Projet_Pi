/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.ContratDAO;
import edu.esprit.entities.Contrat;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haythem
 */
public class ItemContratController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private ImageView imageview;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label prix;
private Contrat contrat;
private int id_contrat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimerJeton(ActionEvent event) {
        ContratDAO cs =new ContratDAO();
        cs.deleteContrat(id_contrat);
    }

    @FXML
    private void ModifIerJeton(ActionEvent event) {
       
    }
    public void setData(Contrat contrat)
    {
    this.contrat = contrat;
        id_contrat = contrat.getId_contrat();
//        File file = new File("src/image/"+maison.getPhoto_maison_edition());
//        System.out.println(file.toURI().toString());
//        Image image = new Image(file.toURI().toString());
//        imageview.setImage(image);
        description.setText(String.valueOf(contrat.getDuree_contrat()));
        
        nom.setText("haythem");
        prix.setText(contrat.getDescription_contrat());
            
    }
}
