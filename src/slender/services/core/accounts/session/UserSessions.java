/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.accounts.session;

import com.slender.domain.Users;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.impl.UserCrudImpl;

/**
 *
 * @author Heinrich
 */
public class UserSessions {
    public static Users getUser(String sessionId) {        
        UserCrud crud = new UserCrudImpl();
        
        // Find user attached to session id and return user
        
        return crud.findById(0);
    }
}
