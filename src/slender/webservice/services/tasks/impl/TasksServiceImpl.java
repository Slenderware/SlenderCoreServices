/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.tasks.impl;

import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import java.util.List;
import slender.webservice.services.tasks.TasksService;

/**
 *
 * @author Heinrich
 */
public class TasksServiceImpl implements TasksService {

    @Override
    public List<Task> getTasks(Integer projectId) {
        TaskCrud crud = new TaskCrudImpl();
        List<Task> tasks = crud.getEntitiesByProperName("projectId", projectId);
        return tasks;
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
}
