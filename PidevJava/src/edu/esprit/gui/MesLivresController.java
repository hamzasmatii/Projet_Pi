/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.LivreDAO;
import edu.esprit.dao.interfaces.ILivre;
import edu.esprit.entities.Livre;
import edu.esprit.util.Statics;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class MesLivresController implements Initializable {

    @FXML
    private GridPane livre_utilisateur;
 private List<Livre> livres = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   livres=getData();
        int column = 0;
        int row = 1;
       // System.out.println(livres.size());
        try {
            for (int i = 0; i < livres.size(); i++) {
               // System.out.print(livres.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookContainer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BookContainerController itemController = fxmlLoader.getController();
                //System.out.print(livres.get(i)+"222222222222222222222222222222");
                itemController.setData(livres.get(i));
                

                if (column == 6) {
                    column = 0;
                    row++;
                }
                

                livre_utilisateur.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                livre_utilisateur.setMinWidth(Region.USE_COMPUTED_SIZE);
                livre_utilisateur.setPrefWidth(Region.USE_COMPUTED_SIZE);
                livre_utilisateur.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                livre_utilisateur.setMinHeight(Region.USE_COMPUTED_SIZE);
                livre_utilisateur.setPrefHeight(Region.USE_COMPUTED_SIZE);
                livre_utilisateur.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        // TODO
    }    
    private List<Livre> getData(){
        LivreDAO ldao=new LivreDAO();    
        return ldao.DisplayAllLivreByecrivain(Statics.currentUser);
        
        
        
    }
}
