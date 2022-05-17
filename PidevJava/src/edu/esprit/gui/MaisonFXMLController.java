/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.MaisonDAO;
import edu.esprit.dao.interfaces.IMaison;
import edu.esprit.entities.Maison;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haythem
 */
public class MaisonFXMLController implements Initializable {

    @FXML
    private Pane pnlJeton;
    @FXML
    private TextField serachbox;
    @FXML
    private VBox boxJeton;
    @FXML
    private TextField nom;
    @FXML
    private TextField lbdescrip;
    @FXML
    private TextField lbdescrip1;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField imagePath;
    private Maison maison ;
    private MaisonFXMLController controller;
    private String imageName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherRecom();
    }    

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void uploadImage(ActionEvent event) throws IOException {
        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)pnlJeton.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) { 
            Image img = new Image(file.toURI().toString());
             imagePath.setText(file.toString());
             File directory = new File("/src/image");
             String destination = directory.toString();
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                File sourceFile=null;
                File destinationFile = null;
                imageName = file.toString().substring(file.toString().lastIndexOf('\\')+1);
                sourceFile = new File(file.toString());
                destinationFile = new File( "src/image/"+imageName);
                if(!destinationFile.exists())
                {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                }
                imageView.setImage(img);
            
         }
    }

    @FXML
    private void AjouterJeton(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
         if (lbdescrip.getText() == null || lbdescrip.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir l'adresse");
                alert.show();
                
            }
        else if (lbdescrip1.getText() == null || lbdescrip1.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir la description");
                alert.show();
                
            }
         else if (nom.getText() == null || nom.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir le nom");
                alert.show();
            }
        else {
                Maison maison = new Maison(1,lbdescrip.getText(),imageName,lbdescrip1.getText(),nom.getText());    
                IMaison maidao= MaisonDAO.getInstance();
                maidao.insertMaison(maison);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success");
                alert2.setContentText("maison is added successfully!");
                alert2.show();
                lbdescrip.setText("");
                nom.setText("");
                 afficherRecom();
                 
                 
      }
    }

    @FXML
    private void TrieCroiss(ActionEvent event) {
    }

    @FXML
    private void Statis(ActionEvent event) {
    }
    
     public void afficherRecom (){
    
    boxJeton.getChildren().removeAll(boxJeton.getChildren());
     
        
        List<Maison> maison=new ArrayList<Maison>();
         
        IMaison maidao=MaisonDAO.getInstance();
       maison=maidao.DisplayAllRecompense();
        
        
        try {
        for (Maison recompense : maison ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemMaison.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemMaisonController itemrecompensController = fxmlloader.getController();
                 System.out.println(itemrecompensController);
                 itemrecompensController.setData(recompense);
                 
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
                 Logger.getLogger(MaisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
}
    public void setController(MaisonFXMLController controller){
    this.controller = controller;
}
}
