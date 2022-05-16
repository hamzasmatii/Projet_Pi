/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import static edu.esprit.util.Statics.imageDirectory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class EvenementAccueilCardController implements Initializable {
    Evenement evenement;
    @FXML
    private ImageView imageEvenement;
    @FXML
    private Label titreEvenement;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
    }    
    
    public void setData(Evenement e) throws IOException{
        this.evenement=e;
        System.out.println(e.getTitre_evenement());
        this.titreEvenement.setText(evenement.getTitre_evenement());
        Image img;
       if(e.getImage()==null){
               img=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
           }else {
              File sourceimage = new File(imageDirectory+evenement.getImage());
                    Image image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
                    img=image;
          }
       
        this.imageEvenement.setImage(img);
    }
    
    
    
}
