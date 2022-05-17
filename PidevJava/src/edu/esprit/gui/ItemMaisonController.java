/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.MaisonDAO;
import edu.esprit.dao.interfaces.IMaison;
import edu.esprit.entities.Contrat;
import edu.esprit.entities.Maison;
import edu.esprit.util.Statics;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haythem
 */
public class ItemMaisonController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private ImageView imageview;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label prix;
 private Maison maison;
 private Contrat contrat;
    private int id_maison;
    private Stage stage;
	private Scene scene;
	private Parent root;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifIerJeton(ActionEvent event) {
        try {
            
            FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ModifierMaison.fxml"));
            Parent root =  fxmlloader.load();
            ModifierMaisonController recompensecontroller = fxmlloader.getController();
            recompensecontroller.setData(maison);
             //root = FXMLLoader.load(getClass().getResource("../view/ModifierJeton.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierMaisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerJeton(ActionEvent event) throws IOException {
           //Recompense recompense
        IMaison maisondao = MaisonDAO.getInstance();
//        jetondao.insertJeton(jeton);
        maisondao.deleteMaison(maison.getId_maison_editio());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("suppression effectuée !");
        alert.show();
        
        //actualiser
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("../gui/MaisonFXML.fxml"));
        Parent root =  fxmlloader.load();
        MaisonFXMLController recompensecontroller = fxmlloader.getController();
        recompensecontroller.afficherRecom();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
	stage.show();
    }
     public void setData(Maison maison)
    {
        this.maison = maison;
        id_maison = maison.getId_maison_editio();
        File file = new File(Statics.imageDirectory+maison.getPhoto_maison_edition());
        System.out.println(file.toURI().toString());
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
        description.setText(maison.getAdresse_maison_edition());
        nom.setText(maison.getNom_maison_edition());
        prix.setText(maison.getDescription_maison_edition());
        
        
        
    }

    @FXML
    private void Afficher(ActionEvent event) {
//        try{
//        FXMLLoader fxmlloader = new FXMLLoader();
//                fxmlloader.setLocation(getClass().getResource("../view/Contrat.fxml"));
//            Parent root =  fxmlloader.load();
//            ContratFXMLController recompensecontroller = fxmlloader.getController();
//            System.out.println(contrat.getId_contrat()+"-------------");
//            recompensecontroller.setData(contrat);
//             //root = FXMLLoader.load(getClass().getResource("../view/ModifierJeton.fxml"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
////            Stage stage = new Stage();
////                stage.setScene(new Scene(root));
////                stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(ModifierRecompenseController.class.getName()).log(Level.SEVERE, null, ex);
//        
//    }
    }
}
