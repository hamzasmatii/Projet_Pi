/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;


import edu.esprit.dao.classes.BlogDAO;
import edu.esprit.dao.interfaces.IBlog;
import edu.esprit.entities.Blog;
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
 * @author Hamza
 */
public class ItemBlogController implements Initializable {

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
    @FXML
    private Label prix1;
    private Blog blog;
    private int id_blog;
    private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Label date;

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
                fxmlloader.setLocation(getClass().getResource("../view/ModifierBlog.fxml"));
            Parent root =  fxmlloader.load();
            ModifierBlogController recompensecontroller = fxmlloader.getController();
            recompensecontroller.setData(blog);
             //root = FXMLLoader.load(getClass().getResource("../view/ModifierJeton.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierRecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerJeton(ActionEvent event) throws IOException {
        IBlog recompensedao = new BlogDAO();
//        jetondao.insertJeton(jeton);
        recompensedao.deleteBlog(blog.getId_blog());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("suppression effectuée !");
        alert.show();
        
        //actualiser
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("../gui/BlogFXML.fxml"));
        Parent root =  fxmlloader.load();
        BlogFXMLController recompensecontroller = fxmlloader.getController();
        recompensecontroller.afficherRecom();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
	stage.show();
    }
      public void setData(Blog blog)
    {
        this.blog = blog;
        id_blog = blog.getId_blog();
        File file = new File(Statics.imageDirectory+blog.getPhoto_blog());
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
        description.setText(blog.getSujet_blog());
        nom.setText(blog.getTitre_blog());
        prix.setText(String.valueOf(blog.getLike_blog()));
        prix1.setText(String.valueOf(blog.getDislike_blog()));
        date.setText(blog.getDate_blog());
        
        
    }

    @FXML
    private void afficher(ActionEvent event) {
    }
    
}
