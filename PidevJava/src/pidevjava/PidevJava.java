/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import edu.esprit.dao.classes.LoginDAO;
import edu.esprit.entities.Utilisateur;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.entities.Login;
import edu.esprit.util.Mailer;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author macbook
 */
public class PidevJava extends Application {

    Parent parent;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
         
        LoginDAO ls = new LoginDAO();
        System.out.println(new java.sql.Date(System.currentTimeMillis()));
        
        ls.unblockByDay(new java.sql.Date(System.currentTimeMillis()));
        
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/edu/esprit/gui/home.fxml"));//meslivres , ajoutlivre  , ajoutcategorie  , ajoutlivreecrivain , livreachete
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Image icon=new Image("/edu/esprit/util/assets/img/libro.png");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setMaxWidth(1200);
        stage.setMaxHeight(880);
        stage.setTitle("Libro");
        stage.getIcons().add(icon);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
