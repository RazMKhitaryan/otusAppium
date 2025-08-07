package skoda;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.HomeScreen;

public class TestTest extends BaseTest {

  @Test
  public void drillersButtonTest() {
    Assert.assertTrue(new HomeScreen().isDrillersButtonDisplayed());
  }

  @Test
  public void supportButtonTest() {
    Assert.assertTrue(new HomeScreen().isSupportButtonDisplayed());
  }

  @Test
  public void fillProfileButtonTest() {
    Assert.assertTrue(new HomeScreen().isFillProfileButtonDisplayed());
  }
}
