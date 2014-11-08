/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.accounts.sessions;

import com.slender.domain.Users;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.accounts.session.UserSessions;

/**
 *
 * @author Heinrich
 */
public class UserSessionsTest {
    
    public UserSessionsTest() {
    }

    @Test
    public void testGetSession() {
        String sessionStr = UserSessions.getNewSessions("dummy");
        
        Assert.assertEquals(sessionStr, "session0");
    }
    
    @Test
    public void testGetUser() {
        Users user = UserSessions.getUser("session0");;
        
        Assert.assertEquals(user.getUsername(), "dummy");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
