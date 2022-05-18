/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.gui.*;
import edu.esprit.dao.classes.AchatRecomponseDAO;
import edu.esprit.dao.classes.RecompenseDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.dao.interfaces.IAchatRecomponseDAO;
import edu.esprit.dao.interfaces.IRecompenseDAO;
import edu.esprit.entities.AchatRecomponse;
import edu.esprit.entities.Jeton;
import edu.esprit.entities.Recompense;
import edu.esprit.entities.Utilisateur;
import edu.esprit.util.Statics;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class ItemRecompensController implements Initializable {

    @FXML
    private HBox itemC;
    private Label photo;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label prix;
    
    private Recompense recompense;
    private int id_recompense;
    private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private ImageView imageview;
    @FXML
    private Button Modifirebut;
    @FXML
    private Button suprimer;
    @FXML
    private Button acheter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Statics.currentUser.getType_utilisateur()!=4)
        {
            suprimer.setVisible(false);
            Modifirebut.setVisible(false);
        }
        else
        {
            acheter.setVisible(false);
        }
    }    

    @FXML
    private void ModifIerJeton(ActionEvent event) {
         try {
            
            FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ModifierRecompense.fxml"));
            Parent root1 =  (Parent) fxmlloader.load();
            //AnchorPane panemod =  fxmlloader.load();
            ModifierRecompenseController recompensecontroller = fxmlloader.getController();
            recompensecontroller.setData(recompense);
             Stage stage2 = new Stage();
            stage2.setScene(new Scene(root1));  
            stage2.show();
//            BorderPane homePane = (BorderPane) Modifirebut.getScene().getRoot();
//            homePane.setCenter(panemod);
             //root = FXMLLoader.load(getClass().getResource("../view/ModifierJeton.fxml"));
             
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierJetonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerJeton(ActionEvent event) throws IOException {
        //Recompense recompense
        IRecompenseDAO recompensedao = RecompenseDAO.getInstance();
//        jetondao.insertJeton(jeton);
        recompensedao.deleteRecompense(recompense.getId_recomponse());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("suppression effectuée !");
        alert.show();
        
        //actualiser
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RecompenseFXML.fxml"));
        Pane pnlJeton = loader.load();
        BorderPane homePane = (BorderPane) suprimer.getScene().getRoot();
        homePane.setCenter(pnlJeton);
        
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//	stage.show();
    }
    
      public void setData(Recompense recompense)
    {
        this.recompense = recompense;
        id_recompense = recompense.getId_recomponse();
        File file = new File(Statics.imageDirectory+recompense.getPhoto_recomponse());
        System.out.println(file.toURI().toString());
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
        description.setText(recompense.getDescription_recomponse());
        nom.setText(recompense.getNom_recomponse());
        prix.setText(String.valueOf(recompense.getPrix_recomponse()));
        
        
        
    }

    @FXML
    private void Acheter(ActionEvent event) throws IOException {
        int solde = Statics.currentUser.getSolde_utilisateur();
         IRecompenseDAO recompensedao=RecompenseDAO.getInstance();
        recompense =recompensedao.findRecompenseById(recompense.getId_recomponse());
        if (solde>=recompense.getPrix_recomponse())
        {
        int id_user=Statics.currentUser.getId_utilisateur();
        
      
       AchatRecomponse achatrecomponse2=new AchatRecomponse();
       IAchatRecomponseDAO recompdao=AchatRecomponseDAO.getInstance();
        achatrecomponse2=recompdao.findQuantiteRecomponseByIdUser(id_user,recompense.getId_recomponse());
        //System.out.println(achatrecomponse2.getQuantite());
        System.out.println("dssssssdsdsd"+achatrecomponse2);
        if (achatrecomponse2.getQuantite()==0)
        {
            AchatRecomponse achatrecomponse = new AchatRecomponse(recompense.getId_recomponse(),id_user,1);    
        IAchatRecomponseDAO achatrecomponsedao = AchatRecomponseDAO.getInstance();
        achatrecomponsedao.insertAchatRecomponse(achatrecomponse);
        
        
        Utilisateur tempuser= Statics.currentUser; 
        tempuser.setSolde_utilisateur(solde-recompense.getPrix_recomponse());
        UtilisateurDAO us = new UtilisateurDAO();
        us.updateUtilisateur(tempuser);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Achat effectuée !");
        alert.show();
        }
        else
        {
            System.out.println(achatrecomponse2.getQuantite());
        int quant = achatrecomponse2.getQuantite()+1;
        
        
         AchatRecomponse achatrecomponse = new AchatRecomponse(recompense.getId_recomponse(),id_user,quant);    
        IAchatRecomponseDAO achatrecomponsedao = AchatRecomponseDAO.getInstance();
        achatrecomponsedao.updateAchatRecomponse(achatrecomponse);
        
        Utilisateur tempuser= Statics.currentUser; 
        tempuser.setSolde_utilisateur(solde-recompense.getPrix_recomponse());
        UtilisateurDAO us = new UtilisateurDAO();
        us.updateUtilisateur(tempuser);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Achzt2 effectuée !");
        alert.show();
        }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText("Solde insuffisant !");
        alert.show();
        }
         //actualiser
//        FXMLLoader fxmlloader = new FXMLLoader();
//        fxmlloader.setLocation(getClass().getResource("../gui/RecompenseFXML.fxml"));
//        Parent root =  fxmlloader.load();
//        RecompenseFXMLController recompensecontroller = fxmlloader.getController();
//        recompensecontroller.afficherRecom();
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//	stage.show();
    }
    
    
    
}
