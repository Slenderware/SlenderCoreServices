/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.projects.impl;

import com.slender.domain.Comment;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.domain.UserProject;
import com.slender.domain.Users;
import com.slender.service.crud.CommentCrud;
import com.slender.service.crud.ProjectCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.impl.CommentCrudImpl;
import com.slender.service.crud.impl.ProjectCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.services.core.projects.ProjectsService;

/**
 *
 * @author Heinrich
 */
public class ProjectsServiceImpl implements ProjectsService {

    @Override
    public Project getProject(Integer id) {
        ProjectCrud crud = new ProjectCrudImpl();
        return crud.findById(id);
    }

    @Override
    public List<Users> getProjectUsers(Integer projId) {
        UserProjectCrud crud = new UserProjectCrudImpl();
        List<UserProject> userProjects = crud.getEntitiesByProperName("projectId", projId);
        
        UserCrud userCrud = new UserCrudImpl();
        List<Users> users = new ArrayList<Users>();
        
        for(UserProject u : userProjects) {
            users.add(userCrud.findById(u.getId()));
        }
        
        return users;
    }

    @Override
    public List<Task> getProjectTasks(Integer projId) {
        TaskCrud crud = new TaskCrudImpl();
        return crud.getEntitiesByProperName("projectId", projId);
    }

    @Override
    public List<Comment> getProjectComments(Integer projId) {
        CommentCrud crud = new CommentCrudImpl();
        return crud.getEntitiesByProperName("projectId", projId);
    }

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

    @Override
    public Project addProject(Project project) {
       ProjectCrud crud = new ProjectCrudImpl();
       return crud.persist(project);
    }     
}
