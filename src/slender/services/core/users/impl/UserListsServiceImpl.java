/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.users.impl;

import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.UserProject;
import com.slender.domain.UserTask;
import com.slender.service.crud.ProjectCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.ProjectCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.services.core.users.UserListsService;

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
public class UserListsServiceImpl implements UserListsService {

    /**
     * Get projects for user using the session ID
     * 
     * @param sessionId The user's session ID
     * @return          List of projects
     */
    @Override
    public List<Project> getUserProjects(String sessionId) {
        Integer userId = UsersServiceImpl.getUserId(sessionId);
        
        UserProjectCrud crud = new UserProjectCrudImpl();
        List<UserProject> userProjects = crud.getEntitiesByProperName("userId", userId);
        
        ProjectCrud projCrud = new ProjectCrudImpl();
        List<Project> projects = new ArrayList<Project>();
        
        for(UserProject p : userProjects) {
            projects.add(projCrud.findById(p.getProjectId()));
        }
        
        return projects;
    }

    /**
     * Get tasks for a user for a specific project
     * by using the user's session ID and project ID
     * 
     * @param sessionId The user's session ID
     * @param projectId The project ID
     * @return          List of tasks
     */
    @Override
    public List<Task> getUserProjectTasks(String sessionId, Integer projectId) {
        Integer userId = UsersServiceImpl.getUserId(sessionId);
        
        UserTaskCrud crud = new UserTaskCrudImpl();
        List<UserTask> userTasks = crud.getEntitiesByProperName("userId", userId);
        
        TaskCrud taskCrud = new TaskCrudImpl();
        List<Task> tasks = new ArrayList<Task>();
        Task task;
        for(UserTask t : userTasks) {
            task = taskCrud.findById(t.getTaskId());
            if(task.getProjectId() == projectId)
                tasks.add(task);
        }
        
        return tasks;
    }
    
    /**
     * Gets all the tasks of a user using the user's session ID
     * 
     * @param sessionId The user's session ID
     * @return          List of tasks
     */
    @Override
    public List<Task> getUserTasks(String sessionId) {
        Integer userId = UsersServiceImpl.getUserId(sessionId);
        
        UserTaskCrud crud = new UserTaskCrudImpl();
        List<UserTask> userTasks = crud.getEntitiesByProperName("userId", userId);
        
        TaskCrud taskCrud = new TaskCrudImpl();
        List<Task> tasks = new ArrayList<Task>();
        
        for(UserTask t : userTasks) {
            tasks.add(taskCrud.findById(t.getTaskId()));
        }
        
        return tasks;
    }
}
