/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.RecompenseDAO;
import edu.esprit.dao.interfaces.IRecompenseDAO;
import edu.esprit.gui.*;
import edu.esprit.entities.AchatRecomponse;
import edu.esprit.entities.Recompense;
import edu.esprit.util.Statics;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class ItemRecompensAchtController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private ImageView imageview;
    
    private AchatRecomponse achatrecomponse;
    private Recompense recompense ;
    private int id_recompense;
    private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Text quantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Donner(ActionEvent event) {
    }
     public void setData(AchatRecomponse achatrecomponse)
    {
        this.achatrecomponse = achatrecomponse;
        
        id_recompense = achatrecomponse.getId_recomponse();
        quantite.setText(String.valueOf(achatrecomponse.getQuantite()));
        //Recompense recompensel=new Recompense();
        IRecompenseDAO recompensedao=RecompenseDAO.getInstance();
        recompense =recompensedao.findRecompenseById(id_recompense);
        //System.out.println("eeeeee"+recompense);
        File file = new File(Statics.imageDirectory+recompense.getPhoto_recomponse());
        System.out.println(file.toURI().toString());
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
        
       // prix.setText(String.valueOf(recompense.getPrix_recomponse()));
        
        
        
    }
}
