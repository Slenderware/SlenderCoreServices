/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.projects;

import com.slender.domain.Project;

/**
 *
 * @author Heinrich
 */
public interface ProjectsService {
    public Project getProject(Integer id);
    public Project addProject(Project project);
}
