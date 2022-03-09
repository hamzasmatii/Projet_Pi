/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.CommentaireLivre;
import edu.esprit.entities.Livre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class CommentairelivreController implements Initializable {

    @FXML
    private Label commentairelivre;
    @FXML
    private Label nom_utilisateur;
private CommentaireLivre c;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setDatacom(CommentaireLivre com) {
        this.c= com;
        commentairelivre.setText(c.getContenu_commentaire());
        nom_utilisateur.setText(com.getUtilisateur().getNom_utilisateur()+" :");
       // nom_utilisateur.setText(c.());
        
        
        
        
        
    }
}
