package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TextBoxPage;

@Epic("Elements")
@Feature("Text Box")
@Owner("Степан Волков")
public class TextBoxTest extends BaseTest {

    @Test(description = "Отправка формы с корректными данными")
    @Story("Valid form")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldSubmitValidData() {

        TextBoxPage page =
                new TextBoxPage(driver, wait);

        page.open();

        page.fill(
                "Stepan Volkov",
                "stepan@example.com",
                "Moscow",
                "Moscow"
        );

        Assert.assertTrue(
                page.isOutputVisible(),
                "Блок результата не появился"
        );
    }

    @Test(description = "Проверка невалидного email")
    @Story("Invalid email")
    @Severity(SeverityLevel.NORMAL)
    public void shouldHighlightInvalidEmail() {

        TextBoxPage page =
                new TextBoxPage(driver, wait);

        page.open();

        page.fill(
                "Stepan Volkov",
                "wrong-email",
                "Moscow",
                "Moscow"
        );

        String cssClass =
                page.getEmailClass();

        Assert.assertTrue(
                cssClass.contains("error") ||
                        cssClass.contains("invalid"),
                "Email не отмечен как ошибочный"
        );
    }
}