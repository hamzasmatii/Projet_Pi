/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.gui.*;
import edu.esprit.dao.classes.JetonDAO;
import edu.esprit.dao.interfaces.IJetonDAO;
import edu.esprit.entities.Jeton;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static jdk.nashorn.internal.objects.NativeRegExp.source;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class ModifierJetonController implements Initializable {

    @FXML
    private TextField lbdescrip;
    @FXML
    private Spinner<Integer> spquantite;
    @FXML
    private Spinner<Integer> spprix;
    
     private JetonFXMLController controller;
      private Jeton jeton;
         
        private Stage stage;
    @FXML
    private AnchorPane panemod;
    
    private Scene scene;
    @FXML
    private Button confirmer;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        id_pack=jeton.getId_pack();
        afficher();
         
                
    }    

    @FXML
    private void ConfirmerJeton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (spprix.getValue() == null) 
            {
                alert.setContentText("Saisir le prix ");
                alert.show();
                
            }
        else if (spquantite.getValue() == null) 
            {
                alert.setContentText("Saisir la quantite");
                alert.show();
                
            }
        else if ("".equals(lbdescrip.getText())) 
            {
                alert.setContentText("Saisir la description");
                alert.show();
                
            }
        else {
        int id = jeton.getId_pack();
         Jeton jeton = new Jeton(id,spquantite.getValue(),spprix.getValue(),lbdescrip.getText());    
        IJetonDAO jetondao = JetonDAO.getInstance();
//        jetondao.insertJeton(jeton);
        jetondao.updateJeton(jeton);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Success");
        alert2.setContentText("Jeton is updated successfully!");
             
//       BorderPane homePane = (BorderPane) confirmer.getScene().getRoot();
    //    homePane.setCenter(node);
        
        
     
            
        //alert.show();
         Optional<ButtonType> res = alert2.showAndWait();
         if(res.isPresent()) {
                if(res.get().equals(ButtonType.OK))
                {
              
                     
                         stage = (Stage) panemod.getScene().getWindow();
                            stage.close();
                            //actualiser new window
                       
                }
        }

         }
        
    }
    
    void afficher(){
    SpinnerValueFactory<Integer> valueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spquantite.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory2 = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spprix.setValueFactory(valueFactory2);
    }
     public void setData(Jeton jeton)
    {
         this.jeton = jeton;
         spquantite.getValueFactory().setValue(jeton.getQuantie_pack());
         spprix.getValueFactory().setValue(jeton.getPrix_pack());
        lbdescrip.setText(jeton.getDescription_pack());
        
        
        
        
    }

    @FXML
    private void search(KeyEvent event) {
    }
    
}
