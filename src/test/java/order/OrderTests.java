package order;

import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String orderDate;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;
    private final String buttonType;
    private final boolean checkFormOpened;
    private final String browser;

    public OrderTests(String name, String surname, String address, String metroStation, String phoneNumber, String orderDate, String rentalPeriod, String colour, String comment, String buttonType, boolean checkFormOpened, String browser) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
        this.buttonType = buttonType;
        this.checkFormOpened = checkFormOpened;
        this.browser = browser;
    }

    @Parameterized.Parameters

    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Анна", "Петрова", "Москва, ул. Ленина 87, 35", "Черкизовская", "+79999999999", "16.10.2026", "сутки", "серая безысходность", "Нет комментариев", "верхняя", true, "chrome"},
                {"Максим", "Эдурадов", "Волгоград, ул. Псковская 33, 66", "Чистые пруды", "+78247389922", "25.12.2026", "пятеро суток", "чёрный жемчуг", "кап", "нижняя", false, "chrome"},
                {"Анна", "Петрова", "Москва, ул. Ленина 87, 35", "Черкизовская", "+79999999999", "16.10.2026", "сутки", "серая безысходность", "Нет комментариев", "верхняя", true, "firefox"},
                {"Максим", "Эдурадов", "Волгоград, ул. Псковская 33, 66", "Чистые пруды", "+78247389922", "25.12.2026", "пятеро суток", "чёрный жемчуг", "кап", "нижняя", false, "firefox"},
        };
    }


    @Before

    public void setup() {

        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }

    @Test
    public void orderButtonTop() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();

        if(buttonType.equals("верхняя")) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomButtonOrder();
        }

        if (checkFormOpened) {
        orderPage.fillingFirstLoginForm(name, surname, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillingSecondLoginForm(orderDate, rentalPeriod, colour, comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        assertTrue(orderPage.isOrderModalDisplayed());
        } else {
            assertTrue(orderPage.firstOrderOpen());
        }
    }

    @After
    public void quit() {
        driver.quit();
    }
}


