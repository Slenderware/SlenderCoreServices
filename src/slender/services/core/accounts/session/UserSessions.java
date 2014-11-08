/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.accounts.session;

import com.slender.domain.Session;
import com.slender.domain.Users;
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
        SessionCrudImpl crud = new SessionCrudImpl();
        List<Session> tmpSessions = crud.getEntitiesByProperName("id", sessionId);
        
        if(tmpSessions.size() > 0) {
            Session session = tmpSessions.get(0);

            UserCrud userCrud = new UserCrudImpl();
            return userCrud.findById(session.getUserId());
        }
        
        return null;
    }
    
    public static String getNewSessions(String username) {       
        UserCrud userCrud = new UserCrudImpl();
        List<Users> users = userCrud.getEntitiesByProperName("username", username);
        
        if(users.size() > 0) {
        
            int userId = users.get(0).getId();
            
            SessionCrudImpl crud = new SessionCrudImpl();
            String sessionId = getSessionString(userId);

            List<Session> tmpSession = crud.getEntitiesByProperName("id", sessionId);

            if(tmpSession.isEmpty()) {
                Session session = new Session();
                session.setUserId(userId);
                session.setId(sessionId);

                crud.persist(session);
            }

            return sessionId;
        }
        
        return null;
    }
    
    private static String getSessionString(int userId) {
        // Still in test phase
        return "session" + userId;
    }
}
