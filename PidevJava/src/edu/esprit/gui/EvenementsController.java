/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXToggleButton;
import edu.esprit.dao.classes.EvenementDAO;
import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.util.EvenementListner;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private List<Evenement> allEvenements=new ArrayList();
    
       @FXML
    private TextField searchBox;

    @FXML
    private ScrollPane scrollEvenemets;

    @FXML
    private GridPane gridEvenements;   
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
    
    private EvenementListner listner;
    @FXML
    private ComboBox<?> typeList;
    @FXML
    private HBox topHbox;
    @FXML
    private Button ajoutButton;
    @FXML
    private JFXToggleButton sortToggle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //topHbox.getChildren().remove(7,8);
       
        this.allEvenements=fechEvenement();
        try {
            setChosenEvenement(this.allEvenements.get(0));
        } catch (IOException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       listner= (Evenement e) -> {
           try {
               setChosenEvenement(e);
           } catch (IOException ex) {
               Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
           }
        };
            List<Evenement> newList=this.sortByDate();
            this.displayGrid(newList);
    }

public List<Evenement> fechEvenement(){
    IevenementDAO edao=new EvenementDAO();
    
    return edao.fetchEvenement();
    
}
private void setChosenEvenement(Evenement evenement) throws IOException {
       
       titreText.setText(evenement.getTitre_evenement());
       descriptionText.setText(evenement.getDescription_evenement());
       dateText.setText(evenement.getDate_evenement().toString());
       lieuText.setText(evenement.getAdresse_evenement());
       Image eventImage;
            if(evenement.getImage()==null){
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
            }else {
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
            }
        imageView.setImage(eventImage);
       } 

    @FXML
    private void ajoutEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutEvenement.fxml"));
        Pane orderView = loader.load();
        BorderPane homePane = (BorderPane) ajoutButton.getScene().getRoot();
        homePane.setCenter(orderView);
    }
    
    private List<Evenement> sortByDate(){
 List<Evenement> sorted=this.allEvenements.stream().
          sorted((e1,e2)->e2.getDate_evenement().compareTo(e1.getDate_evenement()))
         .collect(Collectors.toList());
 
         return sorted;
        
        
    }
    
    private void displayGrid(List<Evenement> evenements ){
            int column=0;
            int row=1;
        try {
        for(int i=0;i<evenements.size();i++){
            listner.onClickListner(evenements.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("evenementCard.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
            EvenementCardController cardController=fxmlLoader.getController();
            cardController.setData(evenements.get(i),listner);
             
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

    @FXML
    private void handleToggle(ActionEvent event) {
        if(sortToggle.isSelected()){
           List<Evenement> sortedGrid=this.sortByDate();
           this.gridEvenements.getChildren().clear();
           this.displayGrid(sortedGrid);
        }else{
            this.gridEvenements.getChildren().clear();
            this.displayGrid(this.allEvenements);
        }
    }

   

    @FXML
    private void search(KeyEvent event) {
        String word=searchBox.getText();
      List<Evenement> searchList=this.allEvenements.stream().
              filter((e)->{return e.getTitre_evenement().toUpperCase().startsWith(word.toUpperCase());})
              .collect(Collectors.toList());
      this.gridEvenements.getChildren().clear();
      this.displayGrid(searchList);
    }
    
    
    
}
