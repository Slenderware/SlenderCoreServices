/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.tasks.impl;

import com.slender.domain.Attachment;
import com.slender.domain.Comment;
import com.slender.domain.UserTask;
import com.slender.domain.Users;
import com.slender.service.crud.AttachmentCrud;
import com.slender.service.crud.CommentCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.AttachmentCrudImpl;
import com.slender.service.crud.impl.CommentCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import slender.services.core.constants.Constants;
import slender.services.core.tasks.TaskListsService;

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
public class TaskListsServiceImpl implements TaskListsService {
    
    /**
     * Get all attachments of a Task
     * 
     * @param taskId    ID of the task
     * @return          List of attachments
     */
    @Override
    public List<File> getTaskAttachments(Integer taskId) {
        AttachmentCrud crud = new AttachmentCrudImpl();
        List<Attachment> list = crud.getEntitiesByProperName("taskId", taskId);
        
        List<File> files = new ArrayList<>();
        
        String dir = System.getProperty("user.dir");;
        File file;
        for(Attachment a : list) {
            file = new File(dir + Constants.ATTACHMENTS_PATH + a.getAttachment());
            if(file.exists())
                files.add(file);
        }
        
        return files;
    }

    /**
     * Return all the comments for a task
     * 
     * @param taskId    ID of the task
     * @return          List of comments
     */
    @Override
    public List<Comment> getTaskComments(Integer taskId) {
        CommentCrud crud = new CommentCrudImpl();
        List<Comment> comments = crud.getEntitiesByProperName("taskId", taskId);
        return comments;
    }

    /**
     * Return the users for a task
     * 
     * @param taskId    ID of task
     * @return          List of users
     */
    @Override
    public List<Users> getTaskUsers(Integer taskId) {
        UserTaskCrud crud = new UserTaskCrudImpl();
        List<UserTask> userTasks = crud.getEntitiesByProperName("taskId", taskId);
        
        UserCrud userCrud = new UserCrudImpl();
        List<Users> users = new ArrayList<Users>();
        
        for(UserTask u : userTasks) {
            users.add(userCrud.findById(u.getUserId()));
        }
        
        return users;
    }
    
}
