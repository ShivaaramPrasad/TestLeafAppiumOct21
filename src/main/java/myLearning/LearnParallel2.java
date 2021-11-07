package myLearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class LearnParallel2 {
	@Test
	public void runCode() throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("app", System.getProperty("user.dir") + "\\APKs\\MultiTouch Tester_v1.2.apk");
		dc.setCapability("deviceName", "Lokesh");
		dc.setCapability("systemPort", "8225");
		dc.setCapability("udid", "emulator-5556");
		// dc.setCapability("noReset", true);
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		Thread.sleep(5000);
		TouchAction<?> action = new TouchAction<>(driver);
		Dimension size = driver.manage().window().getSize();
		int maxY = size.getHeight();
		int maxX = size.getWidth();
		// Swipe up
		action.press(PointOption.point((int) (maxX * 0.5), (int) (maxY * 0.8)))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point((int) (maxX * 0.5), (int) (maxY * 0.2))).release().perform();
		Thread.sleep(3000);
		// Swipe down
		action.press(PointOption.point((int) (maxX * 0.5), (int) (maxY * 0.2)))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point((int) (maxX * 0.5), (int) (maxY * 0.8))).release().perform();
		Thread.sleep(3000);
		// Swipe left
		action.press(PointOption.point((int) (maxX * 0.8), (int) (maxY * 0.5)))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point((int) (maxX * 0.2), (int) (maxY * 0.5))).release().perform();
		Thread.sleep(3000);
		// Swipe right
		action.press(PointOption.point((int) (maxX * 0.2), (int) (maxY * 0.5)))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point((int) (maxX * 0.8), (int) (maxY * 0.5))).release().perform();
		driver.closeApp();
	}
}
