/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.util;

import edu.esprit.entities.Utilisateur;
import java.sql.Date;
import edu.esprit.entities.CategorieLivre;
import edu.esprit.entities.Utilisateur;

/**
 *
 * @author Hrouz
 */
public class Statics {

    public static String imageDirectory="C:\\wamp64\\www\\libroGit\\public\\uploads\\";
    static Date tempDate = new Date(19992909);
    public static Utilisateur currentUser = new Utilisateur(1,"Tester User",tempDate,"profilepic.jpg",2,500,"test@test.test");
public static int livreid;
   public static CategorieLivre categorielivreid;
    // 8,"Tester User",tempDate,"profilepic.jpg",1,500,"test@test.test"
}
