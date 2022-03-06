/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.LoginDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.entities.Login;
import edu.esprit.entities.Utilisateur;
import edu.esprit.util.Statics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hrouz
 */
public class SignupController implements Initializable {

    @FXML
    private Pane imagePain;
    @FXML
    private TextField mdpInput;
    @FXML
    private Button btn;
    @FXML
    private TextField emailInput;
    @FXML
    private Pane imagePane;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField nomInput;
    @FXML
    private TextField confirmerMdpInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private ComboBox<Object> roleInput;
    @FXML
    private TextField filePathInput;
    @FXML
    private Button fileBtn;
    @FXML
    private ToggleGroup roleGroup;
    String imageName;
    int tempRole;
    @FXML
    private AnchorPane anchor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
       filePathInput.setEditable(false);
        Image im= new Image("/edu/esprit/util/assets/img/loginBooks1.jpg",imagePane.getBoundsInParent().getWidth(),imagePane.getBoundsInParent().getHeight(),false,false) ;
        
        imageView.setImage(im);

        /*imageView.setPreserveRatio(true);*/
        System.out.println(imagePane.getBoundsInParent().getWidth());
        System.out.println(imagePane.getBoundsInParent().getHeight());
        Rectangle clip = new Rectangle();
        clip.setWidth(imagePane.getBoundsInParent().getWidth());
        clip.setHeight(imagePane.getBoundsInParent().getHeight());
        clip.setArcHeight(90.0);
        clip.setArcWidth(90.0);
        clip.setStroke(Color.BLACK);
        imageView.setClip(clip);
        
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters,null);
        
        imageView.setClip(null);
        
        imageView.setImage(image);
        
        ObservableList<Object> options = 
    FXCollections.observableArrayList(
           "utilisateur" ,"ecrivain"
    );
        roleInput.setItems(options);
        
    }    
        
    @FXML
    private void sInscrire(ActionEvent event) throws ParseException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        System.out.println(dateInput.getValue());
        System.out.println(nomInput.getText());
        if (nomInput.getText() == null || nomInput.getText().trim().isEmpty())
            {
                alert.setContentText("Le champ nom et prénom est vide!");
                alert.show();
                return;
            }
        if (dateInput.getValue() == null )
            {
                alert.setContentText("Le champ date de naissance vide!");
                alert.show();
                return;
            }
        if (roleGroup.getSelectedToggle() == null )
            {
                alert.setContentText("Veuiller choisir un role!");  
                alert.show();
                return;
            }
        if (filePathInput.getText() == null || filePathInput.getText().trim().isEmpty())
            {
                alert.setContentText("Le champ photo de profil est vide!");
                alert.show();
                return;
            }
        if (emailInput.getText() == null || emailInput.getText().trim().isEmpty())
            {
                alert.setContentText("Le champ email est vide!");
                alert.show();
                return;
            }
        if (mdpInput.getText() == null || mdpInput.getText().trim().isEmpty())
            {
                alert.setContentText("Le champ mot de passe est vide!");
                alert.show();
                return;
            }
        if (confirmerMdpInput.getText() == null || confirmerMdpInput.getText().trim().isEmpty())
            {
                alert.setContentText("Le champ confirmer mot de passe est vide!");
                alert.show();
                return;
            }
        if(!mdpInput.getText().equals(confirmerMdpInput.getText()))
        {
            alert.setContentText("Les mots de passe ne correspond pas!");
            alert.show();
            return;
        }
       Utilisateur newUser= new Utilisateur(nomInput.getText(),java.sql.Date.valueOf( dateInput.getValue() ),imageName,tempRole,500,emailInput.getText());
        UtilisateurDAO us = new UtilisateurDAO();
        LoginDAO ls=new LoginDAO();
        if(us.findUtilisateurtByMail(emailInput.getText()).getType_utilisateur()!=0)
        {
            alert.setContentText("L'utilisateur existe deja!");
            alert.show();
            return;
        }
        us.insertUtilisateur(newUser);
        
       Utilisateur tempuser = us.findUtilisateurtByMail(emailInput.getText());
       Login templogin = new Login(tempuser.getId_utilisateur(),emailInput.getText(),mdpInput.getText());
       ls.insertLogin(templogin);
        try {       
            switchSceneLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fileChose(ActionEvent event) throws FileNotFoundException, IOException {


        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);


        if (file != null) { // only proceed, if file was chosen
             
             filePathInput.setText(file.toString());
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
             

             Utilisateur tempuser= Statics.currentUser;
         }
    }

    @FXML
    private void utilisateurRadio(ActionEvent event) {
        tempRole=1;
    }

    @FXML
    private void ecrivainRadio(ActionEvent event) {
        tempRole=2;
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchSceneLogin(ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("login.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    
}

