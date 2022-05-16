/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;


import edu.esprit.entities.Evenement;
import edu.esprit.util.EvenementListner;
import static edu.esprit.util.Statics.imageDirectory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

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

    Evenement evenement;
    @FXML
    private HBox boxTest;
    @FXML
    private Label adresse;
     @FXML
    private Button participerBTN;
    
    EvenementListner listner ;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(Evenement e,EvenementListner listner) throws IOException {
        this.listner=listner;
        this.evenement=e;
       titreEvenement.setText(evenement.getTitre_evenement());
       this.descriptionText.setText(evenement.getDescription_evenement());
         Image eventImage;
         if(evenement.getImage()==null){
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
           }else {
               File sourceimage = new File(imageDirectory+evenement.getImage());
                    Image image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
                    eventImage=image;
               
           }
       image.setImage(eventImage);
       
       dateLabel.setText(evenement.getDate_evenement().toString());
        if(evenement.getDate_evenement().compareTo(new Date())<0) {
            dateLabel.getStyleClass().add("expired");
        } 
       adresse.setText(evenement.getAdresse_evenement());
       //boxTest.getChildren().remove(0, 2);
       
    }

    @FXML
    private void setSideBar(MouseEvent event) {
            if(listner!=null){
        listner.onClickListner(evenement);
            }
    }

   
    
    
}
