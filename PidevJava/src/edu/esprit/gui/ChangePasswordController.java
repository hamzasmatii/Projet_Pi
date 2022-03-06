/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.LoginDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.entities.Utilisateur;
import edu.esprit.util.Mailer;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hrouz
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private Pane imagePain;
    @FXML
    private TextField confirmPasswordInput;
    @FXML
    private TextField newPasswordInput;
    @FXML
    private Pane imagePane;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btn1;
    @FXML
    private TextField mailInput;
    @FXML
    private TextField codeVerificationInput;
    @FXML
    private Text mailLabel;
    @FXML
    private Text codeLabel;
    @FXML
    private Text motDePasseLabel;
    @FXML
    private Button saveBtn;
    @FXML
    private Button mailBtn;
    @FXML
    private Button codeBtn;

    private String codeVerification ;
    UtilisateurDAO us = new UtilisateurDAO();
    LoginDAO ls = new LoginDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadImage();
        newPasswordInput.setVisible(false);
        newPasswordInput.setManaged(false);
        confirmPasswordInput.setVisible(false);
        confirmPasswordInput.setManaged(false);
        motDePasseLabel.setVisible(false);
        motDePasseLabel.setManaged(false);
        saveBtn.setVisible(false);
        saveBtn.setManaged(false);
        
        codeVerificationInput.setVisible(false);
        codeVerificationInput.setManaged(false);
        codeLabel.setVisible(false);
        codeLabel.setManaged(false);
        codeBtn.setVisible(false);
        codeBtn.setManaged(false);
        
    }    


    @FXML
    private void switchSceneSignUp(ActionEvent event) {
    }

    @FXML
    private void enregistrerMotDePasse(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println(newPasswordInput.getText().equals(confirmPasswordInput.getText()));
        if ((newPasswordInput.getText() == null || newPasswordInput.getText().trim().isEmpty()) ||
                (confirmPasswordInput.getText() == null || confirmPasswordInput.getText().trim().isEmpty()) ||
                (!newPasswordInput.getText().equals(confirmPasswordInput.getText())))
            {
                alert.setContentText("Les mots de passe ne correspond pas!");
                alert.show();
            }
        else
        {
            ls.forgotPassword(newPasswordInput.getText(), mailInput.getText());
            switchSceneLogin(event);
        }
    }

    @FXML
    private void envoyerCode(ActionEvent event) {
        mailBtn.setDisable(true);
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuiller saisir le mail!");
            if (mailInput.getText() == null || mailInput.getText().trim().isEmpty())
            {
            alert.show();
            mailBtn.setDisable(false);
            }
            else {
                
                if(us.findUtilisateurtByMail(mailInput.getText()).getId_utilisateur()==0)
                {
                alert.setContentText("L'Email n'existe pas!");
                alert.show();
                mailBtn.setDisable(false);
                }
                else{
                    Random random = new Random();
                codeVerification = String.format("%04d", random.nextInt(10000));
                    try {
                 Mailer.sendMail(mailInput.getText(), codeVerification);
             } catch (Exception ex) {
                 Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
             }
                    mailInput.setVisible(false);
                mailInput.setManaged(false);
                mailBtn.setVisible(false);
                mailBtn.setManaged(false);
                mailLabel.setVisible(false);
                mailLabel.setManaged(false);
                /*-------------------------------------------*/
                codeVerificationInput.setVisible(true);
                codeVerificationInput.setManaged(true);
                codeLabel.setVisible(true);
                codeLabel.setManaged(true);
                codeBtn.setVisible(true);
                codeBtn.setManaged(true);
                
                   
                }
                
             
            }
            
        
    }

    @FXML
    private void verifierCode(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuiller saisir le Code!");
            if (codeVerificationInput.getText() == null || codeVerificationInput.getText().trim().isEmpty())
            {
            alert.show();
            }
            else {
                if(!codeVerification.equals(codeVerificationInput.getText()))
                {
                    System.out.println(codeVerification+codeVerificationInput.getText());
                   alert.setContentText("Veuiller verifier le code envoyer!"); 
                   alert.show();
                }
                else{
                    newPasswordInput.setVisible(true);
                    newPasswordInput.setManaged(true);
                    confirmPasswordInput.setVisible(true);
                    confirmPasswordInput.setManaged(true);
                    motDePasseLabel.setVisible(true);
                    motDePasseLabel.setManaged(true);
                    saveBtn.setVisible(true);
                    saveBtn.setManaged(true);


                    codeVerificationInput.setVisible(false);
                    codeVerificationInput.setManaged(false);
                    codeLabel.setVisible(false);
                    codeLabel.setManaged(false);
                    codeBtn.setVisible(false);
                    codeBtn.setManaged(false);
                }
        
            }
    }
    private void loadImage()
    {
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
    }
    
     private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void switchSceneLogin(ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("login.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
}
