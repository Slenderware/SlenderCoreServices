/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.projects;

import com.slender.domain.Project;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface ProjectsService {
    public List<Project> getProjects(Integer userId);
}
