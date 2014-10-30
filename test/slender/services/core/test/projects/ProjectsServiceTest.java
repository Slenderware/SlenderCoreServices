/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.projects;

import com.slender.domain.Comment;
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
import slender.services.core.projects.ProjectsService;
import slender.services.core.projects.impl.ProjectsServiceImpl;

/**
 *
 * @author Heinrich
 */
public class ProjectsServiceTest {
    
    public ProjectsServiceTest() {
    }

    @Test
    public void testProject() {
        ProjectsService service = new ProjectsServiceImpl();
        Project project = service.getProject(0);
        
        Assert.assertEquals(project.getProjectName(), "dummy", "Project name not equal to 'dummy'");
    }
    
    @Test
    public void testProjectUsers() {
        ProjectsService service = new ProjectsServiceImpl();
        List<Users> users = service.getProjectUsers(0);
        
        Assert.assertEquals(users.size(), 1, "Size not equal to 1");
        Assert.assertEquals(users.get(0).getUsername(), "dummy", "Username not equal to 'dummy'");
    }
    
    @Test
    public void testProjectTasks() {
        ProjectsService service = new ProjectsServiceImpl();
        List<Task> tasks = service.getProjectTasks(0);
        
        Assert.assertEquals(tasks.size(), 2, "Size not equal to 2");
        Assert.assertEquals(tasks.get(1).getTaskName(), "dummy", "Task name not equal to 'dummy'");
    }
    
    @Test
    public void testProjectComments() {
        ProjectsService service = new ProjectsServiceImpl();
        List<Comment> comments = service.getProjectComments(0);
        
        Assert.assertEquals(comments.size(), 1, "Size not equal to 2");
        Assert.assertEquals(comments.get(0).getComment(), "dummy 2", "Comment not equal to 'dummy 2'");
    }

    @Test
    public void testProjectProgress() {
        ProjectsService service = new ProjectsServiceImpl();
        int progress = service.getProjectProgress(0);
        
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
