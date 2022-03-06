/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.EvenementDAO;
import edu.esprit.dao.classes.Type_evenementDAO;
import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.dao.interfaces.ItypeEvenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Type_evenement;
import edu.esprit.entities.Utilisateur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private DatePicker dateInput;
    @FXML
    private ComboBox<Type_evenement> TypeInput;
    @FXML
    private TextField titreInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private Button saveInput;
    @FXML
    private TextField adresse;
    @FXML
    private TextField imagePath;
    @FXML
    private AnchorPane anchor;
    String  imageName;
    Alert alert;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setComboBoxData();
    }    

    @FXML
    private void saveData(ActionEvent event) {
       
        if (titreInput.getText() == null || titreInput.getText().trim().isEmpty())
            {
                errorAlert("Le champ titre est vide!");
                return;
            }
        if (dateInput.getValue() == null )
            {
                errorAlert("Le champ date est vide!");
                return;
            }
        if (adresse.getText() == null || titreInput.getText().trim().isEmpty())
            {
                adresse.setText("");
                
            }
        if (descriptionInput.getText() == null || descriptionInput.getText().trim().isEmpty())
            {
               errorAlert("Le champ description est vide!");
               return;
            }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId_utilisateur(1);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date=Date.from(dateInput.getValue().atStartOfDay().toInstant(ZoneOffset.UTC));
        long timeInMilliSeconds = date.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
        System.out.print(imageName);
        //java.sql.Date.valueOf( dateInput.getValue() )
       Evenement e = new Evenement(this.titreInput.getText(),this.adresse.getText(),this.descriptionInput.getText(),date1,TypeInput.getValue(),utilisateur,this.imageName);
      IevenementDAO edao=new EvenementDAO();
      edao.insertEvenement(e);
      succesAlert();
      clearForm();
      
    }
    
    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {


        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);


        if (file != null) { 
            
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
            
         }
    }
    
    
    private void setComboBoxData(){
        ItypeEvenementDAO tedao= new Type_evenementDAO();
        ObservableList<Type_evenement> list = FXCollections.observableArrayList(tedao.fetchTypeEvenment());
        this.TypeInput.setItems(list);
        
    }

    private void clearForm() {
        this.titreInput.clear();
        this.adresse.clear();
        this.descriptionInput.clear();
        this.imageName="";
        dateInput.setValue(null);
        this.TypeInput.getSelectionModel().clearSelection();
        this.imagePath.clear();
        
        
        
        
    }
    private void succesAlert(){
        if(alert==null){
            alert=new Alert(Alert.AlertType.INFORMATION);
        }
      alert.setAlertType(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setContentText("Evenement ajouté avec succes");
      alert.show();
        
        
    }
    private void errorAlert(String content){
         if(alert==null){
            alert=new Alert(Alert.AlertType.ERROR);
        }
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.show();
    }

}
