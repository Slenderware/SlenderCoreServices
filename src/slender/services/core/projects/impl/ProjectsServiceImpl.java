/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.projects.impl;

import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.TaskTime;
import com.slender.service.crud.ProjectCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.TaskTimeCrud;
import com.slender.service.crud.impl.ProjectCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.TaskTimeCrudImpl;
import java.util.List;
import slender.services.core.projects.ProjectsService;

/**
 *
 * @author Heinrich
 */
public class ProjectsServiceImpl implements ProjectsService {

    /**
     * Return a project, given the ID.
     * 
     * @param id    ID of project
     * @return      Project entity object
     */
    @Override
    public Project getProject(Integer id) {
        ProjectCrud crud = new ProjectCrudImpl();
        return crud.findById(id);
    }

    /**
     * Adds a new project to database
     * 
     * @param project   Project project entity object
     * @return          ID populated project entity object
     */
    @Override
    public Project addProject(Project project) {
        ProjectCrud crud = new ProjectCrudImpl();
        return crud.persist(project);
    }     
}
