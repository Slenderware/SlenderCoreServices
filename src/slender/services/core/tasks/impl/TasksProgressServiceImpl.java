/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.tasks.impl;

import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import java.util.Date;
import java.util.List;
import slender.services.core.tasks.TasksProgressService;

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
public class TasksProgressServiceImpl implements TasksProgressService {
    /**
    * Get the progress of a task in hours
    *
     * @param taskId    ID of the task
     * @return          Hours spent for task
    * @deprecated use {@link #getProgressPercentage(Integer taskId)} instead.  
    */
    @Deprecated
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
    
    /**
     * Get the progress percentage done for a task
     * 
     * @param taskId    ID of the task
     * @return          Percentage spent for task
     */
    @Override
    public double getProgressPercentage(Integer taskId) {
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        TaskCrud taskCrud = new TaskCrudImpl();
        
        List<TaskTime> tasks = crud.getEntitiesByProperName("taskId", taskId);
        Task task = taskCrud.findById(taskId);
        
        double progress = 0;
        
        for(TaskTime t : tasks) {
            progress += t.getTimeSpent();
        }
        
        int totalTime = task.getTimeAllocation();
        double decimal = (double)progress/(double)totalTime;
        double perc = (int) (decimal * 100);
        return perc;
    }

    /**
     * Add progress to a task for a user using the hours spent on it
     * 
     * @param taskId    ID of the task
     * @param userId    ID of the responsible user
     * @param hours     Hours spent on the task
     */
    @Override
    public void addProgress(Integer taskId, Integer userId, int hours) {
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        
        List<TaskTime> taskTimes = crud.getEntitiesByProperName("taskId", taskId);
        
        if(taskTimes.size() == 0) {
            TaskCrud taskCrud = new TaskCrudImpl();
            Task task = taskCrud.findById(taskId);
            task.setStartDate(new Date());
            task.setStatusId(2);
            
            taskCrud.merge(task);
        }
        
        TaskTime taskTime = new TaskTime();
        taskTime.setCreateDate(new Date());
        taskTime.setTaskId(taskId);
        taskTime.setUserId(userId);
        taskTime.setTimeSpent(hours);
        
        crud.persist(taskTime);
    }
    
    /**
     * Mark a task as completed
     * 
     * @param taskId    ID of the task
     * @return          Boolean stating if successful
     */
    @Override
    public boolean markAsComplete(Integer taskId) {
        try {
            TaskCrud crud = new TaskCrudImpl();

            Task task = crud.findById(taskId);
            task.setEndDate(new Date());
            task.setStatusId(3);

            crud.merge(task);
            
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
