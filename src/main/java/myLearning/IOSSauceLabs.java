package myLearning;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class IOSSauceLabs {

	@Test
	public void runCode() throws MalformedURLException {
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName", "iOS");
		caps.setCapability("appium:app", "storage:filename=UICatalog.app.zip");
		caps.setCapability("appium:deviceName", "iPhone 11 Pro Max Simulator");
		caps.setCapability("appium:platformVersion", "15.0");
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("appiumVersion", "1.22.0");
		caps.setCapability("sauce:options", sauceOptions);
		IOSDriver<WebElement> driver = new IOSDriver<WebElement>(new URL(
				"https://appiumtraining123:c452d277-9520-4090-8ee5-0397ba0818ed@ondemand.us-west-1.saucelabs.com:443/wd/hub"),
				caps);

		driver.findElementByAccessibilityId("AAPLActivityIndicatorViewController").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@name='UICatalog']").click();
		driver.findElementByAccessibilityId("AAPLButtonViewController").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@name='UICatalog']").click();
		driver.findElementByName("Alert Views").click();
		driver.findElementByAccessibilityId("Simple").click();
		driver.findElementByName("OK").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@name='UICatalog']").click();
		driver.quit();
	}

}
