package wrappers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CommonWrappers {
	public AppiumDriver<WebElement> driver;

	public boolean launchApp(String platformName, String deviceName, String udid, String appPackage, String appActivity,
			String automationName, String chromeDriverPort, String systemPort, String xcodeOrgId, String xcodeSigningId,
			String bundleId, String app, String mjpegServerPort, String wdaLocalPort) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			if (!automationName.equals(""))
				dc.setCapability("automationName", automationName);
			if (!udid.equals(""))
				dc.setCapability("udid", udid);
			if (!app.equals(""))
				dc.setCapability("app", app);
			// Android
			if (!appPackage.equals(""))
				dc.setCapability("appPackage", appPackage);
			if (!appActivity.equals(""))
				dc.setCapability("appActivity", appActivity);
			if (!chromeDriverPort.equals(""))
				dc.setCapability("chromedriverPort", chromeDriverPort);
			if (!mjpegServerPort.equals(""))
				dc.setCapability("mjpegServerPort", mjpegServerPort);
			if (!systemPort.equals(""))
				dc.setCapability("systemPort", systemPort);
			// iOS
			if (!wdaLocalPort.equals(""))
				dc.setCapability("wdaLocalPort", wdaLocalPort);
			if (!xcodeOrgId.equals(""))
				dc.setCapability("xcodeOrgId", xcodeOrgId);
			if (!xcodeSigningId.equals(""))
				dc.setCapability("xcodeSigningId", xcodeSigningId);
			if (!bundleId.equals(""))
				dc.setCapability("bundleId", bundleId);
			// Mandatory desired capabilities
			dc.setCapability("platformName", platformName);
			dc.setCapability("deviceName", deviceName);
			// Comment the below line based on need
			dc.setCapability("noReset", true);
			if (platformName.equalsIgnoreCase("Android")) {
				// Comment the below line based on need
				dc.setCapability("autoGrantPermissions", true);
				driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			} else if (platformName.equalsIgnoreCase("iOS")) {
				// Comment the below line based on need
				dc.setCapability("autoAcceptAlerts", true);
				driver = new IOSDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean launchBrowser(String platformName, String browserName, String deviceName, String URL, String udid,
			String chromeDriverPort, String wdaLocalPort, String mjpegServerPort, String webkitDebugProxyPort) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			if (!udid.equals(""))
				dc.setCapability("udid", udid);
			// Android
			if (!chromeDriverPort.equals(""))
				dc.setCapability("chromedriverPort", chromeDriverPort);
			if (!mjpegServerPort.equals(""))
				dc.setCapability("mjpegServerPort", mjpegServerPort);
			// iOS
			if (!wdaLocalPort.equals(""))
				dc.setCapability("wdaLocalPort", wdaLocalPort);
			// Mandatory desired capabilities
			dc.setCapability("browserName", browserName);
			dc.setCapability("deviceName", deviceName);
			dc.setCapability("platformName", platformName);
			// Comment the below line based on need
			dc.setCapability("noReset", true);
			if (platformName.equalsIgnoreCase("Android")) {
				// Comment the below line based on need
				dc.setCapability("autoGrantPermissions", true);
				driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			} else if (platformName.equalsIgnoreCase("iOS")) {
				if (!webkitDebugProxyPort.equals(""))
					dc.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);
				// Comment the below line based on need
				dc.setCapability("autoAcceptAlerts", true);
				dc.setCapability("startIWDP", true);
				dc.setCapability("nativeWebTap", true);
				dc.setCapability("automationName", "XCUITest");
				driver = new IOSDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			}
			driver.get(URL);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean verifyAndInstallApp(String bundleIdOrAppPackage, String appPath) {
		boolean bInstallSuccess = false;

		if (driver.isAppInstalled(bundleIdOrAppPackage))
			driver.removeApp(bundleIdOrAppPackage);

		driver.installApp(appPath);
		bInstallSuccess = true;

		return bInstallSuccess;
	}

	public void sleep(int mSec) {
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void printContext() {
		try {
			Set<String> contexts = driver.getContextHandles();
			for (String string : contexts) {
				System.out.println(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean switchContext(String Context) {
		try {
			driver.context(Context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean switchWebview() {
		try {
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				if (contextName.contains("WEBVIEW"))
					driver.context(contextName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean switchNativeview() {
		try {
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				if (contextName.contains("NATIVE_APP"))
					driver.context(contextName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public enum Locators {
		ID("id"), NAME("name"), CLASS_NAME("className"), LINK_TEXT("link"), PARTIAL_LINK_TEXT("partialLink"),
		TAG_NAME("tag"), CSS("css"), XPATH("xpath"), ACCESSIBILITY_ID("accessibilityId");

		private String value;

		private Locators(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
	}

	public WebElement getWebElement(String locator, String locValue) {
		try {
			switch (locator) {
			case "id":
				// return driver.findElement(MobileBy.id(locValue));
				return driver.findElement(MobileBy.xpath("//*[@id='" + locValue + "']"));
			case "name":
				// return driver.findElement(MobileBy.name(locValue));
				return driver.findElement(MobileBy.xpath("//*[@name='" + locValue + "']"));
			case "className":
				return driver.findElement(MobileBy.className(locValue));
			case "link":
				return driver.findElement(MobileBy.linkText(locValue));
			case "partialLink":
				return driver.findElement(MobileBy.partialLinkText(locValue));
			case "tag":
				return driver.findElement(MobileBy.tagName(locValue));
			case "css":
				return driver.findElement(MobileBy.cssSelector(locValue));
			case "xpath":
				return driver.findElement(MobileBy.xpath(locValue));
			case "accessibilityId":
				return driver.findElement(MobileBy.AccessibilityId(locValue));
			}
		} catch (Exception e) {

		}
		return null;
	}

	public long takeScreenShot() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			File srcFiler = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFiler, new File("./reports/images/" + number + ".png"));
		} catch (WebDriverException e) {
			e.printStackTrace();
			System.err.println("The browser has been closed.");
		} catch (IOException e) {
			System.err.println("The snapshot could not be taken");
		}
		return number;
	}

	public boolean eleIsDisplayed(WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean verifyText(WebElement ele, String Expected) {
		boolean val = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
			String name = ele.getText();
			if (name.equals(Expected)) {
				val = true;
			} else
				val = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	// Applicable only for Mobile Web/Browser
	public boolean scrollDownInBrowser(int val) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0," + val + "\")", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// Applicable only for Mobile Web/Browser
	public boolean navigateBackInBrowser() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean scrollUsingTouchActions(int startX, int startY, int endX, int endY) {
		try {
//			new TouchAction<>(driver).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endX, endY)).release().perform();
			new MultiTouchAction(driver).add(new TouchAction<>(driver).press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endX, endY))
					.release()).perform();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	private boolean scrollUsingPointerInput(int startX, int startY, int endX, int endY) {
		try {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);
			sequence.addAction(
					finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
			sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(
					finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY));
			sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(sequence));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void doubleTapUsingTouchActions(int x, int y) {
		MultiTouchAction touch = new MultiTouchAction(driver);
		touch.add(new TouchAction<>(driver).press(PointOption.point(x, y)).release()
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).press(PointOption.point(x, y)).release())
				.perform();

	}

	public void doubleTapUsingPointerInput(int x, int y) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence doubleTap = new Sequence(finger, 1);
		doubleTap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
		doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(200)));
		doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(200)));
		doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(200)));
		doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(doubleTap));
	}

	public void pinchUsingPointerInput() {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

		Dimension size = driver.manage().window().getSize();
		Point source = new Point(size.getWidth(), size.getHeight());

		Sequence pinch = new Sequence(finger, 1);
		pinch.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x / 3,
				source.y / 3));
		pinch.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		pinch.addAction(new Pause(finger, Duration.ofMillis(100)));
		pinch.addAction(finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), source.x / 2,
				source.y / 2));
		pinch.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Sequence pinch2 = new Sequence(finger2, 1);
		pinch2.addAction(finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
				source.x * 3 / 4, source.y * 3 / 4));
		pinch2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		pinch2.addAction(new Pause(finger, Duration.ofMillis(100)));
		pinch2.addAction(finger2.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), source.x / 2,
				source.y / 2));
		pinch2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(pinch, pinch2));
	}

	// Pinch using Touch Action will not work. Known Bug.
	public void pinchUsingTouchActions() {
		Dimension size = driver.manage().window().getSize();
		Point source = new Point(size.getWidth(), size.getHeight());
		MultiTouchAction multiTouch = new MultiTouchAction(driver);
		TouchAction<?> tAction0 = new TouchAction<>(driver);
		TouchAction<?> tAction1 = new TouchAction<>(driver);
		tAction0.press(PointOption.point(source.x / 3, source.y / 3))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(source.x / 2, source.y / 2)).release();
		tAction1.press(PointOption.point(source.x * 3 / 4, source.y * 3 / 4))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(source.x / 2, source.y / 2)).release();
		multiTouch.add(tAction0).add(tAction1).perform();
	}

	public void zoomUsingPointerInput() {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

		Dimension size = driver.manage().window().getSize();
		Point source = new Point(size.getWidth(), size.getHeight());

		Sequence zoom = new Sequence(finger, 1);
		zoom.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x / 2,
				source.y / 2));
		zoom.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		zoom.addAction(new Pause(finger, Duration.ofMillis(100)));
		zoom.addAction(finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), source.x / 3,
				source.y / 3));
		zoom.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Sequence zoom2 = new Sequence(finger2, 1);
		zoom2.addAction(finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x / 2,
				source.y / 2));
		zoom2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		zoom2.addAction(new Pause(finger, Duration.ofMillis(100)));
		zoom2.addAction(finger2.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(),
				source.x * 3 / 4, source.y * 3 / 4));
		zoom2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(zoom, zoom2));
	}

	// Zoom using Touch Action will not work. Known Bug.
	public void zoomUsingTouchActions() {
		Dimension size = driver.manage().window().getSize();
		Point source = new Point(size.getWidth(), size.getHeight());
		MultiTouchAction multiTouch = new MultiTouchAction(driver);
		TouchAction<?> tAction0 = new TouchAction<>(driver);
		TouchAction<?> tAction1 = new TouchAction<>(driver);
		tAction0.press(PointOption.point(source.x / 2, source.y / 2))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(source.x / 3, source.y / 3)).release();
		tAction1.press(PointOption.point(source.x * 2, source.y * 2))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(source.x * 3 / 4, source.y * 3 / 4)).release();
		multiTouch.add(tAction0).add(tAction1).perform();
	}

	public boolean swipeUpInAppUsingTouchActions() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.5);
		int startY = (int) (size.getHeight() * 0.8);
		int endX = (int) (size.getWidth() * 0.5);
		int endY = (int) (size.getHeight() * 0.2);
		return scrollUsingTouchActions(startX, startY, endX, endY);
	}

	public boolean swipeUpinAppUsingPointerInput() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.5);
		int startY = (int) (size.getHeight() * 0.8);
		int endX = (int) (size.getWidth() * 0.5);
		int endY = (int) (size.getHeight() * 0.2);
		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeDownInAppUsingTouchActions() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.5);
		int startY = (int) (size.getHeight() * 0.2);
		int endX = (int) (size.getWidth() * 0.5);
		int endY = (int) (size.getHeight() * 0.8);
		return scrollUsingTouchActions(startX, startY, endX, endY);

	}

	public boolean swipeDownInAppUsingPointerInput() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.5);
		int startY = (int) (size.getHeight() * 0.2);
		int endX = (int) (size.getWidth() * 0.5);
		int endY = (int) (size.getHeight() * 0.8);
		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeLeftInAppUsingPointerInput() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.8);
		int startY = (int) (size.getHeight() * 0.5);
		int endX = (int) (size.getWidth() * 0.2);
		int endY = (int) (size.getHeight() * 0.5);
		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeLeftInAppUsingTouchActions() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.8);
		int startY = (int) (size.getHeight() * 0.5);
		int endX = (int) (size.getWidth() * 0.2);
		int endY = (int) (size.getHeight() * 0.5);
		return scrollUsingTouchActions(startX, startY, endX, endY);
	}

	public boolean swipeRightInAppUsingPointerInput() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.2);
		int startY = (int) (size.getHeight() * 0.5);
		int endX = (int) (size.getWidth() * 0.8);
		int endY = (int) (size.getHeight() * 0.5);
		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeRightInAppUsingTouchActions() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.2);
		int startY = (int) (size.getHeight() * 0.5);
		int endX = (int) (size.getWidth() * 0.8);
		int endY = (int) (size.getHeight() * 0.5);
		return scrollUsingTouchActions(startX, startY, endX, endY);
	}

	public boolean swipeDowninAppWithWebElementUsingTouchActions(WebElement ele) {

		// int eleStartX = ele.getLocation().getX();
		int eleStartY = ele.getLocation().getY();

		int eleCenterX = ((MobileElement) ele).getCenter().getX();
		int eleCenterY = ((MobileElement) ele).getCenter().getY();
		// int eleEndX = (centerX*2)-eleStartX;
		int eleEndY = (eleCenterY - eleStartY) * 2;

		int startX = (int) eleCenterX;
		int startY = (int) ((eleEndY - eleStartY) * 0.2) + eleStartY;
		int endX = (int) eleCenterX;
		int endY = (int) ((eleEndY - eleStartY) * 0.8) + eleStartY;

		return scrollUsingTouchActions(startX, startY, endX, endY);
	}

	public boolean swipeDowninAppWithWebElementUsingPointerInput(WebElement ele) {

		// int eleStartX = ele.getLocation().getX();
		int eleStartY = ele.getLocation().getY();

		int eleCenterX = ((MobileElement) ele).getCenter().getX();
		int eleCenterY = ((MobileElement) ele).getCenter().getY();
		// int eleEndX = (centerX*2)-eleStartX;
		int eleEndY = (eleCenterY - eleStartY) * 2;

		int startX = (int) eleCenterX;
		int startY = (int) ((eleEndY - eleStartY) * 0.2) + eleStartY;
		int endX = (int) eleCenterX;
		int endY = (int) ((eleEndY - eleStartY) * 0.8) + eleStartY;

		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeUpinAppWithWebElementUsingPointerInput(WebElement ele) {

		// int eleStartX = ele.getLocation().getX();
		int eleStartY = ele.getLocation().getY();

		int eleCenterX = ((MobileElement) ele).getCenter().getX();
		int eleCenterY = ((MobileElement) ele).getCenter().getY();
		// int eleEndX = (centerX*2)-eleStartX;
		int eleEndY = (eleCenterY * 2) - eleStartY;

		int startX = (int) eleCenterX;
		int startY = (int) ((eleEndY - eleStartY) * 0.8) + eleStartY;
		int endX = (int) eleCenterX;
		int endY = (int) ((eleEndY - eleStartY) * 0.2) + eleStartY;

		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeRightinAppWithWebElementUsingPointerInput(WebElement ele) {

		int eleStartX = ele.getLocation().getX();
		// int eleStartY = ele.getLocation().getY();

		int centerX = ((MobileElement) ele).getCenter().getX();
		int centerY = ((MobileElement) ele).getCenter().getY();
		int eleEndX = (centerX * 2) - eleStartX;
		// int eleEndY = (centerY*2)-eleStartY;

		int startX = (int) ((eleEndX - eleStartX) * 0.2) + eleStartX;
		int startY = (int) centerY;
		int endX = (int) ((eleEndX - eleStartX) * 0.8) + eleStartX;
		int endY = (int) centerY;

		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeLeftinAppWithWebElementUsingPointerInput(WebElement ele) {

		int eleStartX = ele.getLocation().getX();
		// int eleStartY = ele.getLocation().getY();

		int centerX = ((MobileElement) ele).getCenter().getX();
		int centerY = ((MobileElement) ele).getCenter().getY();
		int eleEndX = (centerX * 2) - eleStartX;
		// int eleEndY = (centerY*2)-eleStartY;

		int startX = (int) ((eleEndX - eleStartX) * 0.8) + eleStartX;
		int startY = (int) centerY;
		int endX = (int) ((eleEndX - eleStartX) * 0.2) + eleStartX;
		int endY = (int) centerY;

		return scrollUsingPointerInput(startX, startY, endX, endY);
	}

	public boolean swipeUpinAppUsingPointerInputUntilElementIsVisible(String locator, String locValue) {
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeUpinAppUsingPointerInput();
		}
		return true;
	}

	public boolean swipeDowninAppUsingPointerInputUntilElementIsVisible(String locator, String locValue) {
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeDownInAppUsingPointerInput();
		}
		return true;
	}

	public boolean swipeLeftInAppUsingPointerInputUntilElementIsVisible(String locator, String locValue) {
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeLeftInAppUsingPointerInput();
		}
		return true;
	}

	public boolean swipeRightinAppUsingPointerInputUntilElementIsVisible(String locator, String locValue) {
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeRightInAppUsingPointerInput();
		}
		return true;
	}

	public boolean clickInCoOrdinatesOfApp(int x, int y) {
		try {
			new TouchAction<>(driver).press(PointOption.point(x, y)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean pullFile(String phonePath, String destinationPath) {
		byte[] data = driver.pullFile(phonePath);
		Path path = Paths.get(destinationPath);
		try {
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void resetApp() {
		driver.resetApp();
	}

	public void closeApp() {
		if (driver != null) {
			try {
				driver.closeApp();
			} catch (Exception e) {
			}
		}
	}

	public boolean setPortraitOrientation() {
		driver.rotate(ScreenOrientation.PORTRAIT);
		return true;
	}

	public boolean setLanscapeOrientation() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
		return true;
	}

	public void hideKeyboard() {
		// ((IOSDriver) driver).hideKeyboard(HideKeyboardStrategy.PRESS_KEY,"Done");
		driver.hideKeyboard();

	}

	public String getOrientation() {
		return driver.getOrientation().toString();
	}

	public boolean enterValue(WebElement ele, String data) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.clear();
		ele.sendKeys(data);
		return true;
	}

	public boolean click(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getText(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		return ele.getText();
	}

	public boolean loadURL(String URL) {
		driver.get(URL);
		return true;
	}

	// Only for Web browser
	public boolean switchToLastWindow() {
		sleep(5000);
		Set<String> windowHandles = driver.getWindowHandles();
		for (String string : windowHandles) {
			driver.switchTo().window(string);
		}
		return true;
	}

	public void switchToAnotherApp(String bundleIdOrAppPackage) {
		driver.activateApp(bundleIdOrAppPackage);
	}

	public void stopRunningApp(String bundleIdOrAppPackage) {
		driver.terminateApp(bundleIdOrAppPackage);
	}
}
