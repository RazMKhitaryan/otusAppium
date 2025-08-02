package tests;

import io.appium.java_client.AppiumDriver;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

  private static ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

  @BeforeMethod
  public void setUp() throws Exception {
    String runType = System.getProperty("runType", "remote").toLowerCase();

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("appium:automationName", "UiAutomator2");
    capabilities.setCapability("appium:autoGrantPermissions", true);
    capabilities.setCapability("appium:noReset", false);
    capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 180000); // 3 minutes
    capabilities.setCapability("appium:adbExecTimeout", 180000);

    URL remoteUrl;
    if ("remote".equals(runType)) {
      capabilities.setCapability("appium:deviceName", "emulator-5554");
      capabilities.setCapability("appium:app", "/root/tmp/skoda.apk");
      remoteUrl = new URL("http://45.132.17.22:4723/wd/hub");
    } else {
      capabilities.setCapability("appium:deviceName", "emulator-5554");
      String appPath = System.getProperty("user.dir") + "/src/test/java/resources/skoda.apk";
      capabilities.setCapability("appium:app", appPath);
      remoteUrl = new URL("http://127.0.0.1:4723");
    }

    AppiumDriver driver = new AppiumDriver(remoteUrl, capabilities);
    DRIVER.set(driver);
  }

  @AfterMethod
  public void tearDown() {
    if (DRIVER.get() != null) {
      DRIVER.get().quit();
      DRIVER.remove();
    }
  }

  public WebDriver getDriver() {
    return DRIVER.get();
  }
}
