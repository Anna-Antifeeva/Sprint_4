package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;
    //Поле "имя"
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле "фамилия"
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле "Адрес:куда привезти заказ"
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле "Станция метро"
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле "Номер телефона"
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");
    //Поле "Куда привезти самокат"
    private By orderDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Выпадающий список срока аренды
    private By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']/..");
    //Поле "Комментарий курьеру"
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Забронировать"
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //Кнопка подтверждения заказа
    private By yesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");
    // Окно об успешном заказе самоката
    private By orderModal = By.className("Order_Modal__YZ-d3");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillingFirstLoginForm(String name, String surname, String address, String metroStation, String phoneNumber) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).click();
        driver.findElement(By.xpath(".//div[text()='" + metroStation + "']")).click();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillingSecondLoginForm(String orderDate, String rentalPeriod, String colour, String comment) {
        driver.findElement(orderDateField).sendKeys(orderDate);
        driver.findElement(orderDateField).sendKeys(Keys.ENTER);
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
        driver.findElement(By.xpath(".//label[text()='" + colour + "']")).click();
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public boolean isOrderModalDisplayed() {
        return driver.findElement(orderModal).isDisplayed();
    }
}
