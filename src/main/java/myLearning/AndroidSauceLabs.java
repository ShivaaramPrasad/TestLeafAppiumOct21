package myLearning;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AndroidSauceLabs {

	@Test
	public void runCode() throws MalformedURLException {
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:app", "storage:filename=leaforg.apk");
		caps.setCapability("appium:deviceName", "Google Pixel 3 GoogleAPI Emulator");
		caps.setCapability("appium:platformVersion", "11.0");
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("appiumVersion", "1.20.2");
		caps.setCapability("sauce:options", sauceOptions);
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("https://appiumtraining:c33d948a-054d-4dc0-bfe4-e9d16c7c8b0e@ondemand.us-west-1.saucelabs.com:443/wd/hub"), caps);
		driver.findElementByXPath("(//android.widget.EditText)[1]").sendKeys("lokesh@gmail.com");
		driver.findElementByXPath("(//android.widget.EditText)[2]").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		driver.quit();
	}

}
