package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equals("firefox")) {
            return new FirefoxDriver();
        } else {
            return new ChromeDriver();
        }
    }
}
