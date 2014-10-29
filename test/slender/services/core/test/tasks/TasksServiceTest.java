/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.tasks;

import com.slender.domain.Task;
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
    public void testGetTasksOfProject() {
        TasksService service = new TasksServiceImpl();
        List<Task> tasks = service.getTasks(0);
        
        Assert.assertEquals(tasks.size(), 2, "Size not equal to 2");
        Assert.assertEquals(tasks.get(1).getTaskName(), "dummy", "Comment content not equal to \"dummy\"");
    }
    
    @Test
    public void testGetTasksAttachments() {
        TasksService service = new TasksServiceImpl();
        List<File> files = service.getTaskAttachments(0);
        
        Assert.assertEquals(files.size(), 1, "Size not equal to 1");
        Assert.assertEquals(files.get(0).getName(), "dummy.txt", "dummy.txt was not found.");
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
