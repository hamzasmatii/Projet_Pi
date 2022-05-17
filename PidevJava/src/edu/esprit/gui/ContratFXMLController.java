/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.ContratDAO;
import edu.esprit.dao.classes.MaisonDAO;
import edu.esprit.dao.interfaces.IContrat;
import edu.esprit.dao.interfaces.IMaison;
import edu.esprit.entities.Contrat;
import edu.esprit.entities.Maison;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Haythem
 */
public class ContratFXMLController implements Initializable {

    @FXML
    private Pane pnlJeton;
    @FXML
    private TextField serachbox;
    @FXML
    private VBox boxJeton;
    @FXML
    private TextField nom1;
    @FXML
    private ChoiceBox<?> nom;
    @FXML
    private Spinner<?> duree;
    @FXML
    private Button ajouter;
   
    private Contrat contrat;
    private int id_maison;
    
private int id_contrat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void AjouterJeton(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
         if (nom1.getText() == null) 
            {
                alert.setContentText("Saisir la discription ");
                alert.show();
                
            }
        if (duree.getValue() == null) 
            {
                alert.setContentText("Saisir le Date ");
                alert.show();
                
            }
//         else if (nom.getText() == null || nom.getText().trim().isEmpty()) 
//            {
//                alert.setContentText("Saisir le nom");
//                alert.show();
//            }
        else {
            id_maison = contrat.getId_maison_edition();
                Contrat contrat = new Contrat(id_maison,1,1,5,nom1.getText());    
                IContrat condao= ContratDAO.getInstance();
                condao.insertContrat(contrat);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success");
                alert2.setContentText("contrat is added successfully!");
                alert2.show();
                
                nom1.setText("");
                 afficherRecom();
                 
                 
      }
    }
    public void set(Contrat contrat)
    {
        this.contrat = contrat;
        id_contrat = contrat.getId_contrat();

    }
    public void afficherRecom (){
    
    boxJeton.getChildren().removeAll(boxJeton.getChildren());
     
        
        List<Contrat> contrat=new ArrayList<Contrat>();
         
        IContrat condao=ContratDAO.getInstance();
       contrat=condao.DisplayAllContrat();
        
        
        try {
        for (Contrat recompense : contrat) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemContrat.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemContratController itemrecompensController = fxmlloader.getController();
                 System.out.println(itemrecompensController);
                 itemrecompensController.setData(recompense);
                 
                 boxJeton.getChildren().add(anchorpane);
                 
                 
//                 if(column==3)
//                 {
//                     column = 0;
//                     row++;
//                 }
//                 grid.add(anchorpane, column++, row);
//                 GridPane.setMargin(anchorpane, new Insets(10));
                 

        }
        } catch (IOException ex) {
                 Logger.getLogger(ContratFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
}
}
