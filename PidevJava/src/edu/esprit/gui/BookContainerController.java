/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Livre;
import edu.esprit.util.Statics;
import static edu.esprit.util.Statics.imageDirectory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
        //System.out.println("---------------------------------------------"+l); 
    }
public void setData(Livre liv) throws IOException {
           // System.out.println("This is Set data");

        this.l = liv;
        titre.setText(l.getTitre_livre());
        discription.setText(l.getDescription_livre());
        evaluation.setText(l.getEvalution_livre()+"");
        System.out.println("-----------------------"+imageDirectory+"-----"+l.getPhoto_livre());
        
        File sourceimage = new File(imageDirectory+"livre\\"+l.getPhoto_livre());
        Image image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
        Image img=image;
        imagelivre.setImage(img);
        
        
        
        
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void consulterLivre(ActionEvent event) throws IOException {
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("detailLivre.fxml"));

                //DetailLivreController con = fxmlLoader.getController();

                Statics.livreid=l.getId_livre();
                Statics.categorielivreid=l.getCategorieLivre();
                //root = loader.load();
                
                /*stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();*/
                
                
                    Pane orderView = loader.load();
                    DetailLivreController con = loader.getController();
                
                con.setData(l);
        BorderPane homePane = (BorderPane) btnConsulter.getScene().getRoot();
        homePane.setCenter(orderView);

                
    }
    
    
}
    
    

