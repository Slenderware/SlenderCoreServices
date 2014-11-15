/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.users.impl;

import com.slender.app.factory.UserProjectFactory;
import com.slender.app.factory.UserTaskFactory;
import com.slender.domain.Company;
import com.slender.domain.UserProject;
import com.slender.domain.UserTask;
import com.slender.domain.Users;
import com.slender.service.crud.CompanyCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.CompanyCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import slender.services.core.accounts.session.UserSessions;
import slender.services.core.users.UsersService;

/**
 *
 * @author Heinrich
 */
public class UsersServiceImpl implements UsersService {

    /**
     *
     * @param sessionId
     * @return
     */
    public static int getUserId(String sessionId) {
        return UserSessions.getUser(sessionId).getId();
    }
    
    /**
     *
     * @param sessionId
     * @return
     */
    @Override
    public Users getUserBySession(String sessionId) {
        Integer userId = getUserId(sessionId);
        
        UserCrud crud = new UserCrudImpl();
        return crud.findById(userId);
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public Users addUser(Users user) {
        UserCrud crud = new UserCrudImpl();
        CompanyCrud compCrud = new CompanyCrudImpl();
        
        Users newUser = crud.persist(user);
        
        Company company = compCrud.findById(user.getCompanyId());
        company.setAdminUser(user.getId());
        
        compCrud.merge(company);
        
        return newUser;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Users getUser(Integer id) {
        UserCrud crud = new UserCrudImpl();
        return crud.findById(id);
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public Users addAdminUser(Users user) {
        UserCrud crud = new UserCrudImpl();
        CompanyCrud compCrud = new CompanyCrudImpl();
        
        Users newUser = crud.persist(user);
        
        Company company = compCrud.findById(user.getCompanyId());
        company.setAdminUser(user.getId());
        
        compCrud.merge(company);
        
        return newUser;
    }

    /**
     *
     * @param userId
     * @param projId
     * @return
     */
    @Override
    public boolean addUserToProject(Integer userId, Integer projId) {
        try {
            UserProjectCrud crud = new UserProjectCrudImpl();
            UserProjectFactory factory = new UserProjectFactory();
            UserProject item = factory.getUserProject(userId, projId);

            crud.persist(item);
            
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    /**
     *
     * @param userId
     * @param taskId
     * @return
     */
    @Override
    public boolean addUserToTask(Integer userId, Integer taskId) {
        try {
            UserTaskCrud crud = new UserTaskCrudImpl();
            UserTaskFactory factory = new UserTaskFactory();
            UserTask item = factory.getUserTask(userId, taskId);

            crud.persist(item);
            
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
