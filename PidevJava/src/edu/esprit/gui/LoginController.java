/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.LoginDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.entities.Login;
import edu.esprit.util.Statics;
import java.io.IOException;

import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author Hrouz
 */
public class LoginController implements Initializable {
    
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button btn;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField mdpInput;

    @FXML
    private ImageView imageView;
    @FXML
    private Pane imagePane;
    @FXML
    private Pane imagePain;
    @FXML
    private Button btn1;
    @FXML
    private Button btn11;
    @FXML
    private Button btnHome;
    @FXML
    private Text blockedText;
    @FXML
    private Text dureeText;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        blockedText.setVisible(false);
        dureeText.setVisible(false);
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
    

    @FXML
    private void seConnecter(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            LoginDAO ls = new LoginDAO();
            UtilisateurDAO us = new UtilisateurDAO();
            if (ls.verifLogin(emailInput.getText(), mdpInput.getText()))
            {
            Login templogin = ls.findLoginByMail(emailInput.getText());
            if(templogin.getBlocked_login())
            {
                if(templogin.getBlocked_duree()==null)
                {
                    hideInputs();
                    blockedText.setVisible(true);
                    
                }
                else
                {
                    blockedText.setText("Votre compte a éte suspendu jusqu'a");
                    blockedText.setVisible(true);
                    dureeText.setText(templogin.getBlocked_duree()+"");
                    dureeText.setVisible(true);
                    hideInputs();
                }
                return;
            }
            Statics.currentUser = us.findUtilisateurtByMail(emailInput.getText());
            if(Statics.currentUser.getType_utilisateur()==4)
            {
            switchSceneAdminHome(event);
            }
            else{
            switchSceneProfil(event);     
            }
            }
            else
            {
            alert.setContentText("auth failed");
            alert.show();
            }
            
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void switchSceneSignUp(ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("signup.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    public void switchSceneAdminHome(ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("adminHome.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    @FXML
    public void switchSceneChangePassword(ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("changePassword.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
     
    }
    public void switchSceneProfil(ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void switchHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
   private void  hideInputs()
    {
        btn.setVisible(false);
        btn1.setVisible(false);
        btn11.setVisible(false);
        emailInput.setVisible(false);
        mdpInput.setVisible(false);
        
    }
   
    
}
