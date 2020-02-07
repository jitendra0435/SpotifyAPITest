import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Script {

    @Test
    public void test(){

        System.setProperty("webdriver.chrome.driver","/home/admin1/IdeaProjects/PowerFunction/driver/chromedriver");
        WebDriver driver=new ChromeDriver();
        driver.get("https://chromedriver.storage.googleapis.com");

    }

}
