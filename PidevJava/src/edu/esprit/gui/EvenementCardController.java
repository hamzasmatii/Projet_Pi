/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;


import edu.esprit.entities.Evenement;
import edu.esprit.util.EvenementListner;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class EvenementCardController implements Initializable {
    @FXML
    private Label titreEvenement;
    @FXML
    private Text descriptionText;
    @FXML
    private Label dateLabel;
    @FXML
    private ImageView image;

    Evenement e;
    @FXML
    private HBox boxTest;
    @FXML
    private Label adresse;
    
    EvenementListner listner ;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(Evenement e,EvenementListner listner) {
        this.listner=listner;
        this.e=e;
       titreEvenement.setText(e.getTitre_evenement());
       this.descriptionText.setText(e.getDescription_evenement());
         Image eventImage;
            if(e.getImage()==null){
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
            }else {
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
            }
       image.setImage(eventImage);
       
       dateLabel.setText(e.getDate_evenement().toString());
        if(e.getDate_evenement().compareTo(new Date())<0) {
            dateLabel.getStyleClass().add("expired");
        } 
       adresse.setText(e.getAdresse_evenement());
       //boxTest.getChildren().remove(0, 2);
       
    }

    @FXML
    private void setSideBar(MouseEvent event) {
        
            if(listner!=null){
        listner.onClickListner(e);
            }
    }

   
    
    
}
