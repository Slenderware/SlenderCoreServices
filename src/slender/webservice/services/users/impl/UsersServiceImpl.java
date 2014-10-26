/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.services.users.impl;

import com.slender.domain.UserProject;
import com.slender.domain.UserTask;
import com.slender.domain.Users;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import java.util.ArrayList;
import java.util.List;
import slender.webservice.services.users.UsersService;

/**
 *
 * @author Heinrich
 */
public class UsersServiceImpl implements UsersService {

    @Override
    public List<Users> getUsersFromTask(Integer taskId) {
        UserTaskCrud crud = new UserTaskCrudImpl();
        List<UserTask> userTasks = crud.getEntitiesByProperName("taskId", taskId);
        
        UserCrud userCrud = new UserCrudImpl();
        List<Users> users = new ArrayList<Users>();
        
        for(UserTask u : userTasks) {
            users.add(userCrud.findById(u.getId()));
        }
        
        return users;
    }
    
}
