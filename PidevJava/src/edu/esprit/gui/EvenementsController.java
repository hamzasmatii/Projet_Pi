/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import com.jfoenix.controls.JFXToggleButton;
import edu.esprit.dao.classes.EvenementDAO;
import edu.esprit.dao.classes.Participation_evenementDAO;
import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.dao.interfaces.IparticipationEvenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Participation_evenement;
import edu.esprit.entities.Utilisateur;
import edu.esprit.util.EvenementListner;
import edu.esprit.util.Statics;
import static edu.esprit.util.Statics.imageDirectory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class EvenementsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Evenement> allEvenements=new ArrayList();
    
       @FXML
    private TextField searchBox;

    @FXML
    private ScrollPane scrollEvenemets;

    @FXML
    private GridPane gridEvenements;   
    @FXML
    private Button participerBTN;
    @FXML
    private ImageView imageView;
    @FXML
    private Text titreText;
    @FXML
    private Text dateText;
    @FXML
    private Text descriptionText;
    @FXML
    private Text lieuText;
    
    private EvenementListner listner;
    @FXML
    private HBox topHbox;
    @FXML
    private Button ajoutButton;
    @FXML
    private JFXToggleButton sortToggle;
    Utilisateur utilisateur=Statics.currentUser;
    
    Evenement sideBarEvenement=new Evenement();
    @FXML
    private HBox modifBox;
    @FXML
    private Button modifBtn;
    @FXML
    private HBox participerBox;
    Evenement chosenEvent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(utilisateur.getType_utilisateur()<2){
          topHbox.getChildren().remove(6);
        }
       
        this.allEvenements=fechEvenement();
        try {
             if(allEvenements.size()>0){
            setChosenEvenement(this.allEvenements.get(0));}
        } catch (IOException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       listner= (Evenement e) -> {
           try {
               setChosenEvenement(e);
           } catch (IOException ex) {
               Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
           }
        };
       this.displayGrid(allEvenements);
            
    }

public List<Evenement> fechEvenement(){
    IevenementDAO edao=new EvenementDAO();
    return edao.fetchEvenement();
    
}
private void setChosenEvenement(Evenement evenement) throws IOException {
        chosenEvent=evenement;
        this.participerBTN.setVisible(true);
        modifBtn.setVisible(true);
       if(utilisateur.getId_utilisateur()!=evenement.getUtilisateur().getId_utilisateur()){
          modifBtn.setVisible(false);
       }
       IparticipationEvenementDAO pdao=new Participation_evenementDAO();
       if(pdao.verifEvenementUser(evenement, utilisateur)){
           this.participerBTN.setVisible(false);
       }
       sideBarEvenement=evenement;
       titreText.setText(evenement.getTitre_evenement());
       descriptionText.setText(evenement.getDescription_evenement());
       dateText.setText(evenement.getDate_evenement().toString());
       lieuText.setText(evenement.getAdresse_evenement());
       Image eventImage;
           if(evenement.getImage()==null){
               
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
           }else {
               File sourceimage = new File(imageDirectory+evenement.getImage());
                    Image image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
                    eventImage=image;
               
           }
        imageView.setImage(eventImage);
       } 

    @FXML
    private void ajoutEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutEvenement.fxml"));
        Pane orderView = loader.load();
        BorderPane homePane = (BorderPane) ajoutButton.getScene().getRoot();
        homePane.setCenter(orderView);
    }
    
    private List<Evenement> sortByDate(){
 List<Evenement> sorted=this.allEvenements.stream().
          sorted((e1,e2)->e2.getDate_evenement().compareTo(e1.getDate_evenement()))
         .collect(Collectors.toList());
         return sorted;
    }
    
    private void displayGrid(List<Evenement> evenements ){
            int column=0;
            int row=1;
        try {
        for(int i=0;i<evenements.size();i++){
            listner.onClickListner(evenements.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("evenementCard.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
            EvenementCardController cardController=fxmlLoader.getController();
            cardController.setData(evenements.get(i),listner);
             
            if (column == 1) {
                    column = 0;
                    row++;
                }
            
            gridEvenements.add(anchorPane,column++, row);
            gridEvenements.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridEvenements.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridEvenements.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                gridEvenements.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEvenements.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridEvenements.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
            }
        catch (IOException ex) {
            Logger.getLogger(EvenementsController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        
    }

    @FXML
    private void handleToggle(ActionEvent event) {
        if(sortToggle.isSelected()){
           List<Evenement> sortedGrid=this.sortByDate();
           this.gridEvenements.getChildren().clear();
           this.displayGrid(sortedGrid);
        }else{
            this.gridEvenements.getChildren().clear();
            this.displayGrid(this.allEvenements);
        }
    }

   

    @FXML
    private void search(KeyEvent event) {
      String word=searchBox.getText();
      List<Evenement> searchList=this.allEvenements.stream()
                .filter((e)->{return e.getTitre_evenement().toUpperCase().startsWith(word.toUpperCase());})
                .collect(Collectors.toList());
      this.gridEvenements.getChildren().clear();
      this.displayGrid(searchList);
    }

    @FXML
    private void ajoutParticipation(ActionEvent event) {
      Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Participation evenement");
      alert.setContentText("Voulez vous participer a cet evenement");
         Optional<ButtonType> option = alert.showAndWait();
         
       if (option.get() == ButtonType.OK) {
           IparticipationEvenementDAO pdao= new Participation_evenementDAO();
           Utilisateur user=Statics.currentUser;
          
           Participation_evenement p=new Participation_evenement(user,sideBarEvenement);
           pdao.insererParticipation(p);
                   }
        
    }

    @FXML
    private void switchscene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierEvenement.fxml"));
            Pane orderView = loader.load();
            ModifierEvenementController contr=loader.getController();
            contr.setData(chosenEvent);
            BorderPane homePane = (BorderPane) modifBtn.getScene().getRoot();
            homePane.setCenter(orderView);     
        
    }
    
   
    
}
