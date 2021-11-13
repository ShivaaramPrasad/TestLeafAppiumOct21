package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import wrappers.GenericWrappers;

public class HomePage extends GenericWrappers {

	public HomePage() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
		eleIsDisplayed(userNameTxt);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text='Rajkumar Ganesan']")
	private WebElement userNameTxt;

}