/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.gui.*;
import edu.esprit.dao.classes.JetonDAO;
import edu.esprit.dao.classes.RecompenseDAO;
import edu.esprit.dao.interfaces.IJetonDAO;
import edu.esprit.dao.interfaces.IRecompenseDAO;
import edu.esprit.entities.Jeton;
import edu.esprit.entities.Recompense;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
public class ModifierRecompenseController implements Initializable {

    @FXML
    private AnchorPane panemod;
    @FXML
    private Spinner<Integer> spprix;
    @FXML
    private TextField lbdescrip1;
    private TextField photo;
    
    private JetonFXMLController controller;
      private Recompense recompense;
         
        private Stage stage;
        private Scene scene;
    @FXML
    private TextField nom;
    @FXML
    private TextField imagePath;
    @FXML
    private ImageView imageView;
    
    private String  imageName;
    @FXML
    private Button confbut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    
        SpinnerValueFactory<Integer> valueFactory2 = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        spprix.setValueFactory(valueFactory2);
//        spprix.getValueFactory().setValue(recompense.getPrix_recomponse());
//                System.out.println(recompense.getId_recomponse());
//        lbdescrip1.setText(recompense.getDescription_recomponse());
//        nom.setText(recompense.getNom_recomponse());
//        photo.setText(recompense.getPhoto_recomponse());
    
    }    

    @FXML
    private void ConfirmerRecom(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
    if (spprix.getValue() == null) 
            {
                alert.setContentText("Saisir le prix ");
                alert.show();
                
            }
        
         if (lbdescrip1.getText() == null || lbdescrip1.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir la description");
                alert.show();
                
            }
         else if (nom.getText() == null || nom.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir le nom");
                alert.show();
            }
       
        else {
        int id = recompense.getId_recomponse();
         Recompense recomponse = new Recompense(id,spprix.getValue(),nom.getText(),lbdescrip1.getText(),this.imageName);    
        IRecompenseDAO recomponsedao = RecompenseDAO.getInstance();
//        jetondao.insertJeton(jeton);
        recomponsedao.updateRecompense(recomponse);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Success");
        alert2.setContentText("Recompense is updated successfully!");
        
        
     
            
        //alert.show();
         Optional<ButtonType> res = alert2.showAndWait();
         if(res.isPresent()) {
                if(res.get().equals(ButtonType.OK))
                {
                         stage = (Stage) panemod.getScene().getWindow();
                            stage.close();
                            //actualiser new window
                       
//                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                        scene = new Scene(root);
//                        stage.setScene(scene);
//                        stage.show();
                }
        }

         }
//         FXMLLoader fxmlloader = new FXMLLoader();
//                fxmlloader.setLocation(getClass().getResource("../gui/RecompenseFXML.fxml"));
//            //Parent root1 =  (Parent) fxmlloader.load();
//             AnchorPane anchpa = (AnchorPane) fxmlloader.load();
//FXMLLoader fxmlloader2 = new FXMLLoader();
//                        fxmlloader2.setLocation(getClass().getResource("../gui/Home.fxml"));
//                        BorderPane bp =  fxmlloader2.load();
//             Parent root = FXMLLoader.load(getClass().getResource("../gui/Home.fxml"));
//             
//    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//  
//    stage.setScene(this.scene);
//    stage.show();
//    root = FXMLLoader.load (getClass () .getResource ("../gui/RecompenseFXML.fxml") ) ;
//    bp.setCenter(root);
                        //BorderPane homePane = (BorderPane) confbut.getScene().getRoot();
           // homePane.setCenter(anchpa);
            
            //AnchorPane panemod =  fxmlloader.load();
//          FXMLLoader fxmlloader = new FXMLLoader();
//                        fxmlloader.setLocation(getClass().getResource("../gui/RecompenseFXML.fxml"));
//                        
//                        RecompenseFXMLController recompensecontroller = fxmlloader.getController();
//                        
//                        recompensecontroller.afficherRecom();
//                        AnchorPane anchpa =  fxmlloader.load();
//                        
//                       FXMLLoader fxmlloader2 = new FXMLLoader();
//                        fxmlloader2.setLocation(getClass().getResource("../gui/Home.fxml"));
//                        BorderPane bp =  fxmlloader2.load();
////                         FXMLLoader fxmlloader2 = new FXMLLoader();
////                fxmlloader2.setLocation(getClass().getResource("../gui/Home.fxml"));
////            Parent root =  (Parent) fxmlloader2.load();
////                          bp = (BorderPane) confbut.getScene().getRoot();
//            bp.setCenter(anchpa);  
    }

   
     public void setData(Recompense recompense)
    {
        this.recompense = recompense;
        spprix.getValueFactory().setValue(recompense.getPrix_recomponse());
        lbdescrip1.setText(recompense.getDescription_recomponse());
        nom.setText(recompense.getNom_recomponse());
        File file = new File("src/image/"+recompense.getPhoto_recomponse());
        //System.out.println(file.toURI().toString());
        Image image = new Image(file.toURI().toString());
        imagePath.setText(file.toString());
        imageView.setImage(image);
        this.imageName = recompense.getPhoto_recomponse() ;
        
        
        
        
    }

    @FXML
    private void uploadImage(ActionEvent event) throws IOException {
        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)panemod.getScene().getWindow();
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
    
    
}
