/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.comments.impl;

import com.slender.domain.Comment;
import com.slender.service.crud.CommentCrud;
import com.slender.service.crud.impl.CommentCrudImpl;
import java.util.List;
import slender.webservice.services.comments.CommentsService;

/**
 *
 * @author Heinrich
 */
public class CommentsServiceImpl implements CommentsService {

    @Override
    public List<Comment> getComments(Integer taskId) {
        CommentCrud crud = new CommentCrudImpl();
        List<Comment> comments = crud.getEntitiesByProperName("taskId", taskId);
        return comments;
    }
    
}
