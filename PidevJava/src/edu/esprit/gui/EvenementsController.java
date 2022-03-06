/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.EvenementDAO;
import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class EvenementsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Evenement> evenements=new ArrayList();
       @FXML
    private TextField searchBox;

    @FXML
    private ScrollPane scrollEvenemets;

    @FXML
    private GridPane gridEvenements;   
    @FXML
    private VBox sideCard;
    @FXML
    private Button participerBTN;
    @FXML
    private ImageView imageView;
    @FXML
    private Text titreText;
    @FXML
    private Text dateText;
    @FXML
    private Text descriptionText;
    @FXML
    private Text lieuText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        int column=0;
        int row=1;
        this.evenements=fechEvenement();
        
        try {
            setChosenEvenement(this.evenements.get(0));
        } catch (IOException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("In evenements");
        try {
        for(int i=0;i<evenements.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("evenementCard.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
            EvenementCardController cardController=fxmlLoader.getController();
            cardController.setData(evenements.get(i));
             if (column == 1) {
                    column = 0;
                    row++;
                }
            
            gridEvenements.add(anchorPane,column++, row);
            
            gridEvenements.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEvenements.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEvenements.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEvenements.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEvenements.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridEvenements.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
            }
        catch (IOException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }

public List<Evenement> fechEvenement(){
    IevenementDAO edao=new EvenementDAO();
    
    return edao.fetchEvenement();
    
}
   private void setChosenEvenement(Evenement evenement) throws IOException {
       
       titreText.setText(evenement.getTitre_evenement());
        
        
    
   
   
   } 
    
    
}
