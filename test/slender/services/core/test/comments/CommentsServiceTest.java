/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.comments;

import com.slender.domain.Comment;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.webservice.services.comments.CommentsService;
import slender.webservice.services.comments.impl.CommentsServiceImpl;

/**
 *
 * @author Heinrich
 */
public class CommentsServiceTest {
    
    public CommentsServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testGetComments() {
        CommentsService service = new CommentsServiceImpl();
        List<Comment> comments = service.getComments(0);
        
        Assert.assertEquals(comments.size(), 1, "Size not equal to 1");
        Assert.assertEquals(comments.get(0).getComment(), "dummy", "Comment content not equal to \"dummy\"");
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
