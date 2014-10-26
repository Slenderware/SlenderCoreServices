/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.users;

import com.slender.domain.Users;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public interface UsersService {
    public List<Users> getUsersFromTask(Integer taskId);
}
