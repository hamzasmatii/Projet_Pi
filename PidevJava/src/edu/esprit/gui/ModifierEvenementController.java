/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.jfoenix.controls.JFXTimePicker;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
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
public class ModifierEvenementController implements Initializable {

    @FXML
    private DatePicker dateInput;
    @FXML
    private TextField titreInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private Button saveInput;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<Type_evenement> TypeInput;
    @FXML
    private TextField imagePath;
    @FXML
    private JFXTimePicker timepicker;
    
    String imageName;
     Alert alert;
    Evenement evenement;
    @FXML
    private AnchorPane anchor;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setComboBoxData();
        dateInput.setDayCellFactory((DatePicker d)-> 
        new DateCell(){
            @Override 
            public void updateItem(LocalDate item,boolean empty){
                super.updateItem(item, empty);
                setDisable(item.isBefore(LocalDate.now()));
                
            }
        });
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
        LocalTime value = timepicker.getValue();    
        Timestamp date = this.prepareSelectedDate();
        evenement.setTitre_evenement(this.titreInput.getText());
        evenement.setAdresse_evenement(this.adresse.getText());
        evenement.setDescription_evenement(this.descriptionInput.getText());
        evenement.setDate_evenement(date);
        evenement.setType_evenements(TypeInput.getValue());
        evenement.setImage(imageName);
        IevenementDAO edao=new EvenementDAO();
        edao.updateEvenement(evenement);
        succesAlert();
       
      
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
    
    public void setData(Evenement e){
        this.evenement=e;
        this.titreInput.setText(evenement.getTitre_evenement());
        this.descriptionInput.setText(evenement.getDescription_evenement());
        this.adresse.setText(evenement.getAdresse_evenement());
        TypeInput.setValue(evenement.getType_evenements());
        this.imagePath.setText(evenement.getImage());
        this.dateInput.setValue(evenement.getDate_evenement().toLocalDateTime().toLocalDate());
        this.timepicker.setValue(evenement.getDate_evenement().toLocalDateTime().toLocalTime());
        
         
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
    
    private Timestamp prepareSelectedDate(){
        LocalDate value = this.dateInput.getValue();
        LocalTime value1 = this.timepicker.getValue();
        LocalDateTime value2= LocalDateTime.of(value,value1);
        Timestamp selectedDate=Timestamp.valueOf(value2);
        System.out.println(selectedDate);
        return selectedDate;
        
        
    }
   
}
