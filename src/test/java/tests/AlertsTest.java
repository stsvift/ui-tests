package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

@Epic("Alerts, Frame & Windows")
@Feature("Alerts")
@Owner("Степан Волков")
public class AlertsTest extends BaseTest {

    @Test(description = "Принятие обычного alert")
    @Story("Simple Alert")
    @Severity(SeverityLevel.NORMAL)
    public void shouldAcceptAlert() {

        AlertsPage page =
                new AlertsPage(driver, wait);

        page.open();

        page.openAlertAndAccept();
    }

    @Test(description = "Отмена Confirm")
    @Story("Confirm Alert")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDismissConfirm() {

        AlertsPage page =
                new AlertsPage(driver, wait);

        page.open();

        page.dismissConfirm();

        Assert.assertTrue(
                page.getConfirmResult()
                        .toLowerCase()
                        .contains("cancel")
        );
    }

    @Test(description = "Ввод текста в Prompt")
    @Story("Prompt Alert")
    @Severity(SeverityLevel.NORMAL)
    public void shouldAcceptPromptWithText() {

        AlertsPage page =
                new AlertsPage(driver, wait);

        page.open();

        page.enterPrompt("demo");

        Assert.assertTrue(
                page.getPromptResult()
                        .contains("demo")
        );
    }
}