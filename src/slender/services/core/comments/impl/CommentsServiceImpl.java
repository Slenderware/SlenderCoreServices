/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.comments.impl;

import com.slender.domain.Comment;
import com.slender.service.crud.CommentCrud;
import com.slender.service.crud.impl.CommentCrudImpl;
import slender.services.core.comments.CommentsService;

/**
 *
 * @author Heinrich
 */
public class CommentsServiceImpl implements CommentsService {

    /**
     * Get the comment depending on an ID.
     * 
     * @param commentId ID of comment
     * @return          The comment
     */
    @Override
    public Comment getComment(Integer commentId) {
        CommentCrud crud = new CommentCrudImpl();
        return crud.findById(commentId);
    }

    /**
     * Add a comment, given a comment object.
     * 
     * @param comment   The comment object
     * @return          Return ID populated comment
     */
    @Override
    public Comment addComment(Comment comment) {
        CommentCrud crud = new CommentCrudImpl();
        return crud.persist(comment);
    }

}
