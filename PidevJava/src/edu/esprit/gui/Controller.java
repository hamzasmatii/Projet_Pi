package edu.esprit.gui;

import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import edu.esprit.dao.classes.EvenementDAO;
import edu.esprit.dao.classes.Participation_evenementDAO;
import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.dao.interfaces.IparticipationEvenementDAO;
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

public class Controller implements Initializable {

    @FXML
    private BorderPane pnItems ;
    
    
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

    
    @FXML
    private Pane pnlAcceuil;
    @FXML
    private Button btnSignin;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        evenementNotification();
        
        if (Statics.currentUser.getType_utilisateur()==0)
        {
            btnProfile.setVisible(false);
            btnProfile.setManaged(false);
            btnSignout.setVisible(false);
            btnSignout.setManaged(false);
            btnSignin.setVisible(true);
            btnSignin.setManaged(true);
        }
        else{
            btnProfile.setVisible(true);
            btnProfile.setManaged(true);
            btnSignout.setVisible(true);
            btnSignout.setManaged(true);
            btnSignin.setVisible(false);
            btnSignin.setManaged(false);
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evenementsAccueil.fxml"));
        Pane orderView;
        try {
            orderView = loader.load();
            pnItems.setCenter(orderView);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    public  void loadPage (String page) {
            Parent root = null;
        try {
            root = FXMLLoader.load (getClass () .getResource (page+".fxml") ) ;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(root);
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
    private void profile(ActionEvent event) {
        loadPage("profil");
    }

    @FXML
    private void livre(MouseEvent event) {
        loadPage("listeLivre");
    }

    @FXML
    private void maison(MouseEvent event) {
        loadPage("Maison");
    }

    @FXML
    private void evenement(MouseEvent event) {
        loadPage("evenements");
    }

    @FXML
    private void blog(MouseEvent event) {
        loadPage("Blog");
    }

    @FXML
    private void recompense(MouseEvent event) {
        loadPage("RecompenseFXML");
    }

    @FXML
    private void jeton(MouseEvent event) {
        loadPage("JetonFXML");
    }

   
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void seConnecter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }


    @FXML
    private void signOut(ActionEvent event) throws IOException {
        Statics.currentUser=new Utilisateur();
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
    private void evenementNotification(){
        Utilisateur user=Statics.currentUser;
        
        if(user.getType_utilisateur()!=0){
           
        IparticipationEvenementDAO edao= new Participation_evenementDAO();
        if(edao.fetchUpcomingEvents(user)==true){
        
        String title = "Evenement a venir";
        String message = "Veuillez verifier le calendrier vous avez des evenements a venir ";
        Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndWait();  
        }
        }
            
        }
            
        
        
      
        
    }

