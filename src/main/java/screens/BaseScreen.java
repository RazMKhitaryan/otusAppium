package screens;

import com.codeborne.selenide.SelenideElement;
import factory.DriverFactory;
import io.appium.java_client.AppiumDriver;

public abstract class BaseScreen {

  private AppiumDriver driver;

  public BaseScreen() {
    this.driver = new DriverFactory().getDriver();
  }

  public String getCurrentActivity() {
    return driver.getPageSource();
  }

  public void click(SelenideElement element) {
    element.click();
  }

  public boolean isDisplayed(SelenideElement element) {
    return element.isDisplayed();
  }
}
