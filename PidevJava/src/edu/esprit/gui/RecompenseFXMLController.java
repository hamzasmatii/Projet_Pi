/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.gui.*;
import edu.esprit.dao.classes.AchatRecomponseDAO;
import edu.esprit.dao.classes.JetonDAO;
import edu.esprit.dao.classes.LivreDAO;
import edu.esprit.dao.classes.RecompenseDAO;
import edu.esprit.dao.classes.RecompnseLivre;
import edu.esprit.dao.interfaces.IAchatRecomponseDAO;
import edu.esprit.dao.interfaces.IJetonDAO;
import edu.esprit.dao.interfaces.ILivre;
import edu.esprit.dao.interfaces.IRecompenseDAO;
import edu.esprit.dao.interfaces.IRecompnseLivre;
import edu.esprit.entities.AchatRecomponse;
import edu.esprit.entities.Jeton;
import edu.esprit.entities.Livre;
import edu.esprit.entities.Recompense;
import edu.esprit.entities.RecomponseLivre;
import edu.esprit.util.Statics;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.naming.directory.Attributes;
import javax.naming.spi.DirStateFactory.Result;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class RecompenseFXMLController implements Initializable {

    @FXML
    private Pane pnlJeton;
    @FXML
    private TextField serachbox;
    @FXML
    private VBox boxJeton;
    private TextField photo;
    @FXML
    private TextField nom;
    @FXML
    private TextField lbdescrip;
    private Spinner<Integer> spprix;
    private int user_id=3;
    
    
     private List<Recompense> allrecompenses=new ArrayList();
     private RecompenseFXMLController controller;
    @FXML
    private Spinner<Integer> prix;
    @FXML
    private TextField imagePath;
    private String  imageName;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox recomacht;
    @FXML
    private Spinner<Integer> quantitedon;
    @FXML
    private ChoiceBox<String> bookname;
    
     private List<Livre> livres = new ArrayList<>();
    @FXML
    private HBox boxx;

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
    private void AjouterJeton(ActionEvent event) {
        
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (prix.getValue() == null) 
            {
                alert.setContentText("Saisir le prix ");
                alert.show();
                
            }
        
         if (lbdescrip.getText() == null || lbdescrip.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir la description");
                alert.show();
                
            }
         else if (nom.getText() == null || nom.getText().trim().isEmpty()) 
            {
                alert.setContentText("Saisir le nom");
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
               System.out.println(prix.getValue());
                Recompense recompense = new Recompense(prix.getValue(),nom.getText(),lbdescrip.getText(),this.imageName);    
                IRecompenseDAO recompensedao = RecompenseDAO.getInstance();
        //        jetondao.insertJeton(jeton);
                recompensedao.insertRecompense(recompense);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success");
                alert2.setContentText("Recompense is added successfully!");
                alert2.show();
                lbdescrip.setText("");
                //Image eventImage;
               
                    
                
                nom.setText("");
                 afficherRecom();
                 
                 
      }
//         SpinnerValueFactory<Integer> valueFactory2 = 
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
//        spprix.setValueFactory(valueFactory2);
    }

    @FXML
    private void TrieCroiss(ActionEvent event) {
         boxJeton.getChildren().removeAll(boxJeton.getChildren());
     
        SpinnerValueFactory<Integer> valueFactory2 = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        prix.setValueFactory(valueFactory2);
        
                List<Recompense> recompense2=new ArrayList<Recompense>();
        IRecompenseDAO recompdao=RecompenseDAO.getInstance();
        recompense2=recompdao.DisplayAllRecompense();
        
        List<Recompense> sortedListrecompense = recompense2.stream()
                .sorted(Comparator.comparing(Recompense::getPrix_recomponse))
               .collect(Collectors.toList());
        
        
        try {
        for (Recompense recompense : sortedListrecompense ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemRecompense.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemRecompensController itemrecompensController = fxmlloader.getController();
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
                 Logger.getLogger(JetonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        //    List<RecomponseLivre> recomponselivre=new ArrayList<RecomponseLivre>();
//       IRecompnseLivreDAO recomplivdao=RecompnseLivreDAO.getInstance();
//        recomponselivre=recomplivdao.DisplayAllRecompenseLivreByid(1);
//        System.out.println(recomponselivre);
    }
    
    public void afficherRecom (){
        livres = getData();
        System.out.println("aaaaffffiiicherr");
    boxJeton.getChildren().removeAll(boxJeton.getChildren());
     
        SpinnerValueFactory<Integer> valueFactory2 = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        prix.setValueFactory(valueFactory2);
        
         
//         for (Livre livre : livres)
//         {
//             bookname.getItems().add(livre.getId_livre(),livre.getTitre_livre());
//         }
//         
         
        List<Recompense> recompensel=new ArrayList<Recompense>();
        IRecompenseDAO recompensedao=RecompenseDAO.getInstance();
        recompensel=recompensedao.DisplayAllRecompense();
        System.out.println(recompensel);
        
        
        try {
        for (Recompense recompense : recompensel ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemRecompense.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemRecompensController itemrecompensController = fxmlloader.getController();
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
                 Logger.getLogger(JetonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
         List<AchatRecomponse> achatrecomponse2=new ArrayList<AchatRecomponse>();
       IAchatRecomponseDAO achatrecompdao=AchatRecomponseDAO.getInstance();
        achatrecomponse2=achatrecompdao.findQRecomponseByIdUser(user_id);
        try {
        for (AchatRecomponse recompense2 : achatrecomponse2 ) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("../gui/ItemRecompenseAcht.fxml"));

             
                 HBox anchorpane = fxmlloader.load();
                 ItemRecompensAchtController itemrecompensController = fxmlloader.getController();
                 System.out.println("set"+itemrecompensController);
                 itemrecompensController.setData(recompense2);
                 
                 recomacht.getChildren().add(anchorpane);
                 
                 
//                 if(column==3)
//                 {
//                     column = 0;
//                     row++;
//                 }
//                 grid.add(anchorpane, column++, row);
//                 GridPane.setMargin(anchorpane, new Insets(10));
                 

        }
        } catch (IOException ex) {
                 Logger.getLogger(RecompenseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        System.out.println("aaaa"+achatrecomponse2);
        if(Statics.currentUser.getId_utilisateur()!=4)
        {
            boxx.setVisible(false);
        }
        
        
}
    public void setController(RecompenseFXMLController controller){
    this.controller = controller;
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
    private void Statis(ActionEvent event) {
        List<Recompense> recompense=new ArrayList<Recompense>();
       IRecompenseDAO recompdao=RecompenseDAO.getInstance();
        recompense=recompdao.DisplayAllRecompense();
        
        List<AchatRecomponse> achatrecomponse=new ArrayList<AchatRecomponse>();
       IAchatRecomponseDAO achatrecomponsedao=AchatRecomponseDAO.getInstance();
        achatrecomponse=achatrecomponsedao.DisplayAllAchatRecomponse();

        Map<Integer, Recompense> candidates = recompense
                .stream()
                      .collect(Collectors.toMap(Recompense::getId_recomponse, Function.identity()));
        System.out.println(candidates);
        
//        List<AchatRecomponse > listResult;
//        listResult = achatrecomponse.stream()
//                .map(org -> new Result(org, (Attributes) candidates.getOrDefault(org.getId_recomponse(), null)))
//                .collect(Collectors.counting();

    }
    
    
    private List<Livre> getData() {
        ILivre ldao = new LivreDAO();
        return ldao.DisplayAllLivre();

    }
    
}
