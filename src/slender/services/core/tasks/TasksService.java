/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.tasks;

import com.slender.domain.Comment;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.io.File;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface TasksService {
    public Task getTask(Integer id);
    public Task addTask(Task task);
}

