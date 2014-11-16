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
