/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.EvenementDAO;
import edu.esprit.dao.interfaces.IevenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Type_evenement;
import edu.esprit.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class EvenementAdminController implements Initializable {

    @FXML
    private TableView<Evenement> tableView;
    @FXML
    private TableColumn<Evenement,String> id;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> date_creation;
    @FXML
    private TableColumn<Evenement, String> date_evenement;
    @FXML
    private TableColumn<Evenement, String> adresse;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, String> utilisateur;
    @FXML
    private Button suprimerBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button modifierBtn;
IevenementDAO edao= new EvenementDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_evenement())));
        titre.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTitre_evenement())));
        date_creation.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDate_creation_evenement())));
        date_evenement.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDate_evenement())));
        adresse.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAdresse_evenement())));
        description.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDescription_evenement())));
        image.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getImage()))); 
        type.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getType_evenements().getLibelle_type_evenement())));
        utilisateur.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur().getNom_utilisateur())));
        
        List<Evenement> evenements=edao.fetchEvenement();
        tableView.setItems(FXCollections.observableList(evenements));     
    }
    
    @FXML
    private void selectRow(MouseEvent event) {
        
    }

    @FXML
    private void deleteEvenement(ActionEvent event) {
        
        edao.deleteEvenement(tableView.getSelectionModel().getSelectedItem().getId_evenement());
        List<Evenement> evenements=edao.fetchEvenement();
        tableView.setItems(FXCollections.observableList(evenements));  
    }
    
    @FXML
    private void modifierButton(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem()!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierEvenement.fxml"));
            Pane orderView = loader.load();
            ModifierEvenementController contr=loader.getController();
            contr.setData(tableView.getSelectionModel().getSelectedItem());
            BorderPane homePane = (BorderPane) modifierBtn.getScene().getRoot();
            homePane.setCenter(orderView);     
        List<Evenement> evenements=edao.fetchEvenement();
        tableView.setItems(FXCollections.observableList(evenements));  
        }
    }
    
}
