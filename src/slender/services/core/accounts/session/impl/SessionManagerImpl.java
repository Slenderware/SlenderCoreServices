/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.accounts.session.impl;

import com.slender.domain.Session;
import com.slender.domain.Users;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.impl.SessionCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import slender.services.core.accounts.session.SessionManager;

/**
 *
 * @author Heinrich
 */
public class SessionManagerImpl implements SessionManager {
    
    /**
     * Uses the username as an input to create the appropriate session in the database and to return the session key.
     * 
     * @param username  The username of the user
     * @return          The new session ID
     */
    @Override
    public String getNewSession(String username) {
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
    
    /**
     * Returns the user depending on a session ID
     * 
     * @param sessionId The session ID of the user
     * @return          The User
     */
    @Override
    public Users getUser(String sessionId) {        
        SessionCrudImpl crud = new SessionCrudImpl();
        List<Session> tmpSessions = crud.getEntitiesByProperName("id", sessionId);
        
        if(tmpSessions.size() > 0) {
            Session session = tmpSessions.get(0);

            UserCrud userCrud = new UserCrudImpl();
            return userCrud.findById(session.getUserId());
        }
        
        return null;
    }
    
    private static String getSessionString(int userId) {
        String sessionId = "";
        
        int rndm;
        Random randomGenerator = new Random();
        for(int i = 0;i<100;i++) {
            rndm = randomGenerator.nextInt(100);
            sessionId += rndm;
        }
        
        sessionId += userId;
        try { 
            return new String(MessageDigest.getInstance("MD5").digest(sessionId.getBytes()), "UTF-8");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(SessionManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sessionId;
    }
}
