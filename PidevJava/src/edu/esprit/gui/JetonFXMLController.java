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
import edu.esprit.util.Statics;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class JetonFXMLController implements Initializable {

    @FXML
    private Pane pnlJeton;
    
    
     @FXML
    private VBox  boxJeton;
    @FXML
    private TextField lbdescrip;
    @FXML
    private Spinner<Integer> spquantite;
    @FXML
    private Spinner<Integer> spprix;
    
     private JetonFXMLController controller;
    @FXML
    private TextField serachbox;
     private List<Jeton> alljetons=new ArrayList();
    @FXML
    private HBox box;
    @FXML
    private Button ajouter;
     
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherJeton();
       
        
    }    

    @FXML
    private void AjouterJeton(ActionEvent event) {
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
                Jeton jeton = new Jeton(spquantite.getValue(),spprix.getValue(),lbdescrip.getText());    
                IJetonDAO jetondao = JetonDAO.getInstance();
        //        jetondao.insertJeton(jeton);
                jetondao.insertJeton(jeton);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success");
                alert2.setContentText("Ajout effectuée");
                alert2.show();
                lbdescrip.setText("");
                 afficherJeton();
        }
    }
   
public void setController(JetonFXMLController controller){
    this.controller = controller;
}
public void afficherJeton (){
    
    boxJeton.getChildren().removeAll(boxJeton.getChildren());
     SpinnerValueFactory<Integer> valueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spquantite.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory2 = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spprix.setValueFactory(valueFactory2);
        
        List<Jeton> jetonl=new ArrayList<Jeton>();
         
        IJetonDAO jetondao=JetonDAO.getInstance();
        jetonl=jetondao.DisplayAllJeton();
        System.out.println(jetonl);
        
        try {
        for (Jeton jeton : jetonl ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemJeton.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemJetonController itemJetoncontroller = fxmlloader.getController();
                 System.out.println(itemJetoncontroller);
                 itemJetoncontroller.setData(jeton);
                 
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
                 Logger.getLogger(JetonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
        if(Statics.currentUser.getType_utilisateur()!=4)
        {
            box.setVisible(false);
            ajouter.setVisible(false);
            
        }
}

    @FXML
    private void search(KeyEvent event) {
        
        String word=serachbox.getText();
        if (word!= "")
        {
        boxJeton.getChildren().removeAll(boxJeton.getChildren());
      List<Jeton> jetonl=new ArrayList<Jeton>();
       IJetonDAO jetondao=JetonDAO.getInstance();
        jetonl=jetondao.DisplayAllJeton();


        List<Jeton> filteredJeton =  jetonl.stream().
                        filter(p->p.getPrix_pack()== Integer.parseInt(word))
              //filter((e)->{return e.getDescription_pack().toUpperCase().startsWith(word.toUpperCase());})
              .collect(Collectors.toList());

try {
        for (Jeton jeton : filteredJeton ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemJeton.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemJetonController itemJetoncontroller = fxmlloader.getController();
                 System.out.println(itemJetoncontroller);
                 itemJetoncontroller.setData(jeton);
                 
                 boxJeton.getChildren().add(anchorpane);
                
                 

        }
        } catch (IOException ex) {
                 Logger.getLogger(JetonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
//      List<Jeton> searchList=this.alljetons.stream().
//              filter((e)->{return e.getDescription_pack().toUpperCase().startsWith(word.toUpperCase());})
//              .collect(Collectors.toList());
      
      
        



    }

    @FXML
    private void TrieDecroiss(ActionEvent event) {
         boxJeton.getChildren().removeAll(boxJeton.getChildren());
         SpinnerValueFactory<Integer> valueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spquantite.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory2 = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spprix.setValueFactory(valueFactory2);
               List<Jeton> jeton2=new ArrayList<Jeton>();
       IJetonDAO jetondao=JetonDAO.getInstance();
        jeton2=jetondao.DisplayAllJeton();
        
                List<Jeton> filteredJeton = jeton2.stream()
                .sorted(Comparator.comparing(Jeton::getPrix_pack).reversed())
               .collect(Collectors.toList());

try {
        for (Jeton jeton : filteredJeton ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemJeton.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemJetonController itemJetoncontroller = fxmlloader.getController();
                 System.out.println(itemJetoncontroller);
                 itemJetoncontroller.setData(jeton);
                 
                 boxJeton.getChildren().add(anchorpane);
                
                 

        }
        } catch (IOException ex) {
                 Logger.getLogger(JetonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
        

    }

}
