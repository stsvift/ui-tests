package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ButtonsPage;

@Epic("Elements")
@Feature("Buttons")
@Owner("Степан Волков")
public class ButtonsTest extends BaseTest {

    @Test(description = "Проверка двойного клика")
    @Story("Double Click")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDoubleClickButton() {

        ButtonsPage page = new ButtonsPage(driver, wait);

        page.open();
        page.doubleClick();

        Assert.assertTrue(
                page.getDoubleClickMessage()
                        .toLowerCase()
                        .contains("double click"),
                "Сообщение после двойного клика не появилось"
        );
    }

    @Test(description = "Проверка клика правой кнопкой мыши")
    @Story("Right Click")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRightClickButton() {

        ButtonsPage page = new ButtonsPage(driver, wait);

        page.open();
        page.rightClick();

        Assert.assertTrue(
                page.getRightClickMessage()
                        .toLowerCase()
                        .contains("right click"),
                "Сообщение после правого клика не появилось"
        );
    }
}