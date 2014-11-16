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
 * @author Heinrich van den Ende
 * 
 *  The MIT License (MIT)

    Copyright © 2014 Slenderware

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
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
