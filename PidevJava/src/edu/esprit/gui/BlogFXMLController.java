/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.BlogDAO;
import edu.esprit.dao.interfaces.IBlog;
import edu.esprit.entities.Blog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class BlogFXMLController implements Initializable {

    @FXML
    private Pane pnlJeton;
    @FXML
    private TextField serachbox;
    @FXML
    private VBox boxJeton;
    @FXML
    private TextField nom;
    @FXML
    private TextField lbdescrip;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField imagePath;
    private BlogFXMLController controller;
    private String  imageName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherRecom();
    }    

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void uploadImage(ActionEvent event) throws IOException {
        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)pnlJeton.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) { 
            Image img = new Image(file.toURI().toString());
             imagePath.setText(file.toString());
             File directory = new File("/src/image");
             String destination = directory.toString();
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                File sourceFile=null;
                File destinationFile = null;
                imageName = file.toString().substring(file.toString().lastIndexOf('\\')+1);
                sourceFile = new File(file.toString());
                destinationFile = new File( "src/image/"+imageName);
                if(!destinationFile.exists())
                {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                }
                imageView.setImage(img);
            
         }
    
    }

    @FXML
    private void AjouterJeton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
         if (lbdescrip.getText() == null || lbdescrip.getText().trim().isEmpty()) 
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
                   IBlog blogdao= new BlogDAO();
        String badwords[]= new String[]{
        "manef" , "hamza" , "walid" , "karoui","khaled","haythem","aziz"
        };
        String sujet = lbdescrip.getText();
        for (String word : badwords){
            sujet=sujet.replaceAll(word, "****");
        } 
                Blog recompense = new Blog(1,0,0,nom.getText(),sujet,imageName,"");    
                IBlog recompensedao = BlogDAO.getInstance();
        //        jetondao.insertJeton(jeton);
                recompensedao.insertBlog(recompense);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success");
                alert2.setContentText("Recompense is added successfully!");
                alert2.show();
                lbdescrip.setText("");
                //Image eventImage;
               
                    
                
                nom.setText("");
                 afficherRecom();
                 
                 
      }
    }

    @FXML
    private void TrieCroiss(ActionEvent event) {
    }

    @FXML
    private void Statis(ActionEvent event) {
    }
    
    public void afficherRecom (){
    
    boxJeton.getChildren().removeAll(boxJeton.getChildren());
     
        
        
        List<Blog> recompensel=new ArrayList<Blog>();
         
        IBlog blogdao=BlogDAO.getInstance();
        recompensel=blogdao.DisplayAllBlog();
        System.out.println(recompensel);
        
        
        try {
        for (Blog recompense : recompensel ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemBlog.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemBlogController itemrecompensController = fxmlloader.getController();
                 System.out.println(itemrecompensController);
                 itemrecompensController.setData(recompense);
                 
                 boxJeton.getChildren().add(anchorpane);
                 
                 
//                 if(column==3)
//                 {
//                     column = 0;
//                     row++;
//                 }
//                 grid.add(anchorpane, column++, row);
//                 GridPane.setMargin(anchorpane, new Insets(10));
                 

        }
        } catch (IOException ex) {
                 Logger.getLogger(BlogFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
}
    public void setController(BlogFXMLController controller){
    this.controller = controller;
}
    
}
