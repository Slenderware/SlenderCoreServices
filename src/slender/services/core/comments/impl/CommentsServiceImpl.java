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
 * @author Heinrich van den Ende
 * 
 *  The MIT License (MIT)

    Copyright © 2014 Slenderware

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
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
