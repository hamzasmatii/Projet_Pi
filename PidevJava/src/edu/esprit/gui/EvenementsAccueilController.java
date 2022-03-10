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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
 * @author Khaled
 */
public class EvenementsAccueilController implements Initializable {

    @FXML
    private GridPane grid;
    List<Evenement> evenements;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IevenementDAO edao=new EvenementDAO();
        evenements=edao.fetchPopularEvents();
        displayGrid(evenements);
    }    
    private void displayGrid(List<Evenement> evenements ){
           int column=1;
           int row=1;
        try {
        for(int i=0;i<evenements.size();i++){
          
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("evenementAccueilCard.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();   
            EvenementAccueilCardController cardController=fxmlLoader.getController();
            cardController.setData(evenements.get(i));   
            if (column == 4) {
                column = 0;
                row++;
                }
            grid.add(anchorPane,column, row);
            column++;
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            GridPane.setMargin(anchorPane, new Insets(10));
            }
            }
        catch (IOException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE,null, ex);
            }
    }

    
}
