package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    //Кнопка подтверждения использования куки
    private By cookieButton = By.id("rcc-confirm-button");
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookie() {
        driver.findElement(cookieButton).click();
    }

    public void clickQuestion(String questionId) {
        driver.findElement(By.id(questionId)).click();

    }

    public String getQuestionText(String questionId) {
        return driver.findElement(By.id(questionId)).getText();
    }

    public String getAnswerText(String panelId) {
        return driver.findElement(By.id(panelId)).getText();
    }
}
