package skoda;

import com.codeborne.selenide.WebDriverRunner;
import factory.AppiumServerManager;
import factory.DriverFactory;
import factory.EmulatorManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

  protected AppiumDriver driver;
  private DriverFactory driverFactory = new DriverFactory();

  @BeforeSuite
  public void appiumStart() throws Exception {
    EmulatorManager.startEmulator();
    AppiumServerManager.startAppiumServer();
  }

  @AfterSuite
  public void killAppium() throws Exception {
    EmulatorManager.stopEmulator();
    AppiumServerManager.stopAppiumServer();
  }

  @BeforeMethod
  public void setUp() {
    try {
      driver = driverFactory.setUp();
      WebDriverRunner.setWebDriver(driver);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      WebDriverRunner.closeWebDriver();
    }
  }
}
