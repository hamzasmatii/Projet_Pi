/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.Participation_evenementDAO;
import edu.esprit.dao.interfaces.IparticipationEvenementDAO;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.EvenementCalendrier;
import edu.esprit.util.Statics;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
//import jfxtras.icalendarfx.VCalendar;
//import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class CalendarController implements Initializable {

    @FXML
    private AnchorPane anchorCalendar;
    @FXML
    private BorderPane cell;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     VCalendar vCalendar = new VCalendar();
    
     Agenda a=new Agenda();
     a.applyCss();
        List<EvenementCalendrier> elements = participationToAppoitments();
        a.appointments().addAll(elements);
      /*ICalendarAgenda agenda = new ICalendarAgenda(vCalendar);
        BorderPane root = new BorderPane();*/
        
       cell.setCenter(a);
         
    }

    private  List<EvenementCalendrier> participationToAppoitments(){
        IparticipationEvenementDAO participationDao=new Participation_evenementDAO();
        List<Evenement> evenements=participationDao.fetchParticipationByUser(Statics.currentUser.getId_utilisateur());
        return EvenementCalendrier.appointmentsList(evenements);
        
    }
    
    
    
}
