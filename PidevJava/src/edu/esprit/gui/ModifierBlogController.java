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
import java.nio.file.Files;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierBlogController implements Initializable {

    @FXML
    private AnchorPane panemod;
    @FXML
    private TextField nom;
    @FXML
    private TextField lbdescrip1;
    @FXML
    private TextField imagePath;
    @FXML
    private ImageView imageView;
    private Blog blog ;
    private int id_blog;
    private String imageName;
    private Stage stage;
    private Scene scene;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmerRecom(ActionEvent event) throws IOException {
                Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
         if (lbdescrip1.getText() == null || lbdescrip1.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir le sujet");
                alert.show();
                
            }
         else if (nom.getText() == null || nom.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir le titre");
                alert.show();
            }
//        else if (photo.getText() == null || photo.getText().trim().isEmpty()) 
//            {
//                alert.setContentText("Saisir la photo ");
//                alert.show();
//                
//            }
        else {
               //Recompense recompense
              // System.out.println(prix.getValue());
                Blog recompense = new Blog(1,1,blog.getLike_blog(),blog.getDislike_blog(),nom.getText(),lbdescrip1.getText(),imageName,"");    
                IBlog recompensedao = BlogDAO.getInstance();
        //        jetondao.insertJeton(jeton);
                recompensedao.updateBlog(recompense);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success");
                alert2.setContentText("Blog is updated successfully!");
                //alert.show();
         Optional<ButtonType> res = alert2.showAndWait();
         if(res.isPresent()) {
                if(res.get().equals(ButtonType.OK))
                {
                         stage = (Stage) panemod.getScene().getWindow();
                            stage.close();
                            //actualiser new window
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
        }

                
    }
    }
    @FXML
    private void uploadImage(ActionEvent event) throws IOException {
         FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)panemod.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) { 
            Image img = new Image(file.toURI().toString());
             imagePath.setText(file.toString());
             File directory = new File(Statics.imageDirectory);
             String destination = directory.toString();
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                File sourceFile=null;
                File destinationFile = null;
                imageName = file.toString().substring(file.toString().lastIndexOf('\\')+1);
                sourceFile = new File(file.toString());
                destinationFile = new File( Statics.imageDirectory+imageName);
                if(!destinationFile.exists())
                {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                }
                imageView.setImage(img);
            
         }
    }
       public void setData(Blog blog)
    {
        this.blog = blog;
        id_blog = blog.getId_blog();
        File file = new File(Statics.imageDirectory+blog.getPhoto_blog());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        lbdescrip1.setText(blog.getSujet_blog());
        nom.setText(blog.getTitre_blog());
       
        
        
    }
}
