import org.openqa.selenium.By;

/**
 * Created by johnkrawulski on 5/31/17.
 */
public class OverLay extends LandingPage {

    public static final By OVERLAY = By.xpath(".//*[contains(text(),'Added to Cart')]");
    public static final By FRAME_OVERLAY = By.xpath("(.//iframe[@class='thd-overlay-frame'])[2]");
    public static final By OVERLAY_DESC = By.xpath("(.//a[@target='_top'])[1]");
    public static final By CONT_SHOPPING = By.xpath("(.//a[@class='u__default-link'])[1]");
    public static final By BLANK = By.xpath("");

    String overlaydesc = "";


    public boolean verifyOverlayPage(By element, String str){
        if(switchDriver("iframe", element)){
            waitUntilElementDisplayed(OVERLAY);
            overlaydesc = driver.findElement(OVERLAY_DESC).getText();
            if(str.equals(overlaydesc)){
                switchDriver("",BLANK);
                return true;
            }
        } return false;
    }

    public boolean continueShopping(){
        switchDriver("iframe", FRAME_OVERLAY);
        driver.findElement(CONT_SHOPPING).click();
        switchDriver("",BLANK);
        return true;
    }

    //switch driver, do stuff
    //and switch driver back when done.....

}
