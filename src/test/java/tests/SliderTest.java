package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SliderPage;

@Epic("Widgets")
@Feature("Slider")
@Owner("Степан Волков")
public class SliderTest extends BaseTest {

    @Test(description = "Изменение значения слайдера")
    @Story("Slider value")
    @Severity(SeverityLevel.NORMAL)
    public void shouldChangeSliderValue() {

        SliderPage page = new SliderPage(driver, wait);

        page.open();
        page.setValue(75);

        Assert.assertEquals(
                page.getValue(),
                75,
                "Значение слайдера отличается от ожидаемого"
        );
    }
}
