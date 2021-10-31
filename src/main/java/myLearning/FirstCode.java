package myLearning;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirstCode {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		//dc.setCapability("app", System.getProperty("user.dir") + "\\APKs\\leaforg.apk");
		dc.setCapability("appPackage", "com.testleaf.leaforg");
		dc.setCapability("appActivity", "com.testleaf.leaforg.MainActivity");
		dc.setCapability("deviceName", "Lokesh");
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElementByXPath("(//android.widget.EditText)[1]").sendKeys("lokesh@gmail.com");
		driver.findElementByXPath("(//android.widget.EditText)[2]").sendKeys("password");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		//driver.close();
	}

}
