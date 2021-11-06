package myLearning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class HybridAppPractice extends GenericWrappers {
	@Test
	public void runcode() throws InterruptedException {
		launchAndroidApp("Lokesh", "", "", "", System.getProperty("user.dir") + "\\APKs\\leaforg.apk");
		printContext();
		Thread.sleep(5000);
		switchContext("WEBVIEW_com.testleaf.leaforg");
		enterValue(getWebElement("xpath", "//input[@placeholder=\"Email\"]"), "lokesh@gmail.com");
		enterValue(getWebElement("xpath", "//input[@placeholder=\"Password\"]"), "lokesh@gmail.com");
		click(getWebElement("xpath", "//span[text()='Login']"));
	}
}
