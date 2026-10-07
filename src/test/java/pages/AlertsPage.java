package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage extends BasePage {

    private final By alertButton =
            By.id("alertButton");

    private final By confirmButton =
            By.id("confirmButton");

    private final By promptButton =
            By.id("promtButton");

    private final By confirmResult =
            By.id("confirmResult");

    private final By promptResult =
            By.id("promptResult");

    public AlertsPage(
            WebDriver driver,
            WebDriverWait wait
    ) {
        super(driver, wait);
    }

    @Step("Открыть страницу Alerts")
    public void open() {
        driver.get("https://demoqa.com/alerts");
    }

    @Step("Открыть Alert и нажать OK")
    public void openAlertAndAccept() {

        click(alertButton);

        wait.until(
                ExpectedConditions.alertIsPresent()
        ).accept();
    }

    @Step("Открыть Confirm и нажать Cancel")
    public void dismissConfirm() {

        click(confirmButton);

        wait.until(
                ExpectedConditions.alertIsPresent()
        ).dismiss();
    }

    @Step("Открыть Prompt, ввести {text} и нажать OK")
    public void enterPrompt(String text) {

        click(promptButton);

        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );

        alert.sendKeys(text);
        alert.accept();
    }

    public String getConfirmResult() {
        return getElement(confirmResult).getText();
    }

    public String getPromptResult() {
        return getElement(promptResult).getText();
    }
}