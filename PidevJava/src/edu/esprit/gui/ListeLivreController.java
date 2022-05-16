/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.LivreDAO;
import edu.esprit.dao.interfaces.ILivre;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Livre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class ListeLivreController implements Initializable {

    private List<Livre> livres = new ArrayList<>();
        private List<Livre> livresdate = new ArrayList<>();

    @FXML
    private GridPane bookContainer;
    @FXML
    private GridPane livredate;
    @FXML
    private ScrollPane scrollLivre;
    @FXML
    private TextField searchBox;
    @FXML
    private Button ajoutLivreBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livres = getData();
        livresdate=getDataO();
        
        int column = 0;
        int row = 1;
        // System.out.println(livres.size());
        try {
            for (int i = 0; i < livres.size(); i++) {
                // System.out.print(livres.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookContainer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BookContainerController itemController = fxmlLoader.getController();
                itemController.setData(livres.get(i));

                if (column == 6) {
                    column = 0;
                    row++;
                }

                bookContainer.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                bookContainer.setMinWidth(Region.USE_COMPUTED_SIZE);
                bookContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                bookContainer.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                bookContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
                bookContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                bookContainer.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO
        int columndate = 0;
        int rowdate = 1;
        // System.out.println(livres.size());
        try {
            for (int i = 0; i < livresdate.size(); i++) {
                // System.out.print(livres.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookContainer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BookContainerController itemController = fxmlLoader.getController();
                itemController.setData(livresdate.get(i));

              /*  if (columndate == 6) {
                    columndate = 0;
                    rowdate++;
                }*/

                livredate.add(anchorPane, columndate++, rowdate); //(child,column,row)
                //set grid width
                livredate.setMinWidth(Region.USE_COMPUTED_SIZE);
                livredate.setPrefWidth(Region.USE_COMPUTED_SIZE);
                livredate.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                livredate.setMinHeight(Region.USE_COMPUTED_SIZE);
                livredate.setPrefHeight(Region.USE_COMPUTED_SIZE);
                livredate.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Livre> getData() {
        ILivre ldao = new LivreDAO();
        return ldao.DisplayAllLivre();

    }
    
    @FXML
    private void ajoutLivre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutlivrecrivain.fxml"));
        Pane orderView = loader.load();
        BorderPane homePane = (BorderPane) ajoutLivreBtn.getScene().getRoot();
        homePane.setCenter(orderView);
    }
    
    @FXML
    private void search(KeyEvent event) {
      String word=searchBox.getText();
      List<Livre> searchList=this.livres.stream()
                .filter((e)->{return e.getTitre_livre().toUpperCase().startsWith(word.toUpperCase());})
                .collect(Collectors.toList());
      this.bookContainer.getChildren().clear();
      this.displayGrid(searchList);
    }

    private List<Livre> getDataO() {
        ILivre ldao = new LivreDAO();
        return ldao.DisplayAllLivreByDate();
    }
    private void displayGrid(List<Livre> livres){
         int column = 0;
        int row = 1;
        // System.out.println(livres.size());
        try {
            for (int i = 0; i < livres.size(); i++) {
                // System.out.print(livres.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookContainer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BookContainerController itemController = fxmlLoader.getController();
                itemController.setData(livres.get(i));

                if (column == 6) {
                    column = 0;
                    row++;
                }

                bookContainer.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                bookContainer.setMinWidth(Region.USE_COMPUTED_SIZE);
                bookContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                bookContainer.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                bookContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
                bookContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                bookContainer.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
        
        
    }
}
