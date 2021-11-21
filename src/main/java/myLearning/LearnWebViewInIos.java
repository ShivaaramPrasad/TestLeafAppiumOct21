package myLearning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnWebViewInIos extends GenericWrappers{
	
	@Test
	public void runCode() {
		launchIosApp("iPhone 8", "3360A391-E212-48F0-9722-488C749F1D4E", "", "com.example.apple-samplecode.UICatalog", "");
		swipeUpinAppUsingPointerInputUntilElementIsVisible(Locators.ACCESSIBILITY_ID.toString(), "Web View");
		click(getWebElement(Locators.ACCESSIBILITY_ID.toString(), "Web View"));
		sleep(8000);
		printContext();
		switchWebview();
		//switchContext("WEBVIEW_39045.1");
		click(getWebElement(Locators.LINK_TEXT.toString(), "Continue"));
		click(getWebElement(Locators.ID.toString(), "ac-gn-menuanchor-open"));
		sleep(2000);
		click(getWebElement(Locators.XPATH.toString(), "(//a[@data-analytics-title=\"ipad\"])[1]"));
		
	}

}
