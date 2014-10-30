/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.users;

import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UsersServiceImpl;

/**
 *
 * @author Heinrich
 */
public class UsersServiceTest {
    
    private static String session = "sessionvariable1";
    
    public UsersServiceTest() {
    }

    @Test
    public void testUserBySession() {
        UsersService service = new UsersServiceImpl();
        Users user = service.getUserBySession(session);
        
        Assert.assertEquals(user.getUsername(), "dummy");
    }
    
    @Test
    public void testUserProjects() {
        UsersService service = new UsersServiceImpl();
        List<Project> list = service.getUserProjects(session);
        
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0).getProjectName(), "dummy");
    }

    @Test
    public void testUserTasks() {
        UsersService service = new UsersServiceImpl();
        List<Task> list = service.getUserTasks(session);
        
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getTaskName(), "dummy");
    }
    
    @Test
    public void timeSpentForTask() {
        UsersService service = new UsersServiceImpl();
        int time = service.getTimeSpentForTask(session, 0);
        
        Assert.assertEquals(time, 5);
    }
    
    @Test
    public void timeSpentForProject() {
        UsersService service = new UsersServiceImpl();
        int time = service.getTimeSpentForProject(session, 0);
        
        Assert.assertEquals(time, 5);
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
