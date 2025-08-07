package extentions;

import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import factory.AppiumServerManager;
import factory.DriverFactory;
import factory.EmulatorManager;
import io.appium.java_client.AppiumDriver;
import module.GuiceScreenModule;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class UIExtension implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback,
    AfterAllCallback {

  protected AppiumDriver driver;
  private DriverFactory driverFactory = new DriverFactory();

  @Override
  public void beforeEach(ExtensionContext context) {
    try {
      driver = driverFactory.setUp();
      WebDriverRunner.setWebDriver(driver);
      Guice.createInjector(new GuiceScreenModule()).injectMembers(context.getTestInstance().get());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    if (driver != null) {
      driver.quit();
      WebDriverRunner.closeWebDriver();
    }
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    EmulatorManager.stopEmulator();
    AppiumServerManager.stopAppiumServer();
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    EmulatorManager.startEmulator();
    AppiumServerManager.startAppiumServer();
  }
}
