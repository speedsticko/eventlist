/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author klim
 */
public class NewSeleneseIT {
    
    @Test
    public void testSimple() throws Exception {
         // Optional, if not specified, WebDriver will search your path for chromedriver.
        System.setProperty("webdriver.ie.driver", "C:\\Users\\klim\\Downloads\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
        
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new InternetExplorerDriver();

        // And now use this to visit NetBeans
        driver.get("http://www.netbeans.org");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.netbeans.org");

        // Check the title of the page
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().contains("Welcome to NetBeans");
            }
        });

        //Close the browser
        driver.quit();
    }
    
}
