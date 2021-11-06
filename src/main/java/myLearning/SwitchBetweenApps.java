package myLearning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class SwitchBetweenApps extends GenericWrappers {
	@Test
	public void runcode() throws InterruptedException {
		launchAndroidApp("Lokesh", "", "", "", System.getProperty("user.dir") + "\\APKs\\leaforg.apk");
		enterValue(getWebElement("xpath", "(//android.widget.EditText)[1]"), "lokesh@gmail.com");
		enterValue(getWebElement("xpath", "(//android.widget.EditText)[2]"), "password");
		click(getWebElement("xpath", "//android.widget.Button[@text='LOGIN']"));
		switchToAnotherApp("com.vodqareactnative");
		click(getWebElement("xpath", "//*[@text='LOG IN']"));
		switchToAnotherApp("com.testleaf.leaforg");
		click(getWebElement("xpath", "//*[@text='BACK']"));

	}
}
