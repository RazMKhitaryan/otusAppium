package example;

import org.testng.annotations.Test;

public class TestTest extends BaseTest {

  @Test
  public void test() {
    getDriver().getPageSource();
    System.out.println("app launched");
  }

}
