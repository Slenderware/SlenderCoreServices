/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.comments;

import com.slender.domain.Comment;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface CommentsService {
    public List<Comment> getComments(Integer taskId);
}
