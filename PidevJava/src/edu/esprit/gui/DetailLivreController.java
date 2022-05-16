/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.AchatLivreDAO;
import edu.esprit.dao.classes.CommentaireLivreDAO;
import edu.esprit.dao.classes.LivreDAO;
import edu.esprit.dao.classes.UtilisateurDAO;
import edu.esprit.dao.interfaces.ICommentaireLivre;
import edu.esprit.dao.interfaces.ILivre;
import edu.esprit.entities.CategorieLivre;
import edu.esprit.entities.CommentaireLivre;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Livre;
import edu.esprit.util.Statics;
import static edu.esprit.util.Statics.imageDirectory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 * FXML Controller class
 *
 * @author aziz karoui
 */
public class DetailLivreController implements Initializable {

    private List<Livre> livres = new ArrayList<>();
    private List<CommentaireLivre> commentaire = new ArrayList<>();
    private CommentaireLivre c;

    private Livre l;
    @FXML
    private GridPane livre_categorie;
    @FXML
    private Label titreLabel;
    @FXML
    private GridPane commentaire_list;
    @FXML
    private ImageView imagedetail;
    @FXML
    private Label discriptiondetail;
    @FXML
    private Label auteurdetail;
    @FXML
    private Label evaluationdetail;
    @FXML
    private Label id_livre;
    @FXML
    private Button pdflivre;
    @FXML
    private Button acheterlivre;
    @FXML
    private Button evaluationP;
    @FXML
    private Button evaluationM;
    @FXML
    private TextField commentairelivreInput;
    @FXML
    private Button ajoutcommentairebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //livres = getDataTest();
        // System.out.println("Prix "+l.getPrix_livre());
        livres = getData(Statics.categorielivreid);
        int column = 0;
        int row = 1;
        if (livres == null) {
        }
        // System.out.println(Statics.livreid + "----------------------------------**************************");
        // System.out.println(livres.size());
        try {
            for (int i = 0; i < livres.size(); i++) {
                System.out.print(livres.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookContainer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BookContainerController itemController = fxmlLoader.getController();
                itemController.setData(livres.get(i));


                /* if (column == 5) {
                    column = 0;
                    row++;
                }*/
                livre_categorie.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                livre_categorie.setMinWidth(Region.USE_COMPUTED_SIZE);
                livre_categorie.setPrefWidth(Region.USE_COMPUTED_SIZE);
                livre_categorie.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                livre_categorie.setMinHeight(Region.USE_COMPUTED_SIZE);
                livre_categorie.setPrefHeight(Region.USE_COMPUTED_SIZE);
                livre_categorie.setMaxHeight(Region.USE_PREF_SIZE);

                livre_categorie.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO
        commentaire = getDataCategorie(Statics.livreid);

        int columnn = 0;
        int roww = 1;
        //System.out.println(commentaire.size());
        try {
            for (int i = 0; i < commentaire.size(); i++) {
                // System.out.print(commentaire.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("commentairelivre.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CommentairelivreController itemControllerCommentaire = fxmlLoader.getController();
                itemControllerCommentaire.setDatacom(commentaire.get(i));

                commentaire_list.add(anchorPane, columnn, roww++); //(child,column,row)
                //set grid width
                commentaire_list.setMinWidth(Region.USE_COMPUTED_SIZE);
                commentaire_list.setPrefWidth(Region.USE_COMPUTED_SIZE);
                commentaire_list.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                commentaire_list.setMinHeight(Region.USE_COMPUTED_SIZE);
                commentaire_list.setPrefHeight(Region.USE_COMPUTED_SIZE);
                commentaire_list.setMaxHeight(Region.USE_PREF_SIZE);

                commentaire_list.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //tesst
    }

    private List<Livre> getDataTest() {
        LivreDAO ldao = new LivreDAO();
        return ldao.DisplayAllLivre();

    }

    private List<Livre> getData(CategorieLivre idc) {
        LivreDAO ldao = new LivreDAO();
        return ldao.DisplayAllLivreByCategorie(idc);

    }

    private List<CommentaireLivre> getDataCategorie(int id) {
        CommentaireLivreDAO ldao = new CommentaireLivreDAO();

        return ldao.DisplayAllCommentaireLivreByLivre(id);

    }

    public void setData(Livre l) throws IOException {
        this.l = l;
        // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa." + l);
        titreLabel.setText(l.getTitre_livre());
        discriptiondetail.setText(l.getDescription_livre());
        auteurdetail.setText("Par :" + l.getUtilisateur().getNom_utilisateur());
        evaluationdetail.setText("réaction pour ce livre :" + l.getEvalution_livre() + "");
        File sourceimage = new File(imageDirectory+l.getPhoto_livre());
                    Image image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
                Image img=image;
       
        imagedetail.setImage(img);

    }

    @FXML
    public void openPDF() {
        File file = new File("src/pdf/livre" + l.getContenu_livre());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        String path = file.getAbsolutePath();
        System.out.println(path);
// build a controller
        SwingController controller = new SwingController();

// Build a SwingViewFactory configured with the controller
        SwingViewBuilder factory = new SwingViewBuilder(controller);

// Use the factory to build a JPanel that is pre-configured
//with a complete, active Viewer UI.
        JPanel viewerComponentPanel = factory.buildViewerPanel();

// add copy keyboard command
        ComponentKeyBinding.install(controller, viewerComponentPanel);

// add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

// Create a JFrame to display the panel in
        JFrame window = new JFrame("Using the Viewer Component");
        window.getContentPane().add(viewerComponentPanel);
        window.pack();
        window.setVisible(true);

// Open a PDF document to view
        controller.openDocument(path);

    }

    @FXML
    public void evaluationP() {
        LivreDAO ldao = new LivreDAO();
        System.out.println(l.getId_livre());
        ldao.updateLivreEvaluationP(l.getId_livre());
        evaluationP.setDisable(true);
        evaluationM.setDisable(true);

    }

    @FXML
    public void evaluationM() {
        LivreDAO ldao = new LivreDAO();
        System.out.println(l.getId_livre());
        ldao.updateLivreEvaluationM(l.getId_livre());
        evaluationP.setDisable(true);
        evaluationM.setDisable(true);

    }

    @FXML
    private void validerachat(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        //System.out.println("Prix " + l.getPrix_livre());
        if (Statics.currentUser.getSolde_utilisateur() > l.getPrix_livre()) {
            UtilisateurDAO user = new UtilisateurDAO();
            user.updateLivreEvaluationP(Statics.currentUser.getId_utilisateur(), l.getPrix_livre());
            AchatLivreDAO achat = new AchatLivreDAO();
            LocalDate date = java.time.LocalDate.now();
            achat.insertAchatLivre(Statics.currentUser.getId_utilisateur(), l.getId_livre(), java.sql.Date.valueOf(date));
            
            acheterlivre.setDisable(true);
            alert.setAlertType(AlertType.ERROR);
            return;
        }

        Optional<ButtonType> options = alert.showAndWait();
        if (options.get() == ButtonType.OK) {
            System.out.println("Echri");
            /*UtilisateurDAO user = new UtilisateurDAO();
            user.updateLivreEvaluationP(Statics.currentUser.getId_utilisateur(), l.getPrix_livre());*/
        }

    }

    @FXML
    private void AjoutCommentaire(ActionEvent event) {

        LocalDate date = java.time.LocalDate.now();
        CommentaireLivre temp = new CommentaireLivre(Statics.currentUser.getId_utilisateur(), commentairelivreInput.getText(), l.getId_livre(), java.sql.Date.valueOf(date));
        CommentaireLivreDAO lc = new CommentaireLivreDAO();
        lc.insertCommentaireLivre(temp);
    }

}
