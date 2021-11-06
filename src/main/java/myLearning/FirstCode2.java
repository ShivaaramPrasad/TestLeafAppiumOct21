package myLearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class FirstCode2 {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		//dc.setCapability("platformName", "Android");
		//dc.setCapability("app", System.getProperty("user.dir") + "\\APKs\\leaforg.apk");
		dc.setCapability("appPackage", "com.testleaf.leaforg");
		dc.setCapability("appActivity", "com.testleaf.leaforg.MainActivity");
		dc.setCapability("deviceName", "Lokesh");
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElementByXPath("(//android.widget.EditText)[1]").sendKeys("lokesh@gmail.com");
		driver.findElementByXPath("(//android.widget.EditText)[2]").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		//driver.close();
		//driver.hideKeyboard();
	}

}
