package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import wrappers.GenericWrappers;

public class LoginPage extends GenericWrappers {

	public LoginPage(AppiumDriver<WebElement> driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		eleIsDisplayed(emailTxtBox);
	}

	@AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
	// @iOSXCUITFindBy(id = "")
	private WebElement emailTxtBox;

	@AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
	// @iOSXCUITFindBy(id = "")
	private WebElement pwdTxtBox;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='LOGIN']")
	// @iOSXCUITFindBy(id = "")
	private WebElement loginBtn;

	public LoginPage enterEmailAddress(String email) {
//		String platform = driver.getCapabilities().getCapability("platformName").toString();
//		System.out.println(platform);
		if (enterValue(emailTxtBox, email)) {
			reportStep("Username entered successfully", "pass");
		} else {
			reportStep("Username entry failed", "fail");
		}
		return this;
	}

	public LoginPage enterPassword(String pwd) {
		if (enterValue(pwdTxtBox, pwd)) {
			reportStep("Password entered successfully", "pass");
		} else {
			reportStep("Password entry failed", "fail");
		}
		return this;
	}

	public HomePage clickLogin() {
		if (click(loginBtn)) {
			reportStep("Login button clicked successfully", "pass");
		} else {
			reportStep("Login button click failed", "fail");
		}
		return new HomePage(driver, test);
	}
}