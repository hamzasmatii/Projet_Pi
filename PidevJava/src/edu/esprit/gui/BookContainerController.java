/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Livre;
import edu.esprit.util.Statics;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class BookContainerController implements Initializable {
     
    @FXML
    private Label titre;
    @FXML
    private Label discription;

    Livre l;
    @FXML
    private ImageView imagelivre;
    @FXML
    private Label evaluation;
    @FXML
    private Button btnConsulter;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
public void setData(Livre liv) {
        this.l = liv;
        titre.setText(l.getTitre_livre());
        discription.setText(l.getDescription_livre());
        evaluation.setText(l.getEvalution_livre()+"");
        System.out.print(l.getPhoto_livre());
        Image img = new Image("/image/"+l.getPhoto_livre(),false);
        imagelivre.setImage(img);
        
        
        
        
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void consulterLivre(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("detailLivre.fxml"));
                
               

                //DetailLivreController con = fxmlLoader.getController();

                Statics.livreid=l.getId_livre();
                Statics.categorielivreid=l.getCategorieLivre();
                root = fxmlLoader.load();
                DetailLivreController con = fxmlLoader.getController();
                System.out.print("---------------------------------------------"+l);
                con.setData(l);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                
    }
    
    
}
    
    

