/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.users.impl;

import com.slender.app.factory.UserProjectFactory;
import com.slender.app.factory.UserTaskFactory;
import com.slender.domain.Company;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.domain.UserProject;
import com.slender.domain.UserTask;
import com.slender.domain.Users;
import com.slender.service.crud.CompanyCrud;
import com.slender.service.crud.ProjectCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.CompanyCrudImpl;
import com.slender.service.crud.impl.ProjectCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.services.core.accounts.session.UserSessions;
import slender.services.core.users.UsersService;

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
        return UserSessions.getUser(sessionId).getId();
    }

    @Override
    public Users addUser(Users user) {
        UserCrud crud = new UserCrudImpl();
        CompanyCrud compCrud = new CompanyCrudImpl();
        
        Users newUser = crud.persist(user);
        
        Company company = compCrud.findById(user.getCompanyId());
        company.setAdminUser(user.getId());
        
        compCrud.merge(company);
        
        return newUser;
    }

    @Override
    public Users getUser(Integer id) {
        UserCrud crud = new UserCrudImpl();
        return crud.findById(id);
    }

    @Override
    public List<Task> getUserProjectTasks(String sessionId, Integer projectId) {
        Integer userId = getUserId(sessionId);
        
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

    @Override
    public Users addAdminUser(Users user) {
        UserCrud crud = new UserCrudImpl();
        CompanyCrud compCrud = new CompanyCrudImpl();
        
        Users newUser = crud.persist(user);
        
        Company company = compCrud.findById(user.getCompanyId());
        company.setAdminUser(user.getId());
        
        compCrud.merge(company);
        
        return newUser;
    }

    @Override
    public boolean addUserToProject(Integer userId, Integer projId) {
        try {
            UserProjectCrud crud = new UserProjectCrudImpl();
            UserProjectFactory factory = new UserProjectFactory();
            UserProject item = factory.getUserProject(userId, projId);

            crud.persist(item);
            
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean addUserToTask(Integer userId, Integer taskId) {
        try {
            UserTaskCrud crud = new UserTaskCrudImpl();
            UserTaskFactory factory = new UserTaskFactory();
            UserTask item = factory.getUserTask(userId, taskId);

            crud.persist(item);
            
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

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
}
