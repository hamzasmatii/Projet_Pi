/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.CategorieLivreDAO;
import edu.esprit.dao.classes.LivreDAO;
import edu.esprit.entities.CategorieLivre;
import edu.esprit.entities.Livre;
import edu.esprit.util.MyConnection;
import edu.esprit.util.Statics;
import static edu.esprit.util.Statics.imageDirectory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class AjoutlivrecrivainController implements Initializable {

    @FXML
    private TextField titre_livreInput;
    @FXML
    private TextField photo_livreInput;
    @FXML
    private TextField contenu_livreInput;
    @FXML
    private TextField prix_livreInput;
    @FXML
    private TextArea description_livreInput;
    @FXML
    private ComboBox<CategorieLivre> categorie_livreInput;
    @FXML
    private AnchorPane anchor;
    String imageName;
    String pdfName;
    @FXML
    private Button uploadImage;
    @FXML
    private Button uploadPdf;
     
    

      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategorieLivreDAO cls= new CategorieLivreDAO();
        List<CategorieLivre> listeCategorieLivre = cls.DisplayAllCategorieLivre();
     
        //System.out.println(listeCategorieLivre);
        categorie_livreInput.setItems(FXCollections.observableList(listeCategorieLivre));
        categorie_livreInput.setConverter(new StringConverter<CategorieLivre>()  {
            public String toString(CategorieLivre object) {
                return object.getLibelle();
            }

            
            public CategorieLivre fromString(String string) {
                return null;
            }});
    }    
   
    @FXML
    private void ajoutLivre(ActionEvent event) throws IOException {
        
         //LocalDate date = LocalDate.of(2020, 1, 8);
         if (titre_livreInput.getText() == null || titre_livreInput.getText().trim().isEmpty())
            {
                errorAlert("Le champ titre est vide!");
                return;
            }
        
        if (description_livreInput.getText() == null || description_livreInput.getText().trim().isEmpty())
            {
                errorAlert("Le champ description est vide!");
                return;
            }
        if (prix_livreInput.getText() == null || prix_livreInput.getText().trim().isEmpty())
            {
               errorAlert("Le champ prix est vide!");
               return;
            }
        if (contenu_livreInput.getText() == null || contenu_livreInput.getText().trim().isEmpty())
            {
               errorAlert("Le champ contenu livre est vide!");
               return;
            }
        if (photo_livreInput.getText() == null || photo_livreInput.getText().trim().isEmpty())
            {
               errorAlert("Le champ photo livre est vide!");
               return;
            }
       
         LocalDate date=java.time.LocalDate.now();
        Livre templ = new Livre(titre_livreInput.getText(),description_livreInput.getText(),java.sql.Date.valueOf(date),imageName,pdfName,Integer.parseInt(prix_livreInput.getText()),0,Statics.currentUser.getId_utilisateur(),categorie_livreInput.getValue().getId_categorie_livre());
        LivreDAO ls = new LivreDAO();
        ls.insertLivre(templ);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeLivre.fxml"));
        Pane orderView = loader.load();
        BorderPane homePane = (BorderPane) uploadPdf.getScene().getRoot();
        homePane.setCenter(orderView);
        
    }
    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) { 
             this.photo_livreInput.setText(file.toString());
             File directory = new File(imageDirectory+"livre\\");
             String destination = directory.toString();
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                File sourceFile=null;
                File destinationFile = null;
                imageName = file.toString().substring(file.toString().lastIndexOf('\\')+1);
                sourceFile = new File(file.toString());
                destinationFile = new File( imageDirectory+"livre\\"+imageName);
                if(!destinationFile.exists())
                {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                }
            
         }
    } 
    @FXML
private void uploadPDF(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) { 
             this.contenu_livreInput.setText(file.toString());
             File directory = new File("/src/pdf");
             String destination = directory.toString();
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                File sourceFile=null;
                File destinationFile = null;
                pdfName = file.toString().substring(file.toString().lastIndexOf('\\')+1);
                sourceFile = new File(file.toString());
                destinationFile = new File( "src/pdf/livre"+pdfName);
                if(!destinationFile.exists())
                {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                }
            
         }
    } 
    Alert alert;

 private void errorAlert(String content){
         if(alert==null){
            alert=new Alert(Alert.AlertType.ERROR);
        }
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.show();
    }
    
}
