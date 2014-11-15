/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.users;

import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface UsersService {
    public Users getUser(Integer id);
    public Users getUserBySession(String sessionId);
    public Users addUser(Users user);
    public Users addAdminUser(Users user);
    public boolean addUserToProject(Integer userId, Integer projId);
    public boolean addUserToTask(Integer userId, Integer taskId);
}
