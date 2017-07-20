import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by johnkrawulski on 5/25/17.
 */
public class LandingPage extends MainPage{

    public String LANDING_PAGE = ".//span[@class='original-keyword " +
            "u__regular' and contains(text(),'TO_REPLACE')]";
    public String ADD_TO_CART_BUTTON = "(.//span[@class='bttn__content' and text() = 'Add To Cart'])[]";
    //public String ADD_TO_CART_BUTTON2 = ".//span[@class='bttn__content' and text() = 'Add To Cart']";

//removed static final because xpath will change....same for landing page?  try and run


    public static final By WRAPPER = By.xpath(".//div[contains(@class,'plp-pod plp-pod--default pod-item--')]");
    public static final By PRICE = By.xpath(".//div[@class='price']");
    public static final By DESCRIPTION = By.xpath(".//div[@class='pod-plp__description js-podclick-analytics']//a");
    //public static final By ADD_TO_CART_BUTTON = By.xpath((".//span[@class='bttn__content' and text() = 'Add To Cart']"));
    public static final By CART_ITEM_COUNT = By.xpath(".//span[@class='MyCart__itemCount']");
    public static final By CART_ITEM_COUNT2 = By.xpath(".//*[@id='headerCart']/div[1]/span[2]");
    public static final By NUM_IN_STOCK = By.xpath(".//span[@class='Inventory-Stock__quantity']");
    public static final By CLICK_CART_BUTTON = By.xpath(".//span[@class='MyCart__label']");


    By item;
    String desc;
    String desc2;
    String desc3;
    String numOfItems;
    //String cartValue = "2";
    String itemCount;


    //for any full xpath then:  public static final
    //incomplete xpath--- then public static (not final)
    //By item;
    //Places to modify
    //Constructor
    //setter
    //at the method being called
    //public boolean verify.....(var for xpath. .....)
    // build xpath (string ITEM, arg to 'finish'
    // string = buildtItmXpath
    // item=By.xpath(string);

    public boolean xpathForLandingPgValidation(String page, String stuff){
        String str = page;
        str = str.replace("TO_REPLACE", stuff);
        item = By.xpath(str);
        if(super.verifyLandingPage(item)){
            return true;
        }
        return false;
    }



    public boolean validateItemDescriptionFromPrice(){
        int i=1;
        if(waitUntilElementDisplayed(WRAPPER)){
            for (WebElement element:getElements(WRAPPER)) {
                String price = element.findElement(PRICE).getText();
                double thePrice = Double.parseDouble(price.replaceAll("[^0-9]", ""))/100;
                if(thePrice > 10 && thePrice < 15){
                    desc = element.findElement(DESCRIPTION).getText();
                    System.out.println(desc);
                    System.out.println(thePrice);
                    String cartButton = insertIndexIntoXpath(ADD_TO_CART_BUTTON, i);
                    driver.findElement(By.xpath(cartButton)).click();
                    //driver.findElement(By.xpath(ADD_TO_CART_BUTTON2)).click();


                    //need to pass as a By object
                    //clickButton(cartButton);
                    return true;
                }
                i=i+1;

            }
        }
        return false;
    }

    public boolean validateItemFromDescription(){
        int i=1;
        if(waitUntilElementDisplayed(WRAPPER)){
            for (WebElement element:getElements(WRAPPER)) {
                    desc2 = element.findElement(DESCRIPTION).getText();
                    if(desc2.contains("Galvanized")) {
                        System.out.println(desc2);
                        String cartButton = insertIndexIntoXpath(ADD_TO_CART_BUTTON, i);
                        driver.findElement(By.xpath(cartButton)).click();
                        //need to pass as a By object
                        //clickButton(cartButton);
                        return true;
                    }
                    }
                i=i+1;
        }
        return false;
    }

    public boolean validateNthItemFromDescription(){
        int i=0;
        int c=0;
        if(waitUntilElementDisplayed(WRAPPER)){
            for (WebElement element:getElements(WRAPPER)) {
                desc3 = element.findElement(DESCRIPTION).getText();
                c=c+1;
                if(desc3.contains("Husky")) {
                    i=i+1;
                    if (i==2) {
                        //System.out.println(desc3);
                        numOfItems = element.findElement(NUM_IN_STOCK).getText();
                        //System.out.println("Step #9c:  "+numOfItems+" "+desc3+" available in store.");
                        String cartButton = insertIndexIntoXpath(ADD_TO_CART_BUTTON, c);
                        driver.findElement(By.xpath(cartButton)).click();
                        //need to pass as a By object
                        //clickButton(cartButton);
                        return true;
                    }
                }
            }

        }
        return false;
    }



    public boolean retriveCartValue(By cartCount, String cartVal){
        if(waitUntilElementDisplayed(cartCount)){
            itemCount=driver.findElement(cartCount).getText();
            if (itemCount.equals(cartVal)){
                return true;
            }
        }return false;
    }

}
