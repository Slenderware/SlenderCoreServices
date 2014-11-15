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
 *
 * @author Heinrich
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
