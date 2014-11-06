/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.accounts.session;

import com.slender.domain.Session;
import com.slender.domain.Users;
import com.slender.service.crud.SessionCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.impl.SessionCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import java.util.List;

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
    
    public static String getNewSessions(int userId) {        
        SessionCrudImpl crud = new SessionCrudImpl();
        String sessionId = getSessionString(userId);
        
        List<Session> tmpSession = crud.getEntitiesByProperName("sessionId", sessionId);
        
        if(tmpSession.isEmpty()) {
            Session session = new Session();
            session.setUserId(userId);
            session.setId(sessionId);

            crud.persist(session);
        }
        
        return sessionId;
    }
    
    private static String getSessionString(int userId) {
        // Still in test phase
        return "session" + userId;
    }
}
