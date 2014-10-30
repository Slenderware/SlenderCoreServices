/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.tasks;

import com.slender.domain.Comment;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.io.File;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.webservice.services.tasks.TasksService;
import slender.webservice.services.tasks.impl.TasksServiceImpl;

/**
 *
 * @author Heinrich
 */
public class TasksServiceTest {
    
    public TasksServiceTest() {
    }

    @Test
    public void testGetTask() {
        TasksService service = new TasksServiceImpl();
        Task task = service.getTask(0);
        
        Assert.assertEquals(task.getTaskName(), "dummy", "Task name not equal to 'dummy'");
    }
    
    @Test
    public void testTaskComments() {
        TasksService service = new TasksServiceImpl();
        List<Comment> list = service.getTaskComments(0);
        
        Assert.assertEquals(list.size(), 1, "Size not equal to 1");
        Assert.assertEquals(list.get(0).getComment(), "dummy", "Comment not equal to 'dummy'");
    }
    
    @Test
    public void testTaskUsers() {
        TasksService service = new TasksServiceImpl();
        List<Users> list = service.getTaskUsers(0);
        
        Assert.assertEquals(list.size(), 1, "Size not equal to 1");
        Assert.assertEquals(list.get(0).getUsername(), "dummy", "Username not equal to 'dummy'");
    }
    
    @Test
    public void testTasksAttachments() {
        TasksService service = new TasksServiceImpl();
        List<File> files = service.getTaskAttachments(0);
        
        Assert.assertEquals(files.size(), 1, "Size not equal to 1");
        Assert.assertEquals(files.get(0).getName(), "dummy.txt", "dummy.txt was not found.");
    }
    
    @Test
    public void testGetProgress() {
        TasksService service = new TasksServiceImpl();
        int progress = service.getProgress(0);
        
        Assert.assertEquals(progress, 5);
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
