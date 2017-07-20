import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by johnkrawulski on 5/25/17.
 */
public class BasicTests {

    static ShopCart utils = new ShopCart();

    public static final String HOME_PAGE = "http://www.homedepot.com";

    //DO NOT CALL UTILS FROM THIS PAGE, ALWAYS CALL METHOD IN POM!!!  REFACTOR

    @Test
    public void goGetItems(){
        Assert.assertTrue("Could not navigate to main page", utils.navigateURL(HOME_PAGE));
        System.out.println("Step #1:  Successfully navigated to "+HOME_PAGE);

        //Add syncstorelocation method

        //Assert.assertTrue("Could not find or enter into search box", utils.enterItemIntoSearchBox(utils.SEARCH_BOX, utils.item1));
        //System.out.println("Entered "+utils.item1+" into search box");

        Assert.assertTrue("Could not find or enter into search box", utils.enterTextIntoTextBox(utils.SEARCH_BOX, utils.item1));
        System.out.println("Step #2a:  Entered "+utils.item1+" into search box");

        Assert.assertTrue("Could not find button to click", utils.clickButton(utils.SEARCH_BUTTON));
        System.out.println("Step #2b:  Successfully clicked button");

        Assert.assertTrue("Could not validate Landing Page with item named "+utils.item1, utils.xpathForLandingPgValidation(utils.LANDING_PAGE, utils.item1));
        System.out.println("Step #2c:  Validated Landing Page with item named "+utils.item1);

        Assert.assertTrue("Item not priced >$10 and <$15", utils.validateItemDescriptionFromPrice());
        System.out.println("Step #3a:  Successfully found a "+utils.item1+" priced >$10 and <$15 ");

        Assert.assertTrue("Incorrect Overlay page", utils.verifyOverlayPage(utils.FRAME_OVERLAY, utils.desc));
        System.out.println("Step #3b:  Validated Overlay page content of "+utils.overlaydesc);

        Assert.assertTrue("Could not return to Landing Page", utils.continueShopping());
        System.out.println("Step #4a:  Successfully clicked Continue Shopping Button");

        Assert.assertTrue("Could not validate Landing Page with item named "+utils.item1,utils.xpathForLandingPgValidation(utils.LANDING_PAGE, utils.item1));
        System.out.println("Step #4b:  Validated Landing Page with item named "+utils.item1);

        Assert.assertTrue("Could not find or enter into search box", utils.enterTextIntoTextBox(utils.SEARCH_BOX, utils.item2));
        System.out.println("Step #5a:  Entered "+utils.item2+" into search box");

        Assert.assertTrue("Could not find button to click", utils.clickButton(utils.SEARCH_BUTTON));
        System.out.println("Step #5b:  Successfully clicked button");

        Assert.assertTrue("Could not validate Landing Page with item named "+utils.item2,utils.xpathForLandingPgValidation(utils.LANDING_PAGE, utils.item2));
        System.out.println("Step #5c:  Validated Landing Page with item named "+utils.item2);

        Assert.assertTrue("Could not find and add to cart Galvanized "+utils.desc2, utils.validateItemFromDescription());
        System.out.println("Step#6:  Validated Galvanized "+utils.desc2+" added to cart");

        Assert.assertTrue("Incorrect Overlay page", utils.verifyOverlayPage(utils.FRAME_OVERLAY, utils.desc2));
        System.out.println("Step #7:  Validated Overlay page content of "+utils.overlaydesc);

        Assert.assertTrue("Could not return to Landing Page", utils.continueShopping());
        System.out.println("Step #8a:  Successfully clicked Continue Shopping Button");

        Assert.assertTrue("Could not validate Landing Page with item named "+utils.item2,utils.xpathForLandingPgValidation(utils.LANDING_PAGE, utils.item2));
        System.out.println("Step #8b:  Validated Landing Page with item named "+utils.item2);

        Assert.assertTrue("Cart constains incorrect value", utils.retriveCartValue(utils.CART_ITEM_COUNT2, "2"));
        System.out.println("Step #8c:  Cart value correctly equals "+utils.itemCount);

        Assert.assertTrue("Could not find or enter into search box", utils.enterTextIntoTextBox(utils.SEARCH_BOX, utils.item3));
        System.out.println("Step #9a:  Entered "+utils.item3+" into search box");

        Assert.assertTrue("Could not find button to click", utils.clickButton(utils.SEARCH_BUTTON));
        System.out.println("Step #9b:  Successfully clicked button");

        Assert.assertTrue("Could not find and add to cart "+utils.desc3, utils.validateNthItemFromDescription());
        System.out.println("Step #10a:  Validated "+utils.desc3+" added to cart");

        Assert.assertTrue("Incorrect Overlay page", utils.verifyOverlayPage(utils.FRAME_OVERLAY, utils.desc3));
        System.out.println("Step #10b:  Validated Overlay page content of "+utils.overlaydesc);
        //System.out.println("Step #11:  "+utils.numOfItems+" "+utils.desc3+" in stock and available for pickup today.");

        Assert.assertTrue("Could not return to Landing Page", utils.continueShopping());
        System.out.println("Step #13a:  Successfully clicked Continue Shopping Button");

        Assert.assertTrue("Could not validate Landing Page with item named "+utils.item3,utils.xpathForLandingPgValidation(utils.LANDING_PAGE, utils.item3));
        System.out.println("Step #13b:  Validated Landing Page with item named "+utils.item3);

        Assert.assertTrue("Cart contains incorrect value", utils.retriveCartValue(utils.CART_ITEM_COUNT,    "3"));
        System.out.println("Step #13c:  Cart value correctly equals "+utils.itemCount);

        Assert.assertTrue("Could not find button to click", utils.clickButton(utils.CLICK_CART_BUTTON));
        System.out.println("Step #14a:  Successfully clicked button");

        Assert.assertTrue("Could not validate landed on "+utils.pageName+" page.", utils.verifyShopCartPage());
        System.out.println("Step 14b:  Successfully landed on "+utils.pageName+" page.");

        Assert.assertTrue("Could not find item "+utils.cartDesc, utils.validateCartItemFromDescription() );
        System.out.println("Step 14c:  Found item "+utils.cartDesc+" in Shopping Cart");

        //DO NOT CALL UTILS FROM THIS PAGE, ALWAYS CALL METHOD IN POM!!!  REFACTOR

        Assert.assertTrue("Could not find subtotal", utils.getSubTotal());
        System.out.println("Step #14d:  Subtotal equals "+utils.subtotal);

        Assert.assertTrue("Could not modify quantity", utils.modifyQuantity("2"));
        System.out.println("Step 15a:  Modified quantity");

        Assert.assertTrue("Could not find subtotal", utils.getSubTotal());
        System.out.println("Step 15b:  New subtotal equals "+utils.subtotal);

        Assert.assertTrue("Could not find item "+utils.desc2+" to remove", utils.removeItemFromCart());
        System.out.println("Step #'s 16 & 17 skipped, Step 18a:  Removed "+utils.desc2+" from cart");

        Assert.assertTrue("Could not find subtotal", utils.getSubTotal());
        System.out.println("Step 18b:  New subtotal equals "+utils.subtotal);

        Assert.assertTrue("Cart contains incorrect value of "+utils.itemCount, utils.retriveCartValue(utils.CART_ITEM_COUNT,    "3"));
        System.out.println("Step 18c:  Cart value correctly equals "+utils.itemCount);

        //Verify item has been removed from cart....

        Assert.assertTrue("Could not modify quantity", utils.modifyQuantity("1"));
        System.out.println("Step 19a:  Modified quantity");

        Assert.assertTrue("Could not find subtotal", utils.getSubTotal());
        System.out.println("Step 19b:  New subtotal equals "+utils.subtotal);

        Assert.assertTrue("Cart contains incorrect value of "+utils.itemCount, utils.retriveCartValue(utils.CART_ITEM_COUNT,    "2"));
        System.out.println("Step 19c:  Cart value correctly equals "+utils.itemCount);

        Assert.assertTrue("Unable to find Express Delivery button", utils.clickExpressDeliveryButton());
        System.out.println("Step #20a:  Successfully clicked Express Delivery Button");

        Assert.assertTrue("Unable to find express delivery amount", utils.getExpressDeliveryText());
        System.out.println("Step #20b:  Found/validated express delivery amount");

        Assert.assertTrue("Could not find item "+utils.desc+" to remove", utils.removeHammerFromCart());
        System.out.println("Step #21a:  Removed "+utils.desc+" from cart");

        Assert.assertTrue(utils.desc+" is still in the cart", utils.validateNoItemFromDescription(utils.desc));
        System.out.println("Step #21b: "+utils.desc+" is no longer in cart");

        Assert.assertTrue("Could not find item "+utils.desc3+" to remove", utils.removeScrewDriverFromCart());
        System.out.println("Step 22:  Removed "+utils.desc3+" from cart");

        //Validate no items in cart

        System.out.println("Hammer description is "+utils.desc);
        System.out.println("Nail description is "+utils.desc2);
        System.out.println("Screwdriver description is "+utils.desc3);
        System.out.println(utils.numOfItems+" "+utils.desc3+" in stock and available for pickup today.");
        System.out.println("This is the Express Delivery amount ......"+utils.expDelAmt);



    }

}
