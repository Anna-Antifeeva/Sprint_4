package OrderYandexSamokat;

import PageObject.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderInGoogleChrome {
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

    public OrderInGoogleChrome(String name, String surname, String address, String metroStation, String phoneNumber, String orderDate, String rentalPeriod, String colour, String comment, String buttonType) {
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
    }

    @Parameterized.Parameters

    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Анна", "Петрова", "Москва, ул. Ленина 87, 35", "Черкизовская", "+79999999999", "16.10.2026", "сутки", "серая безысходность", "Нет комментариев", "верхняя"},
                {"Максим", "Эдурадов", "Волгоград, ул. Псковская 33, 66", "Чистые пруды", "+78247389922", "25.12.2026", "пятеро суток", "чёрный жемчуг", "кап", "верхняя"},
                {"Букля", "Поттеровна", "Хогвардс", "Спортивная", "+65778827592", "31.12.2026", "трое суток", "серая безысходность", "Полетели", "нижняя"},
                {"Винни", "Пух", "Полянка", "Университет", "+67855478536", "15.12.2026", "двое суток", "серая безысходность", "шарик", "нижняя"},
        };
    }


    @Before

    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void orderButtonTop() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderPage orderPage = new OrderPage(driver);
        driver.findElement(By.id("rcc-confirm-button")).click();

       if(buttonType.equals("верхняя")) {
           driver.findElement(By.xpath(".//div[@class='Header_Header__214zg']//button[text()='Заказать']")).click();
       } else {
           driver.findElement(By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']")).click();
       }

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


