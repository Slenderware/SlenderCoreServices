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
import java.util.List;
import java.util.Random;
import slender.services.core.accounts.session.SessionManager;

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
        //List<Users> users = userCrud.getEntitiesByProperName("username", username);
        List<Users> users = userCrud.findAll();
        Users user = null;
        
        for(Users u : users) {
            if(u.getUsername().equals(username)) {
                user = u;
            }
        }
        
        if(users != null) {
        
            int userId = user.getId();
            
            SessionCrudImpl crud = new SessionCrudImpl();
            String sessionId;

            List<Session> tmpSession = crud.getEntitiesByProperName("userId", userId);

            if(tmpSession.isEmpty()) {
                sessionId = getSessionString(userId);
                Session session = new Session();
                session.setUserId(userId);
                session.setId(sessionId);

                crud.persist(session);
                
                return sessionId;
            }

            return tmpSession.get(0).getId();
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
        for(int i = 0;i<40;i++) {
            rndm = randomGenerator.nextInt(9);
            sessionId += rndm;
        }
        
        sessionId += userId;
        /*try { 
            return new String(MessageDigest.getInstance("MD5").digest(sessionId.getBytes()), "UTF-8");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(SessionManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        return sessionId;
    }
}
