/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao.interfaces;

import edu.esprit.entities.CommentaireLivre;
import java.util.List;

/**
 *
 * @author aziz karoui
 */
public interface ICommentaireLivre {
    void insertCommentaireLivre(CommentaireLivre com) ;

    void updateCommentaireLivre(CommentaireLivre com);

    void deleteCommentaireLivre(int id);

    CommentaireLivre findCommentaireLivreById(int id);

    List<CommentaireLivre> DisplayAllCommentaireLivre();  
    
    
}
