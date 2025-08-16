package skoda;

import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import factory.AppiumServerManager;
import factory.DriverFactory;
import factory.EmulatorManager;
import io.appium.java_client.AppiumDriver;
import module.GuiceScreenModule;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestBase {

  protected AppiumDriver driver;
  private DriverFactory driverFactory = new DriverFactory();

  /** Runs once before all tests in the suite */
  @BeforeSuite
  public void beforeSuite() throws Exception {
    // Start Emulator and Appium Server once before suite
    EmulatorManager.startEmulator();
    AppiumServerManager.startAppiumServer();
  }

  /** Runs before each test method */
  @BeforeMethod
  public void beforeEach() {
    try {
      Guice.createInjector(new GuiceScreenModule()).injectMembers(this);
      driver = driverFactory.setUp();
      WebDriverRunner.setWebDriver(driver);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Runs after each test method */
  @AfterMethod
  public void afterEach() {
    if (driver != null) {
      driver.quit();
      WebDriverRunner.closeWebDriver();
    }
  }

  /** Runs once after all tests in the suite */
  @AfterSuite
  public void afterSuite() throws Exception {
    AppiumServerManager.stopAppiumServer();
    EmulatorManager.stopEmulator();
  }
}
