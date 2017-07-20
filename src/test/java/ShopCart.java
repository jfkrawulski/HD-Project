import com.sun.tools.javac.comp.Enter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by johnkrawulski on 6/6/17.
 */
public class ShopCart extends OverLay {

    JavascriptExecutor je = (JavascriptExecutor) driver;


    public static final By SHOP_CART_PAGE = By.xpath(".//div[@class='col__7-12 col__9-12--xs']");
    public static final By CART_WRAPPER = By.xpath(".//div[@class='cartItem']");
    public static final By CART_DESC = By.xpath(".//div[@class='col__8-12 col__4-12--xs']");
    public static final By SUB_TOT = By.xpath(".//li[@class='List__item list__item--padding-none u__bold col__4-12 list--right']");
    public static final By QTY = By.xpath(".//input[@class='cartItem__qtyInput form-input__field u__right padding_left-10 padding_right-10']");
    public static final By REMOVE = By.xpath(".//a[@class='list__link' and text() = 'Remove']");
    public static final By EXP_DEL = By.xpath(".//input[@value='DeliverFromStore']");
    //public static final String EXP_DEL = ".//input[@value contains (text(),'DeliverFromStore')]";

    public static final By EXP_DEL_AMT = By.xpath(".//div[@class='u__text--success']");

    String shopCart;
    String pageName = "Shopping Cart";
    String cartDesc;
    String subtotal;
    String expDelAmt;

    public boolean verifyShopCartPage(){
            waitUntilElementDisplayed(SHOP_CART_PAGE);
            shopCart = driver.findElement(SHOP_CART_PAGE).getText();
            if(shopCart.contains(pageName)){
                return true;
            }
         return false;
    }

    public boolean validateCartItemFromDescription(){
        int i=1;
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
                cartDesc = element.findElement(CART_DESC).getText();
                if(cartDesc.contains(desc)) {
                    System.out.println(cartDesc);
                    //String cartButton = insertIndexIntoXpath(ADD_TO_CART_BUTTON, i);
                    //driver.findElement(By.xpath(cartButton)).click();
                    //need to pass as a By object
                    //clickButton(cartButton);
                    return true;
                }
            }
            i=i+1;
        }
        return false;
    }

    public boolean getSubTotal(){
        if (waitUntilElementDisplayed(SUB_TOT)){
            subtotal=driver.findElement(SUB_TOT).getText();
            return true;
        }
        return false;
    }

    public boolean modifyQuantity(String qty){
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
                cartDesc = element.findElement(CART_DESC).getText();
                if(cartDesc.contains(desc)) {
                    element.findElement(QTY).clear();
                    element.findElement(QTY).sendKeys(qty);
                    element.findElement(QTY).sendKeys(Keys.TAB);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;

    }

    public boolean removeItemFromCart(){
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
                cartDesc = element.findElement(CART_DESC).getText();
                if(cartDesc.contains(desc2)) {
                    element.findElement(REMOVE).click();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean clickExpressDeliveryButton(){
        //int i=0;
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
          //      i=i+1;
                cartDesc = element.findElement(CART_DESC).getText();
                if(cartDesc.contains(desc)) {
                    //String buttonInWrapper = insertIndexIntoXpath(EXP_DEL, i);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    element.findElement(EXP_DEL).click();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getExpressDeliveryText(){
       // int i=0;
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
         //       i=i+1;
                cartDesc = element.findElement(CART_DESC).getText();
                if(cartDesc.contains(desc)) {
                    if (waitUntilElementDisplayed(EXP_DEL_AMT)){
                        expDelAmt=driver.findElement(EXP_DEL_AMT).getText();
                        //System.out.println("This is the Express Delivery amount "+expDelAmt);
                        return true;
                    }

                }
            }
        }
        return false;

    }

    public boolean removeHammerFromCart(){
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
                cartDesc = element.findElement(CART_DESC).getText();
                if(cartDesc.contains(desc)) {
                    WebElement removeElement = driver.findElement(By.xpath(".//a[@class='list__link' and text() = 'Remove']"));
                    je.executeScript("arguments[0].scrollIntoView(true);",removeElement);
                    element.findElement(REMOVE).click();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeScrewDriverFromCart(){
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
                cartDesc = element.findElement(CART_DESC).getText();
                if(cartDesc.contains(desc3)) {
                    element.findElement(REMOVE).click();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateNoItemFromDescription(String elementItem){
        int i=1;
        if(waitUntilElementDisplayed(CART_WRAPPER)){
            for (WebElement element:getElements(CART_WRAPPER)) {
                cartDesc = element.findElement(CART_DESC).getText();
                if(!cartDesc.contains(elementItem)) {
                    System.out.println(cartDesc);
                    //String cartButton = insertIndexIntoXpath(ADD_TO_CART_BUTTON, i);
                    //driver.findElement(By.xpath(cartButton)).click();
                    //need to pass as a By object
                    //clickButton(cartButton);
                    return true;
                }
            }
            i=i+1;
        }
        return false;
    }

}
