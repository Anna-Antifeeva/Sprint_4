package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    //Кнопка подтверждения использования куки
    private static final By cookieButton = By.id("rcc-confirm-button");

    //Верхняя кнопка "Заказать"
    private static final By topButtonOrder = By.xpath(".//div[@class='Header_Header__214zg']//button[text()='Заказать']");

    //Нижняя кнопка "Заказать"
    private static final By bottomButtonOrder = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookie() {
        driver.findElement(cookieButton).click();
    }

    public void clickQuestion(String questionId) {
        driver.findElement(By.id(questionId)).click();

    }

    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickButtonOrder(String buttonType) {
        if (buttonType.equals("верхняя")) {
            driver.findElement(topButtonOrder).click();
        } else {
            driver.findElement(bottomButtonOrder).click();
        }
    }

    public String getAnswerText(String panelId) {
        return driver.findElement(By.id(panelId)).getText();
    }
}
