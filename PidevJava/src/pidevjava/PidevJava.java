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
         
        
        
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/edu/esprit/gui/Home.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setMaxWidth(1200);
        stage.setMaxHeight(820);
        stage.setTitle("Add and Show Persons");
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
