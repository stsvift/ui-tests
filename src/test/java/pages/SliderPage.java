package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SliderPage extends BasePage {

    private final By slider = By.cssSelector("input[type='range']");
    private final By sliderValue = By.id("sliderValue");

    public SliderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Slider")
    public void open() {
        driver.get("https://demoqa.com/slider");
    }

    @Step("Установить значение слайдера: {value}")
    public void setValue(int value) {
        WebElement element = getElement(slider);

        int current = Integer.parseInt(element.getAttribute("value"));

        Keys key = value > current ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

        for (int i = 0; i < Math.abs(value - current); i++) {
            element.sendKeys(key);
        }
    }

    public int getValue() {
        return Integer.parseInt(
                getElement(sliderValue).getAttribute("value")
        );
    }
}
