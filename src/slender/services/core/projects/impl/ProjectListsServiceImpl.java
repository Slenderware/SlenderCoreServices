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
