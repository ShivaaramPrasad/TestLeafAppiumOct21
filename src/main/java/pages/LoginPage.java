package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en.Given;
//import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import wrappers.GenericWrappers;

public class LoginPage extends GenericWrappers {

	public LoginPage() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
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

	@Given("Enter the username as {string}")
	public LoginPage enterEmailAddress(String email) {
		enterValue(emailTxtBox, email);
		return this;
	}

	@Given("Enter the password as {string}")
	public LoginPage enterPassword(String pwd) {
		enterValue(pwdTxtBox, pwd);
		return this;
	}

	@Given("Click login button")
	public HomePage clickLogin() {
		click(loginBtn);
		return new HomePage();
	}
}