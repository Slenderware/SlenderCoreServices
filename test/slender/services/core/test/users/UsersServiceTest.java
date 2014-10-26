/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.users;

import com.slender.domain.Users;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.webservice.services.users.UsersService;
import slender.webservice.services.users.impl.UsersServiceImpl;

/**
 *
 * @author Heinrich
 */
public class UsersServiceTest {
    
    public UsersServiceTest() {
    }

    @Test
    public void testGetUsersOfTask() {
        UsersService service = new UsersServiceImpl();
        List<Users> users = service.getUsersFromTask(0);
        
        Assert.assertEquals(users.size(), 1, "Size not equal to 1");
        Assert.assertEquals(users.get(0).getUsername(), "dummy", "Comment content not equal to \"dummy\"");
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
