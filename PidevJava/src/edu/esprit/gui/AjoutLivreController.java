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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class AjoutLivreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    @FXML
    private TextField titre_livreInput;
    @FXML
    private TextField photo_livreInput;
    @FXML
    private TextField contenu_livreInput;
    @FXML
    private TextField prix_livreInput;
    @FXML
    private TextArea description_livreInput;
    @FXML
    private ComboBox<CategorieLivre> categorie_livreInput;
    @FXML
    private Button uploadImage;
    @FXML
    private Button uploadPdf;
    @FXML
    private AnchorPane anchor;

    public AjoutLivreController() {
        connection = MyConnection.getInstance();

    }

    @FXML
    private TableView<Livre> table_livre;

    @FXML
    private TableColumn<Livre, Integer> id_livre;

    @FXML
    private TableColumn<Livre, String> titre_livre;

    @FXML
    private TableColumn<Livre, String> description_livre;

    @FXML
    private TableColumn<Livre, Date> date_publication_livre;

    @FXML
    private TableColumn<Livre, String> photo_livre;

    @FXML
    private TableColumn<Livre, String> contenu_livre;

    @FXML
    private TableColumn<Livre, Integer> prix_livre;

    @FXML
    private TableColumn<Livre, Integer> evaluation_livre;

    @FXML
    private TableColumn<Livre, Integer> id_ecrivain_livre;

    @FXML
    private TableColumn<Livre, Integer> id_categorie_livre;
    //private AnchorPane anchor;

    String imageName;
    String pdfName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_livre.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id_livre"));
        titre_livre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre_livre"));
        description_livre.setCellValueFactory(new PropertyValueFactory<Livre, String>("description_livre"));
        date_publication_livre.setCellValueFactory(new PropertyValueFactory<Livre, Date>("date_publication_livre"));
        photo_livre.setCellValueFactory(new PropertyValueFactory<Livre, String>("photo_livre"));
        contenu_livre.setCellValueFactory(new PropertyValueFactory<Livre, String>("contenu_livre"));
        prix_livre.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("prix_livre"));
        evaluation_livre.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("evaluation_livre"));
        id_ecrivain_livre.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id_ecrivain_livre"));
        id_categorie_livre.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id_categorie_livre"));

        LivreDAO ls = new LivreDAO();
        List<Livre> listeLivre = ls.DisplayAllLivre();

        table_livre.setItems(FXCollections.observableList(listeLivre));

        CategorieLivreDAO cls = new CategorieLivreDAO();
        List<CategorieLivre> listeCategorieLivre = cls.DisplayAllCategorieLivre();

        //System.out.println(listeCategorieLivre);
        categorie_livreInput.setItems(FXCollections.observableList(listeCategorieLivre));
        categorie_livreInput.setConverter(new StringConverter<CategorieLivre>() {
            public String toString(CategorieLivre object) {
                return object.getLibelle();
            }

            public CategorieLivre fromString(String string) {
                return null;
            }
        });

    }

    @FXML
    private void selectRow(MouseEvent event) {
        if (event.getClickCount() == 1) //Check for a click, change to 2 for double click
        {
            titre_livreInput.setText(table_livre.getSelectionModel().getSelectedItem().getTitre_livre());
            description_livreInput.setText(table_livre.getSelectionModel().getSelectedItem().getDescription_livre());
            photo_livreInput.setText(table_livre.getSelectionModel().getSelectedItem().getPhoto_livre());
            contenu_livreInput.setText(table_livre.getSelectionModel().getSelectedItem().getContenu_livre());
            prix_livreInput.setText(table_livre.getSelectionModel().getSelectedItem().getPrix_livre() + "");
            categorie_livreInput.setValue(table_livre.getSelectionModel().getSelectedItem().getCategorieLivre());

        }
    }

    @FXML
    private void ajoutLivre(ActionEvent event) {

        //LocalDate date = LocalDate.of(2020, 1, 8);
        LocalDate date = java.time.LocalDate.now();
        Livre templ = new Livre(titre_livreInput.getText(), description_livreInput.getText(), java.sql.Date.valueOf(date), imageName, pdfName, Integer.parseInt(prix_livreInput.getText()), 0, Statics.currentUser.getId_utilisateur(), categorie_livreInput.getValue().getId_categorie_livre());
        LivreDAO ls = new LivreDAO();
        ls.insertLivre(templ);

    }

    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser chooser = new FileChooser();
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            this.photo_livreInput.setText(file.toString());
            File directory = new File("/src/image");
            String destination = directory.toString();
            if (!directory.exists()) {
                directory.mkdir();
            }
            File sourceFile = null;
            File destinationFile = null;
            imageName = file.toString().substring(file.toString().lastIndexOf('\\') + 1);
            sourceFile = new File(file.toString());
            destinationFile = new File("src/image/livre" + imageName);
            if (!destinationFile.exists()) {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
            }

        }
    }

    @FXML
    private void uploadPDF(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser chooser = new FileChooser();
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            this.contenu_livreInput.setText(file.toString());
            File directory = new File("/src/pdf");
            String destination = directory.toString();
            if (!directory.exists()) {
                directory.mkdir();
            }
            File sourceFile = null;
            File destinationFile = null;
            pdfName = file.toString().substring(file.toString().lastIndexOf('\\') + 1);
            sourceFile = new File(file.toString());
            destinationFile = new File("src/pdf/livre" + pdfName);
            if (!destinationFile.exists()) {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
            }

        }
    }

    @FXML
    private void modifier_livre(ActionEvent event) {
        LocalDate date = LocalDate.of(2020, 1, 8);
        System.out.println(table_livre.getSelectionModel().getSelectedItem().getId_livre());
        Livre templ = new Livre(table_livre.getSelectionModel().getSelectedItem().getId_livre(), titre_livreInput.getText(), 50, description_livre.getText(), java.sql.Date.valueOf(date), photo_livre.getText(), contenu_livre.getText(), 1, 15, 2);
        LivreDAO ls = new LivreDAO();
        ls.updateLivre(templ);
    }

    @FXML
    private void supprimer_livre(ActionEvent event) {
        LivreDAO ls = new LivreDAO();
        ls.deleteLivre(table_livre.getSelectionModel().getSelectedItem().getId_livre());

    }

}
