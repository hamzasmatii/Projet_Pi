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
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
      
      public AjoutLivreController(){
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
        
        LivreDAO ls = new LivreDAO () ;
        List<Livre> listeLivre = ls.DisplayAllLivre();
        
        table_livre.setItems(FXCollections.observableList(listeLivre));
        
        CategorieLivreDAO cls= new CategorieLivreDAO();
        List<CategorieLivre> listeCategorieLivre = cls.DisplayAllCategorieLivre();
     
        System.out.println(listeCategorieLivre);
        categorie_livreInput.setItems(FXCollections.observableList(listeCategorieLivre));
        categorie_livreInput.setConverter(new StringConverter<CategorieLivre>()  {
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
        prix_livreInput.setText(table_livre.getSelectionModel().getSelectedItem().getPrix_livre()+"");
        categorie_livreInput.setValue(table_livre.getSelectionModel().getSelectedItem().getCategorieLivre());

    }
}

    @FXML
    private void ajoutLivre(ActionEvent event) {
        LocalDate date = LocalDate.of(2020, 1, 8);
        Livre templ = new Livre(titre_livreInput.getText(),description_livre.getText(),java.sql.Date.valueOf(date),photo_livre.getText(),contenu_livre.getText(),50,15,1,2);
        LivreDAO ls = new LivreDAO();
        ls.insertLivre(templ);
        
        
        /*
        String titre_livre,String description_livre,Date date_publication_livre, String photo_livre,String contenu_livre, int prix_livre,int evalution_livre ,int id_ecrivain_livre, int id_categorie_livre
        this.titre_livre = titre_livre;
        this.prix_livre = prix_livre;
        this.description_livre=description_livre;
        this.date_publication_livre = date_publication_livre;
        this.photo_livre = photo_livre;
        this.contenu_livre = contenu_livre;
        this.id_ecrivain_livre = id_ecrivain_livre;
        this.evalution_livre = evalution_livre;
        this.id_categorie_livre = id_categorie_livre;
        
        */
    }

    @FXML
    private void modifier_livre(ActionEvent event) {
                LocalDate date = LocalDate.of(2020, 1, 8);
                System.out.println(table_livre.getSelectionModel().getSelectedItem().getId_livre());
               Livre templ = new Livre(table_livre.getSelectionModel().getSelectedItem().getId_livre(),titre_livreInput.getText(),50,description_livre.getText(),java.sql.Date.valueOf(date),photo_livre.getText(),contenu_livre.getText(),1,15,2);
LivreDAO ls = new LivreDAO();
ls.updateLivre(templ);
    }

    @FXML
    private void supprimer_livre(ActionEvent event) {
        LivreDAO ls = new LivreDAO();
ls.deleteLivre(table_livre.getSelectionModel().getSelectedItem().getId_livre());
        
       
    }
    

}
