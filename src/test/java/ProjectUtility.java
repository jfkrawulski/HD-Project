import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by johnkrawulski on 5/24/17.
 */
public class ProjectUtility {

    //public static final int MAX_TIME = 10;
    //public static final int TIME_INTERVAL=3;
    public WebDriver driver;

    ProjectUtility(){
        //Setting Chrome driver properties
        System.setProperty("webdriver.chrome.driver", "/Users/johnkrawulski/IdeaProjects/JK HD Project/chromedriver");
        //Completing instantiation
        driver = new ChromeDriver();
    }

    // Selenium command for navigating to a page
    public boolean navigateURL(String url){
        try{
            driver.get(url); // Selenium command
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean waitUntilElementDisplayed(By expression){
        int counter = 0;
        do {
            if (verifyDisplayed(expression)){
                return true;
            }
            else if (counter > 3){
                if(verifyEnabled(expression)){
                    return true;
                }

                else if (verifyLocation(expression)){
                    return true;
                }
            }

            counter++;
            try {
                TimeUnit.MILLISECONDS.sleep(950);
            }catch(Exception e){
                return false;
            }
        } while (counter < 20);

        return false;
    }


    public boolean verifyDisplayed(By expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(expression).isDisplayed()) {
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }


    public boolean verifyEnabled(By expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(expression).isEnabled()) {
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }


    public boolean verifyLocation(By expression){
        try{
            TimeUnit.MILLISECONDS.sleep(50);
            if(driver.findElement(expression).getLocation().x < 0 ||
                    driver.findElement(expression).getLocation().y < 0){
                return true;
            }
        }catch(Exception ne){
            return false;
        }
        return false;
    }

    public boolean enterTextIntoTextBox( By element, String strText){

        try {
            if (waitUntilElementDisplayed(element)) {
                //driver.findElement(By.xpath(element)).clear();
                try {
                    driver.findElement(element).clear();
                    driver.findElement(element).sendKeys(strText);
                }catch(Exception e){
                    System.out.println("Here is the exception.");
                }
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean clickButton(By element){
        if(waitUntilElementDisplayed(element)){
            try {
                syncElement("MILLISECONDS",100); //Syncing
                driver.findElement(element).click(); // command for click
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    public boolean verifyLandingPage(By path){
        // Make sure element is displayed on landing page
        try{
            if(waitUntilElementDisplayed(path)){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void syncElement(String time, int amount){
        try {
            switch (time.toUpperCase()) {
                case "MILLI":
                case "MILLISECONDS":
                    TimeUnit.MILLISECONDS.sleep(amount);
                    break;
                case "SEC":
                case "SECONDS":
                    TimeUnit.SECONDS.sleep(amount);
                    break;
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public List<WebElement> getElements(By xpath){
        try{
            List<WebElement> list = driver.findElements(xpath);
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String insertIndexIntoXpath(String xpath, int index){
        return xpath.replace("[]","["+ index +"]");
    }

    public boolean switchDriver(String target, By iFrame){
        if(target.toUpperCase().equals("IFRAME")){
            try {
                driver.switchTo().frame(driver.findElement(iFrame));
                return true;
            }catch(Exception e){
                return false;
            }
        }
        else{
            try {
                driver.switchTo().defaultContent();
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }


}
