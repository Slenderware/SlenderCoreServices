/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.users.impl;

import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import java.util.List;
import slender.services.core.users.UsersProgressService;
import static slender.services.core.users.impl.UsersServiceImpl.getUserId;

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
public class UsersProgressServiceImpl implements UsersProgressService {

    /**
     * Get time spent for task by user in hours
     * 
     * @param sessionId The user's session ID
     * @param taskId    The task ID
     * @return          Amount of hours spent on task
     */
    @Override
    public int getTimeSpentForTask(String sessionId, Integer taskId) {
        Integer userId = getUserId(sessionId);
        
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        List<TaskTime> task = crud.getEntitiesByProperName("taskId", taskId);
        
        int timeSpent = 0;
        
        for(TaskTime t : task) {
            if(t.getUserId() ==  userId)
                timeSpent += t.getTimeSpent();
        }
        
        return timeSpent;
    }

    /**
     * Get time spent for user on project
     * 
     * @param sessionId The user's session ID
     * @param projectId The project ID
     * @return          Amount of hours spent on project
     */
    @Override
    public int getTimeSpentForProject(String sessionId, Integer projectId) {
        Integer userId = getUserId(sessionId);
        
        // Create Task Crud
        TaskCrud taskCrud = new TaskCrudImpl();
        
        // Find all tasks for project
        List<Task> tasks = taskCrud.getEntitiesByProperName("projectId", projectId);
        
        // Create TaskTime Crud
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        
        // Iterate through tasks and add each task's time spent by the user to the progress int
        List<TaskTime> taskTimes;
        int timeSpent = 0;
        for(Task t : tasks) {
            taskTimes = crud.getEntitiesByProperName("taskId", t.getId());
            
            for(TaskTime time : taskTimes) {
                if(time.getUserId() ==  userId)
                    timeSpent += time.getTimeSpent();
            }
            
        }
        
        return timeSpent;
    }
    
    /**
     * Get the percentage amount spent for task by user
     * 
     * @param sessionId The user's session ID
     * @param taskId    The task ID
     * @return          Percentage amount spent for task
     */
    @Override
    public double getPercentageSpentForTask(String sessionId, Integer taskId) {
        Integer userId = getUserId(sessionId);
        
        TaskCrud taskCrud = new TaskCrudImpl();
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        Task task = taskCrud.findById(taskId);
        List<TaskTime> tasks = crud.getEntitiesByProperName("taskId", taskId);
        
        int timeSpent = 0;
        for(TaskTime t : tasks) {
            if(t.getUserId() ==  userId)
                timeSpent += t.getTimeSpent();
        }
        
        int totalTime = task.getTimeAllocation();
        double decimal = (double)timeSpent/(double)totalTime;
        double perc = (int) (decimal * 100);
        return perc;
    }

    /**
     * Get the percentage amount spent for project by user
     * 
     * @param sessionId The user's session ID
     * @param projectId The project ID
     * @return          The percentage amount spent for project by user
     */
    @Override
    public double getPercentageSpentForProject(String sessionId, Integer projectId) {
        Integer userId = getUserId(sessionId);
        
        // Create Task Crud
        TaskCrud taskCrud = new TaskCrudImpl();
        
        // Find all tasks for project
        List<Task> tasks = taskCrud.getEntitiesByProperName("d", projectId);
        
        // Create TaskTime Crud
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        
        // Iterate through tasks and add each task's time spent by the user to the progress int
        List<TaskTime> taskTimes;
        int timeSpent = 0;
        int totalTime = 0;
        for(Task t : tasks) {
            taskTimes = crud.getEntitiesByProperName("taskId", t.getId());
            
            for(TaskTime time : taskTimes) {
                if(time.getUserId() ==  userId)
                    timeSpent += time.getTimeSpent();
                totalTime++;
            }
            
        }
        
        return (timeSpent/totalTime) * 100;
    }

    /**
     * Get time spent for task by user in hours
     * 
     * @param sessionId The user's session ID
     * @param taskId    The task ID
     * @return          Amount of hours spent on task
     */
    @Override
    public int getTimeSpentForTask(Integer userId, Integer taskId) {
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        List<TaskTime> task = crud.getEntitiesByProperName("taskId", taskId);
        
        int timeSpent = 0;
        
        for(TaskTime t : task) {
            if(t.getUserId() ==  userId)
                timeSpent += t.getTimeSpent();
        }
        
        return timeSpent;
    }

    /**
     * Get time spent for user on project
     * 
     * @param sessionId The user's session ID
     * @param projectId The project ID
     * @return          Amount of hours spent on project
     */
    @Override
    public int getTimeSpentForProject(Integer userId, Integer projectId) {
        // Create Task Crud
        TaskCrud taskCrud = new TaskCrudImpl();
        
        // Find all tasks for project
        List<Task> tasks = taskCrud.getEntitiesByProperName("projectId", projectId);
        
        // Create TaskTime Crud
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        
        // Iterate through tasks and add each task's time spent by the user to the progress int
        List<TaskTime> taskTimes;
        int timeSpent = 0;
        for(Task t : tasks) {
            taskTimes = crud.getEntitiesByProperName("taskId", t.getId());
            
            for(TaskTime time : taskTimes) {
                if(time.getUserId() ==  userId)
                    timeSpent += time.getTimeSpent();
            }
            
        }
        
        return timeSpent;
    }
    
    /**
     * Get the percentage amount spent for task by user
     * 
     * @param sessionId The user's session ID
     * @param taskId    The task ID
     * @return          Percentage amount spent for task
     */
    @Override
    public double getPercentageSpentForTask(Integer userId, Integer taskId) {
        TaskCrud taskCrud = new TaskCrudImpl();
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        Task task = taskCrud.findById(taskId);
        List<TaskTime> tasks = crud.getEntitiesByProperName("taskId", taskId);
        
        int timeSpent = 0;
        for(TaskTime t : tasks) {
            if(t.getUserId() ==  userId)
                timeSpent += t.getTimeSpent();
        }
        
        int totalTime = task.getTimeAllocation();
        double decimal = (double)timeSpent/(double)totalTime;
        double perc = (int) (decimal * 100);
        return perc;
    }

    /**
     * Get the percentage amount spent for project by user
     * 
     * @param sessionId The user's session ID
     * @param projectId The project ID
     * @return          The percentage amount spent for project by user
     */
    @Override
    public double getPercentageSpentForProject(Integer userId, Integer projectId) {
        // Create Task Crud
        TaskCrud taskCrud = new TaskCrudImpl();
        
        // Find all tasks for project
        List<Task> tasks = taskCrud.getEntitiesByProperName("d", projectId);
        
        // Create TaskTime Crud
        TaskTimeCrud crud = new TaskTimeCrudImpl();
        
        // Iterate through tasks and add each task's time spent by the user to the progress int
        List<TaskTime> taskTimes;
        int timeSpent = 0;
        int totalTime = 0;
        for(Task t : tasks) {
            taskTimes = crud.getEntitiesByProperName("taskId", t.getId());
            
            for(TaskTime time : taskTimes) {
                if(time.getUserId() ==  userId)
                    timeSpent += time.getTimeSpent();
                totalTime++;
            }
            
        }
        
        return (timeSpent/totalTime) * 100;
    }
}
