/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.CategorieLivreDAO;
import edu.esprit.dao.classes.LivreDAO;
import edu.esprit.entities.CategorieLivre;
import edu.esprit.entities.Livre;
import edu.esprit.util.MyConnection;
import edu.esprit.util.Statics;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class AjoutcategorieController implements Initializable {

    private Connection connection;

    @FXML
    private TableView<CategorieLivre> table_categorie;
    @FXML
    private TableColumn<CategorieLivre, Integer> id_categorie_livre;
    @FXML
    private TableColumn<CategorieLivre, String> libelle;
    @FXML
    private TextField libelleInput;

    public AjoutcategorieController() {
        connection = MyConnection.getInstance();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_categorie_livre.setCellValueFactory(new PropertyValueFactory<CategorieLivre, Integer>("id_categorie_livre"));
        libelle.setCellValueFactory(new PropertyValueFactory<CategorieLivre, String>("libelle"));

        /*
        LivreDAO ls = new LivreDAO();
        List<Livre> listeLivre = ls.DisplayAllLivre();

        table_livre.setItems(FXCollections.observableList(listeLivre));
         */
        CategorieLivreDAO ls = new CategorieLivreDAO();
        List<CategorieLivre> categorielivre = ls.DisplayAllCategorieLivre();
        table_categorie.setItems(FXCollections.observableList(categorielivre));

    }

    @FXML
    private void selectRow(MouseEvent event) {
        if (event.getClickCount() == 1) //Check for a click, change to 2 for double click
        {
            libelleInput.setText(table_categorie.getSelectionModel().getSelectedItem().getLibelle());

        }
    }
 @FXML
    private void AjoutCategorieLivre(ActionEvent event) {

        //LocalDate date = LocalDate.of(2020, 1, 8);
        CategorieLivre templ = new CategorieLivre(libelleInput.getText());
        CategorieLivreDAO ls = new CategorieLivreDAO();
        ls.insertCategorieLivre(templ);

    }

    @FXML
    private void modifier_CategorieLivre(ActionEvent event) {
        CategorieLivre templ = new CategorieLivre(table_categorie.getSelectionModel().getSelectedItem().getId_categorie_livre(),libelleInput.getText());
        CategorieLivreDAO ls = new CategorieLivreDAO();
        ls.updateCategorieLivre(templ);
    }
    
     @FXML
    private void supprimer_CategorieLivre(ActionEvent event) {
        CategorieLivreDAO ls = new CategorieLivreDAO();
        ls.deleteCategorieLivre(table_categorie.getSelectionModel().getSelectedItem().getId_categorie_livre());

    }

 
}
