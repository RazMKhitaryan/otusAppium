package skoda;


import static org.testng.AssertJUnit.assertTrue;

import jakarta.inject.Inject;
import org.testng.annotations.Test;
import screens.HomeScreen;

public class SkodaTest extends TestBase {

  @Inject
  HomeScreen homeScreen;

  @Test
  public void drillersButtonTest() {
    assertTrue(homeScreen.isDrillersButtonDisplayed());
  }

  @Test
  public void supportButtonTest() {
    assertTrue(homeScreen.isSupportButtonDisplayed());
  }

  @Test
  public void fillProfileButtonTest() {
    assertTrue(homeScreen.isFillProfileButtonDisplayed());
  }

  @Test
  public void servicesButtonTest() {
    assertTrue(homeScreen.isServicesButtonDisplayed());
  }

}
