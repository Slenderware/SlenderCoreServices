/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.projects.impl;

import com.slender.domain.Comment;
import com.slender.domain.Task;
import com.slender.domain.UserProject;
import com.slender.domain.Users;
import com.slender.service.crud.CommentCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.impl.CommentCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.services.core.projects.ProjectListsService;

/**
 *
 * @author Heinrich
 */
public class ProjectListsServiceImpl implements ProjectListsService {
    
    /**
     * Returns a list of users of a project.
     * 
     * @param projId    The ID of the project
     * @return          The list of users
     */
    @Override
    public List<Users> getProjectUsers(Integer projId) {
        UserProjectCrud crud = new UserProjectCrudImpl();
        List<UserProject> userProjects = crud.getEntitiesByProperName("projectId", projId);
        
        UserCrud userCrud = new UserCrudImpl();
        List<Users> users = new ArrayList<Users>();
        
        for(UserProject u : userProjects) {
            users.add(userCrud.findById(u.getUserId()));
        }
        
        return users;
    }

    /**
     * Returns a list of tasks of a project
     * 
     * @param projId    The project ID
     * @return          The list of tasks
     */
    @Override
    public List<Task> getProjectTasks(Integer projId) {
        TaskCrud crud = new TaskCrudImpl();
        return crud.getEntitiesByProperName("projectId", projId);
    }

    /**
     * Returns a list of comments for a project
     * 
     * @param projId    ID of the project
     * @return          The list of comments
     */
    @Override
    public List<Comment> getProjectComments(Integer projId) {
        CommentCrud crud = new CommentCrudImpl();
        return crud.getEntitiesByProperName("projectId", projId);
    }
}
