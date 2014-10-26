/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.projects.impl;

import com.slender.domain.Project;
import com.slender.domain.UserProject;
import com.slender.domain.Users;
import com.slender.service.crud.ProjectCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.impl.ProjectCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.webservice.services.projects.ProjectsService;

/**
 *
 * @author Heinrich
 */
public class ProjectsServiceImpl implements ProjectsService {

    @Override
    public List<Project> getProjects(Integer userId) {
        UserProjectCrud crud = new UserProjectCrudImpl();
        List<UserProject> userProjects = crud.getEntitiesByProperName("userId", userId);
        
        ProjectCrud projCrud = new ProjectCrudImpl();
        List<Project> projects = new ArrayList<Project>();
        
        for(UserProject p : userProjects) {
            projects.add(projCrud.findById(p.getProjectId()));
        }
        
        return projects;
    }
    
}
