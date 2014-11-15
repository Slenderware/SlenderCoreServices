/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.accounts.impl;

import com.slender.domain.Users;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.impl.UserCrudImpl;
import java.util.List;
import slender.services.core.accounts.UserAuthenticationService;

/**
 *
 * @author Heinrich
 */
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public int authenticate(String username, String password) {
        UserCrud crud = new UserCrudImpl();
        List<Users> users = crud.findAll();
        
        for(Users u : users) {
            if(u.getUsername().equals(username)) {
                if(u.getPassword().equals(password)) {
                    return 0;
                }
                else {
                    return 2;
                }
            }
        }
        
        return 1;
    }
}
