package wrappers;

import java.util.HashMap;

import org.openqa.selenium.WebElement;

public class IosWrappers extends AndroidWrappers {

	public boolean launchIosApp(String deviceName, String udid, String xcodeOrgId, String bundleId, String app) {
		return launchApp("iOS", deviceName, udid, "", "", "XCUITest", "", "", xcodeOrgId, "iPhone Developer", bundleId,
				app, "", "");
	}

	public boolean launchIosAppInParallel(String deviceName, String udid, String xcodeOrgId, String bundleId,
			String app, String wdaLocalPort) {
		return launchApp("iOS", deviceName, udid, "", "", "XCUITest", "", "", xcodeOrgId, "iPhone Developer", bundleId,
				app, "", wdaLocalPort);
	}

	public boolean launchSafariBrowser(String deviceName, String URL, String udid) {
		return launchBrowser("iOS", "Safari", deviceName, URL, udid, "", "", "", "");
	}

	public boolean launchSafariBrowserInParallel(String deviceName, String URL, String udid, String wdaLocalPort,
			String webkitDebugProxyPort) {
		return launchBrowser("iOS", "Safari", deviceName, URL, udid, "", wdaLocalPort, "", webkitDebugProxyPort);
	}

	public boolean deleteSafariCookies() {
		switchToAnotherApp("com.apple.Preferences");
		stopRunningApp("com.apple.Preferences");
		switchToAnotherApp("com.apple.Preferences");
		switchNativeview();
		swipeDownInAppUsingPointerInput();
		enterValue(getWebElement(Locators.XPATH.toString(), "//*[@label='Search']"), "Safari");
		try {
			click(getWebElement(Locators.XPATH.toString(), "//XCUIElementTypeCell[2]//*[@name='Safari']"));
		} catch (Exception e) {
			click(getWebElement(Locators.XPATH.toString(), "//*[@label='Safari']"));
		}
		sleep(1000);
		swipeUpinAppUsingPointerInputUntilElementIsVisible(Locators.XPATH.toString(),
				"//*[@value='Clear History and Website Data' and @visible='true']");
		click(getWebElement(Locators.XPATH.toString(),
				"//*[@value='Clear History and Website Data' and @visible='true']"));
		click(getWebElement(Locators.XPATH.toString(), "//*[@label='Clear' or @label='Clear History and Data']"));
		stopRunningApp("com.apple.Preferences");
		switchToAnotherApp("com.apple.mobilesafari");
		switchWebview();
		return true;
	}

	public boolean chooseNextOptionInPickerWheel(String locator, String locatorValue) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("order", "next");
		params.put("offset", 0.15);
		params.put("element", getWebElement(locator, locatorValue));
		driver.executeScript("mobile: selectPickerWheelValue", params);
		return true;
	}

	public boolean chooseNextOptionInPickerWheel(WebElement ele) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("order", "next");
		params.put("offset", 0.18);
		params.put("element", ele);
		driver.executeScript("mobile: selectPickerWheelValue", params);
		return true;
	}

	public boolean choosePreviousOptionInPickerWheel(String locator, String locatorValue) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("order", "previous");
		params.put("offset", 0.15);
		params.put("element", getWebElement(locator, locatorValue));
		driver.executeScript("mobile: selectPickerWheelValue", params);
		return true;
	}

	public boolean choosePreviousOptionInPickerWheel(WebElement ele) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("order", "previous");
		params.put("offset", 0.15);
		params.put("element", ele);
		driver.executeScript("mobile: selectPickerWheelValue", params);
		return true;
	}
}
