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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Hrouz
 */
public class ProfilController implements Initializable {

    @FXML
    private Label soldeInput;
    @FXML
    private TextField mdpInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField nomInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private ComboBox<role> roleInput;
    @FXML
    private Rectangle rectangle;
    @FXML
    private ImageView imageView;
    @FXML
    private ToggleGroup changePassword;
    @FXML
    private ToggleButton noToggle;
    @FXML
    private ToggleButton yesToggle;
    @FXML
    private TextField newMdpInput;
    private Button changePasswordBtn;
    @FXML
    private TextField confirmNewMdpInput;
    @FXML
    private Button saveChangesBtn;

    List<role> listeroles = new ArrayList<role>();
    @FXML
    private Button fileBtn;
    @FXML
    private AnchorPane anchor;
    String imageName;
    @FXML
    private TextField filePathInput;
    @FXML
    private Button loadpane;
    @FXML
    private BorderPane CalendarContainer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        roleInput.setDisable(true);
        role r1 = new role(1, "Utilisateur");
        listeroles.add(r1);
        listeroles.add(new role(2, "Ecrivain"));
        listeroles.add(new role(3, "Admin Maison d'edition"));
        listeroles.add(new role(4, "Administrateur"));
        roleInput.setItems(FXCollections.observableList(listeroles));
        roleInput.setConverter(new StringConverter<role>() {
            public String toString(role object) {
                return object.libelle;
            }

            public role fromString(String string) {
                return null;
            }
        });
        confirmNewMdpInput.setDisable(true);
        mdpInput.setDisable(true);
        newMdpInput.setDisable(true);
        Image im = new Image("/image/"+Statics.currentUser.getPhoto_utilisateur(), rectangle.getBoundsInParent().getWidth(), rectangle.getBoundsInParent().getHeight(), false, false);

        imageView.setImage(im);

        /*imageView.setPreserveRatio(true);*/
        System.out.println(rectangle.getBoundsInParent().getWidth());
        System.out.println(rectangle.getBoundsInParent().getHeight());
        Rectangle clip = new Rectangle();
        clip.setWidth(rectangle.getBoundsInParent().getWidth());
        clip.setHeight(rectangle.getBoundsInParent().getHeight());
        clip.setArcHeight(180.0);
        clip.setArcWidth(180.0);
        clip.setStroke(Color.BLACK);
        imageView.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters, null);

        imageView.setClip(null);

        imageView.setImage(image);
        // TODO
        Utilisateur current = Statics.currentUser;
        nomInput.setText(current.getNom_utilisateur());
        dateInput.setValue(current.getDate_naissance_utilisateur().toLocalDate());
        roleInput.setValue(listeroles.get(current.getType_utilisateur() - 1));
        emailInput.setText(current.getEmail_utilisateur());
        soldeInput.setText(current.getSolde_utilisateur() + "");
        filePathInput.setText(current.getPhoto_utilisateur());
        imageName=current.getPhoto_utilisateur();
        yesToggle.selectedProperty().addListener(((value) -> {
            mdpInput.setDisable(false);
            newMdpInput.setDisable(false);
            confirmNewMdpInput.setDisable(false);
        }));
        noToggle.selectedProperty().addListener(((value) -> {
            mdpInput.clear();
            newMdpInput.clear();
            mdpInput.setDisable(true);
            newMdpInput.setDisable(true);
            confirmNewMdpInput.setDisable(true);
        }));
        try {
            loadCalendar();
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (nomInput.getText() == null || nomInput.getText().trim().isEmpty()) 
            {
                alert.setContentText("Le champ Nom et prénom est vide!");
                alert.show();
                return;
            }
        if (emailInput.getText() == null || emailInput.getText().trim().isEmpty()) 
            {
                alert.setContentText("Le champ Email est vide!");
                alert.show();
                return;
            }
        if ((yesToggle.isSelected()) && ((mdpInput.getText() == null || mdpInput.getText().trim().isEmpty()) ||
                (newMdpInput.getText() == null || newMdpInput.getText().trim().isEmpty()) ||
                (confirmNewMdpInput.getText() == null || confirmNewMdpInput.getText().trim().isEmpty()) 
                ))
        {
            alert.setContentText("Le champ mot de passe est vide!");
                alert.show();
                return;
        }
        if ((yesToggle.isSelected()) &&(!newMdpInput.getText().equals(confirmNewMdpInput.getText())))
        {
        alert.setContentText("\"Les mots de passe ne correspond pas!\"");
                alert.show();
                return;
        }
        LoginDAO ls = new LoginDAO();
        if (yesToggle.isSelected())
        {
            String resultPassword=ls.changePassword(mdpInput.getText(), newMdpInput.getText(), Statics.currentUser.getEmail_utilisateur());
        if (!resultPassword.equals("updated"))
        {
        alert.setContentText(resultPassword);
                alert.show();
                return;
        }
        }
        
        System.out.println("cbon");
            
        Utilisateur tempuser= Statics.currentUser;
        System.out.println(tempuser);
        tempuser.setNom_utilisateur(nomInput.getText());
        tempuser.setDate_naissance_utilisateur(java.sql.Date.valueOf( dateInput.getValue() ));
        tempuser.setEmail_utilisateur(emailInput.getText());
        tempuser.setPhoto_utilisateur(imageName);
        System.out.println(tempuser);
        Login templogin = new Login();
        templogin.setId_login(Statics.currentUser.getId_utilisateur());
        ls.updateMail(templogin, emailInput.getText());
        UtilisateurDAO us = new UtilisateurDAO();
        us.updateUtilisateur(tempuser);
        
    }

    @FXML
    private void fileChose(ActionEvent event) throws FileNotFoundException, IOException {


        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);


        if (file != null) { // only proceed, if file was chosen
             Image img = new Image(file.toURI().toString(),rectangle.getBoundsInParent().getWidth(), rectangle.getBoundsInParent().getHeight(), false, false);
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
             
             imageView.setImage(img);
             Utilisateur tempuser= Statics.currentUser;
         }
    }

    @FXML
    private void loadepane(ActionEvent event) throws IOException {
         /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Home.fxml"));
            fxmlLoader.setLocation(getClass().getResource("Home.fxml"));
            BorderPane borderPane=fxmlLoader.load();

            Controller cardController=fxmlLoader.getController();
            cardController.loadPage("login");*/
         /*Parent root = null;
                     root = FXMLLoader.load (getClass () .getResource ("changePassword.fxml") ) ;
                     Controller.bp.setCenter(root);*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePassword.fxml"));
        Pane orderView = loader.load();
        BorderPane homePane = (BorderPane) loadpane.getScene().getRoot();
        homePane.setCenter(orderView);

    }
    
    private void loadCalendar() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calendar.fxml"));
        Pane calendarView = loader.load();
        CalendarContainer.setCenter(calendarView);
        
        
    }
}

class role {

    int id;
    String libelle;

    public role(int id_, String libelle_) {
        id = id_;
        libelle = libelle_;
    }

    @Override
    public String toString() {
        return "role{" + "id=" + id + ", libelle=" + libelle + '}';
    }
    
    
}
