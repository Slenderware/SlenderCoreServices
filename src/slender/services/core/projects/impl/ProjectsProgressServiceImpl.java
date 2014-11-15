/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.projects.impl;

import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import java.util.List;
import slender.services.core.projects.ProjectsProgressService;

/**
 *
 * @author Heinrich
 */
public class ProjectsProgressServiceImpl implements ProjectsProgressService {

    /**
     * Get the overall progress of a projet in hours.
     * 
     * @param projId    The ID of the project
     * @return          The amount of hours
     */
    @Override
    public int getProjectProgress(Integer projId) {
        // Create Task Crud
        TaskCrud taskCrud = new TaskCrudImpl();
        
        // Find all tasks for project
        List<Task> tasks = taskCrud.getEntitiesByProperName("projectId", projId);
        
        // Create TaskTime Crud
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        
        // Iterate through tasks and add each task's time spent by the user to the progress int
        List<TaskTime> taskTimes;
        int timeSpent = 0;
        for(Task t : tasks) {
            taskTimes = crud.getEntitiesByProperName("taskId", t.getId());
            
            for(TaskTime time : taskTimes) {
                timeSpent += time.getTimeSpent();
            }
        }
        
        return timeSpent;
    }
}
