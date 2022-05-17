/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;
import edu.esprit.entities.Commentaire_blog;
import java.util.List;

/**
 *
 * @author user
 */
public interface ICommentaire_blog {
     
     void insertCommentaire_blog(Commentaire_blog commentaire);

    void updateCommentaire_Blog(Commentaire_blog c);

    void deleteCommentaire_blog(int id);
 //
    
     List<Commentaire_blog> DisplayAllCommentaire_blog();

}
