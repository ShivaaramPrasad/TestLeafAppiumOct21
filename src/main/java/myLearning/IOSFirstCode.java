package myLearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class IOSFirstCode {

	@Test
	public void runCode() throws MalformedURLException {

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "iOS");
		//dc.setCapability("app", System.getProperty("user.dir")+"/apks/UICatalog.app.zip");
		dc.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
		dc.setCapability("deviceName", "iPhone 8");
		dc.setCapability("udid", "3360A391-E212-48F0-9722-488C749F1D4E");
		dc.setCapability("automationName", "XCUITest");
		dc.setCapability("platformVersion", "13.3");
		IOSDriver<WebElement> driver = new IOSDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
