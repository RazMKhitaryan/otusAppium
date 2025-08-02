package tests;

import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {

   @Test
   public void appShouldOpen() {
      getDriver().getPageSource();
      System.out.println("App launched.");
   }

}
