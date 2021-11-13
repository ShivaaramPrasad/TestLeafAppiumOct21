package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import wrappers.GenericWrappers;

public class HomePage extends GenericWrappers {

	public HomePage(AppiumDriver<WebElement> driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		eleIsDisplayed(userNameTxt);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Rajkumar Ganesan']")
	private WebElement userNameTxt;

}