/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.projects;

import com.slender.domain.Project;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.webservice.services.projects.ProjectsService;
import slender.webservice.services.projects.impl.ProjectsServiceImpl;

/**
 *
 * @author Heinrich
 */
public class ProjectsServiceTest {
    
    public ProjectsServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testGetComments() {
        ProjectsService service = new ProjectsServiceImpl();
        List<Project> projects = service.getProjects(0);
        
        Assert.assertEquals(projects.size(), 2, "Size not equal to 2");
        Assert.assertEquals(projects.get(0).getProjectName(), "dummy", "Project name not equal to \"dummy\"");
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
