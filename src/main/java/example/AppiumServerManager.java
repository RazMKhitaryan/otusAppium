package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class AppiumServerManager {

  private static final String APPIUM_LOG = "appium.log";
  private static final String APPIUM_PID_FILE = "appium.pid";

  public static void startAppiumServer() throws Exception {
    String runType = System.getProperty("runType", "local");
    if (runType == null || runType.equals("remote")) {
      return;
    }
    ProcessBuilder pb = new ProcessBuilder("bash", "-c",
        "appium --log-level info > " + APPIUM_LOG + " 2>&1 & echo $! > " + APPIUM_PID_FILE);
    pb.directory(new File(System.getProperty("user.dir")));
    pb.start();
    Thread.sleep(5000);
    System.out.println("Appium server started.");
  }

  public static void stopAppiumServer() throws Exception {
    String runType = System.getProperty("runType", "local");
    if (runType == null || runType.equals("remote")) {
      return;
    }
    File pidFile = new File(APPIUM_PID_FILE);
    if (pidFile.exists()) {
      try (BufferedReader reader = new BufferedReader(
          new InputStreamReader(new FileInputStream(pidFile), StandardCharsets.UTF_8))) {
        String pid = reader.readLine();

        if (pid != null && !pid.isEmpty()) {
          ProcessBuilder pb = new ProcessBuilder("bash", "-c", "kill " + pid);
          pb.start().waitFor();
          if (!pidFile.delete()) {
            System.err.println("Warning: Failed to delete PID file " + pidFile.getAbsolutePath());
          }
          System.out.println("Appium server stopped.");
        }
      }
    } else {
      System.out.println("Appium PID file not found. Is Appium running?");
    }
  }
}
