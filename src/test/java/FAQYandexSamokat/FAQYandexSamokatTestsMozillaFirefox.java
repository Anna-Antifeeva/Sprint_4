package FAQYandexSamokat;

import PageObject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQYandexSamokatTestsMozillaFirefox {

    private WebDriver driver;
    private final String questionId;
    private final String panelId;
    private final String expectedQuestion;
    private final String expectedAnswer;

    public FAQYandexSamokatTestsMozillaFirefox(String questionId, String panelId, String expectedQuestion, String expectedAnswer) {
        this.questionId = questionId;
        this.panelId = panelId;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getFaq() {
        return new Object[][] {
                {"accordion__heading-0", "accordion__panel-0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "accordion__panel-1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "accordion__panel-2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "accordion__panel-3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "accordion__panel-4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "accordion__panel-5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "accordion__panel-6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "accordion__panel-7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},

        };
    }

    @Before

    public void setup() {
        driver = new FirefoxDriver();
    }
    @Test
    public void listAboutImportantIssuesTextTest() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickQuestion(questionId);
        String questionText = mainPage.getQuestionText(questionId);
        assertEquals(expectedQuestion, questionText);

        new WebDriverWait(driver, Duration.ofSeconds(3)) // выполнено с помощью ИИ (в новой версии Selenium принимает не int, а Duration)
                .until(ExpectedConditions.attributeToBe(By.xpath(".//div[@data-accordion-component='AccordionItemPanel']"), "class", "accordion__panel"));

        String answerText = mainPage.getAnswerText(panelId);
        assertEquals(expectedAnswer, answerText);

    }


    @After
    public void quit() {
        driver.quit();
    }
}
