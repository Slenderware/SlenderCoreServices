/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.tasks.impl;

import com.slender.domain.Attachment;
import com.slender.domain.Comment;
import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.domain.UserTask;
import com.slender.domain.Users;
import com.slender.service.crud.AttachmentCrud;
import com.slender.service.crud.CommentCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.AttachmentCrudImpl;
import com.slender.service.crud.impl.CommentCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import slender.webservice.constants.Constants;
import slender.webservice.services.tasks.TasksService;

/**
 *
 * @author Heinrich
 */
public class TasksServiceImpl implements TasksService {
    
    @Override
    public Task getTask(Integer id) {
        TaskCrud crud = new TaskCrudImpl();
        return crud.findById(id);
    }

    @Override
    public int getProgress(Integer taskId) {
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        List<TaskTime> task = crud.getEntitiesByProperName("taskId", taskId);
        
        int progress = 0;
        
        for(TaskTime t : task) {
            progress += t.getTimeSpent();
        }
        
        return progress;
    }

    @Override
    public void addProgress(Integer taskId, Integer userId, int hours) {
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        
        TaskTime taskTime = new TaskTime();
        taskTime.setCreateDate(new Date());
        taskTime.setTaskId(taskId);
        taskTime.setUserId(userId);
        taskTime.setTimeSpent(hours);
        
        crud.persist(taskTime);
    }
    
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

    @Override
    public List<Comment> getTaskComments(Integer taskId) {
        CommentCrud crud = new CommentCrudImpl();
        List<Comment> comments = crud.getEntitiesByProperName("taskId", taskId);
        return comments;
    }

    @Override
    public List<Users> getTaskUsers(Integer taskId) {
        UserTaskCrud crud = new UserTaskCrudImpl();
        List<UserTask> userTasks = crud.getEntitiesByProperName("taskId", taskId);
        
        UserCrud userCrud = new UserCrudImpl();
        List<Users> users = new ArrayList<Users>();
        
        for(UserTask u : userTasks) {
            users.add(userCrud.findById(u.getId()));
        }
        
        return users;
    }
}
