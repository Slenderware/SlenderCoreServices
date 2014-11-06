/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.projects;

import com.slender.domain.Comment;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface ProjectsService {
    public Project getProject(Integer id);
    public List<Users> getProjectUsers(Integer projId);
    public List<Task> getProjectTasks(Integer projId);
    public List<Comment> getProjectComments(Integer projId);
    public int getProjectProgress(Integer projId);
    public Project addProject(Project project);
}
