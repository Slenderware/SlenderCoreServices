/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.tasks.impl;

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
import slender.services.core.constants.Constants;
import slender.services.core.tasks.TasksService;

/**
 *
 * @author Heinrich
 */
public class TasksServiceImpl implements TasksService {
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Task getTask(Integer id) {
        TaskCrud crud = new TaskCrudImpl();
        return crud.findById(id);
    }

    /**
     *
     * @param task
     * @return
     */
    @Override
    public Task addTask(Task task) {
        TaskCrud crud = new TaskCrudImpl();
        return crud.persist(task);
    }
}
