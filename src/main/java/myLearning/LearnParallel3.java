package myLearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class LearnParallel3 extends GenericWrappers {
	@Parameters({ "systemPort", "udid" })
	@Test
	public void runScript(String systemPort, String udid) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("appPackage", "com.testleaf.leaforg");
		dc.setCapability("appActivity", "com.testleaf.leaforg.MainActivity");
		dc.setCapability("deviceName", "Lokesh");
		dc.setCapability("systemPort", systemPort);
		dc.setCapability("udid", udid);
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElementByXPath("(//android.widget.EditText)[1]").sendKeys("lokesh@gmail.com");
		driver.findElementByXPath("(//android.widget.EditText)[2]").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		driver.closeApp();
	}
}
