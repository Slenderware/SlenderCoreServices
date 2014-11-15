/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.accounts.session;

import com.slender.domain.Users;
import slender.services.core.accounts.session.impl.SessionManagerImpl;

/**
 *
 * @author Heinrich
 */
public class UserSessions {
    public static Users getUser(String sessionId) {        
        SessionManager manager = new SessionManagerImpl();
        return manager.getUser(sessionId);
    }
    
    public static String getNewSessions(String username) {       
        SessionManager manager = new SessionManagerImpl();
        return manager.getNewSession(username);
    }
}
