/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.accounts;

import javax.ws.rs.core.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.webservice.services.accounts.UserAuthenticationService;
import slender.webservice.services.accounts.impl.UserAuthenticationServiceImpl;

/**
 *
 * @author Heinrich
 */
public class UserAuthenticationTest {
    
    public UserAuthenticationTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testUserAuthentication() {
        UserAuthenticationService service = new UserAuthenticationServiceImpl();
        
        // Test for successful authentication
        int response = service.authenticate("dummy", "dummy");
        Assert.assertEquals(response, 0);
        
        // Test for failed authentication (wrong password)
        response = service.authenticate("dummy", "qwe");
        Assert.assertEquals(response, 2);
        
        // Test for failed authentication (invalid username)
        response = service.authenticate("asd", "asd");
        Assert.assertEquals(response, 1);
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
