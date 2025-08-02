package tests;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

  private static ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

  @BeforeMethod
  public void setUp() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "Samsung Galaxy S6");
    capabilities.setCapability("automationName", "UiAutomator2");
    capabilities.setCapability("app", "/root/tmp/skoda.apk");
    capabilities.setCapability("autoGrantPermissions", true);
    capabilities.setCapability("noReset", true);
    capabilities.setCapability("adbExecTimeout", 60000);
    capabilities.setCapability("uiautomator2ServerInstallTimeout", 60000);

    URL remoteUrl = new URL("http://45.132.17.22:4723/wd/hub");
    RemoteWebDriver driver = new RemoteWebDriver(remoteUrl, capabilities);
    DRIVER.set(driver);
  }

  @AfterMethod
  public void tearDown() {
    if (DRIVER.get() != null) {
      DRIVER.get().quit();
      DRIVER.remove();
    }
  }

  @Test
  public void appShouldOpen() {
    System.out.println("App launched.");
  }
}