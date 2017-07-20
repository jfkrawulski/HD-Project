import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by johnkrawulski on 5/25/17.
 */
public class MainPage extends ProjectUtility {

    public static final By STORE_LOCATOR = By.xpath(".//*[@class='MyStore__store']");
    public static final By SEARCH_BOX = By.xpath(".//input[@id='headerSearch']");
    public static final By SEARCH_BUTTON = By.xpath(".//button[@id='headerSearchButton']");

    private static Map<String, String> productSearch = new HashMap<>();


    String item1 = "hammer";
    String item2 = "nail";
    String item3 = "screwdriver";


    public boolean syncStoreLocation(){
        int counter = 0;
        do{
            // Grabs text from element
            try {
                String text = driver.findElement(STORE_LOCATOR).getText();
                if (!text.contains("Choose")) {
                    return true;
                }
                syncElement("MILLISECONDS", 100);
                counter++;
            }catch(Exception e){
                continue;
            }
        }while(counter < 100);
        return false;
    }

    public String getDescription(String item){
        return productSearch.get(item);
    }

    public void setDescription(String item, String desc){

        productSearch.put(item, desc);
    }


}
