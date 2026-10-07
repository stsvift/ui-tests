package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ButtonsPage extends BasePage {

    private final By doubleClickButton = By.id("doubleClickBtn");
    private final By rightClickButton = By.id("rightClickBtn");

    private final By doubleClickMessage = By.id("doubleClickMessage");
    private final By rightClickMessage = By.id("rightClickMessage");

    public ButtonsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Buttons")
    public void open() {
        driver.get("https://demoqa.com/buttons");
    }

    @Step("Выполнить двойной клик")
    public void doubleClick() {
        WebElement button = getElement(doubleClickButton);
        scrollToCenter(button);

        new Actions(driver)
                .moveToElement(button)
                .pause(Duration.ofMillis(200))
                .doubleClick(button)
                .perform();

        if (driver.findElements(doubleClickMessage).isEmpty()) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new MouseEvent('dblclick', " +
                            "{bubbles:true, cancelable:true, view:window}));",
                    button
            );
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(doubleClickMessage));
    }

    @Step("Выполнить клик правой кнопкой мыши")
    public void rightClick() {
        WebElement button = getElement(rightClickButton);
        scrollToCenter(button);

        new Actions(driver)
                .moveToElement(button)
                .pause(Duration.ofMillis(200))
                .contextClick(button)
                .perform();

        // Fallback для Chromium/Edge при нестабильном contextClick на DemoQA.
        if (driver.findElements(rightClickMessage).isEmpty()) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new MouseEvent('contextmenu', " +
                            "{bubbles:true, cancelable:true, view:window, button:2}));",
                    button
            );
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(rightClickMessage));
    }

    public String getDoubleClickMessage() {
        return getElement(doubleClickMessage).getText();
    }

    public String getRightClickMessage() {
        return getElement(rightClickMessage).getText();
    }

    private void scrollToCenter(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                element
        );
    }
}
