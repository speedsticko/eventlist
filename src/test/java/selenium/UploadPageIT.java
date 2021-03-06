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
public class UploadPageIT {
    
    @Test
    public void testSimple() throws Exception {
        
        WebDriver driver = new InternetExplorerDriver();

        driver.get("http://localhost:8080/eventlist/upload.jsp");

        // Check the title of the page
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().contains("Upload JSON Data");
            }
        });

        //Close the browser
        driver.quit();
    }
    
}
