/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.users;

import com.slender.domain.Project;
import com.slender.domain.Task;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface UserListsService {
    public List<Project> getUserProjects(String sessionId);
    public List<Task> getUserTasks(String sessionId);
    public List<Task> getUserProjectTasks(String sessionId, Integer projectId);
}
