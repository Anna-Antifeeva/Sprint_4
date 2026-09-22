package order;

import org.openqa.selenium.By;
import pages.DriverFactory;
import pages.MainPage;
import pages.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

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
    private final By orderButtonLocator;

    public OrderTests(String name, String surname, String address, String metroStation, String phoneNumber, String orderDate, String rentalPeriod, String colour, String comment, By orderButtonLocator) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
        this.orderButtonLocator = orderButtonLocator;
    }

    @Parameterized.Parameters

    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Анна", "Петрова", "Москва, ул. Ленина 87, 35", "Черкизовская", "+79999999999", "16.10.2026", "сутки", "серая безысходность", "Нет комментариев", By.xpath(".//div[@class='Header_Header__214zg']//button[text()='Заказать']")},
                {"Максим", "Эдурадов", "Волгоград, ул. Псковская 33, 66", "Чистые пруды", "+78247389922", "25.12.2026", "пятеро суток", "чёрный жемчуг", "кап",By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']")},
        };
    }


    @Before

    public void setup() {

        driver = DriverFactory.getDriver();
    }

    @Test
    public void orderSamokat() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickCookie();
        driver.findElement(orderButtonLocator).click();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillingFirstLoginForm(name, surname, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillingSecondLoginForm(orderDate, rentalPeriod, colour, comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        assertTrue(orderPage.isOrderModalDisplayed());

    }

    @After
    public void quit() {
        driver.quit();
    }
}


