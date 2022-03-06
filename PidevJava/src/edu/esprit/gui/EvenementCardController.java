/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;


import edu.esprit.entities.Evenement;
import edu.esprit.util.evenementListner;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Button participerBtn;
    @FXML
    private Text descriptionText;
    @FXML
    private Label dateLabel;
    Evenement e;
    private evenementListner listner;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(Evenement e) {
        this.e=e;
       titreEvenement.setText(e.getTitre_evenement());
       
    }

    @FXML
    private void setSideBar(MouseEvent event) {
         listner.onClickListener(e);
    }
    
    
}
