/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.users.impl;

import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.domain.UserProject;
import com.slender.domain.UserTask;
import com.slender.domain.Users;
import com.slender.service.crud.ProjectCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.ProjectCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.webservice.services.users.UsersService;

/**
 *
 * @author Heinrich
 */
public class UsersServiceImpl implements UsersService {

    @Override
    public Users getUserBySession(String sessionId) {
        Integer userId = getUserId(sessionId);
        
        UserCrud crud = new UserCrudImpl();
        return crud.findById(userId);
    }

    @Override
    public List<Project> getUserProjects(String sessionId) {
        Integer userId = getUserId(sessionId);
        
        UserProjectCrud crud = new UserProjectCrudImpl();
        List<UserProject> userProjects = crud.getEntitiesByProperName("userId", userId);
        
        ProjectCrud projCrud = new ProjectCrudImpl();
        List<Project> projects = new ArrayList<Project>();
        
        for(UserProject p : userProjects) {
            projects.add(projCrud.findById(p.getProjectId()));
        }
        
        return projects;
    }

    @Override
    public List<Task> getUserTasks(String sessionId) {
        Integer userId = getUserId(sessionId);
        
        UserTaskCrud crud = new UserTaskCrudImpl();
        List<UserTask> userTasks = crud.getEntitiesByProperName("userId", userId);
        
        TaskCrud taskCrud = new TaskCrudImpl();
        List<Task> tasks = new ArrayList<Task>();
        
        for(UserTask t : userTasks) {
            tasks.add(taskCrud.findById(t.getTaskId()));
        }
        
        return tasks;
    }

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
    
    private int getUserId(String sessionId) {
        // Get User id from session able
        return 0;
    }
}
