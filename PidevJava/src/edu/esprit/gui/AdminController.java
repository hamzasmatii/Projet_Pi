package edu.esprit.gui;

import edu.esprit.entities.Utilisateur;
import edu.esprit.util.Statics;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    private VBox pnItems = null;
    
    
    @FXML
    private BorderPane bp;
    
    @FXML
    private AnchorPane ap;
    
    @FXML
    private Button btnMaison;
    
     @FXML
    private Button btnAcceuil;

    @FXML
    private Button btnLivre;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnBlog;

    @FXML
    private Button btnRecompense;

    @FXML
    private Button btnJeton;

    @FXML
    private Button btnSignout;
    
    @FXML
    private Button btnEvenement;

    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        

    }


    
    
    
//    public void handleClicks(ActionEvent actionEvent) {
//        if (actionEvent.getSource() == btnProfile) {
//            loadPage("Profile");
//        }
////        if (actionEvent.getSource() == btnBlog) {
////            pnlBlog.setStyle("-fx-background-color : #53639F");//#02030A
////            pnlBlog.toFront();
////        }
//        if (actionEvent.getSource() == btnOverview) {
//            bp.setCenter(ap);
//        }
////        if(actionEvent.getSource()==btnLivre)
////        {
////            pnlLivre.setStyle("-fx-background-color : #460F67");//#02030A
////            pnlLivre.toFront();
////        }
////        if(actionEvent.getSource() == btnAcceuil)
////        {
////            pnlAcceuil.setStyle("-fx-background-color : #02030A");//#02030A
////            pnlAcceuil.toFront();
////        }
////        if(actionEvent.getSource() == btnMaison)
////        {
////            pnlMaison.setStyle("-fx-background-color : #464F55");//#02030A
////            pnlMaison.toFront();
////        }
////        if(actionEvent.getSource() == btnProfile)
////        {
////            pnlProfile.setStyle("-fx-background-color : #224F67");//#02030A
////            pnlProfile.toFront();
////        }
////        if(actionEvent.getSource() == btnEvenement)
////        {
////            pnlEvenement.setStyle("-fx-background-color : #400F67");//#02030A
////            pnlEvenement.toFront();
////        }
////        if(actionEvent.getSource() == btnRecompense)
////        {
////            pnlRecompense.setStyle("-fx-background-color : #00FF00");//#02030A
////            pnlRecompense.toFront();
////        }
////        if(actionEvent.getSource() == btnJeton)
////        {
////            pnlJeton.setStyle("-fx-background-color : #FF0000");//#02030A
////            pnlJeton.toFront();
////        }
//    }
    
    private void loadPage (String page) {
            Parent root = null;
        try {
            root = FXMLLoader.load (getClass () .getResource (page+".fxml") ) ;
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bp.setCenter(root);
        
   }

    @FXML
    private void acceuil(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void profile(MouseEvent event) {
        loadPage("blockUser");
    }

    @FXML
    private void livre(MouseEvent event) {
        loadPage("ajoutLivre");
    }

    @FXML
    private void maison(MouseEvent event) {
        loadPage("Maison");
    }

    @FXML
    private void evenement(MouseEvent event) {
        loadPage("Evenement");
    }

    @FXML
    private void blog(MouseEvent event) {
        loadPage("Blog");
    }

    @FXML
    private void recompense(MouseEvent event) {
        loadPage("Recompense");
    }

    @FXML
    private void jeton(MouseEvent event) {
        loadPage("Jeton");
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void switchSceneHome(ActionEvent event) throws IOException {
        Statics.currentUser=new Utilisateur();
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
}
