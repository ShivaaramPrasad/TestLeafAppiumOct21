package myLearning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnWebApp extends GenericWrappers{
	
	@Test
	public void runcode() {
		launchChromeBrowser("lokesh","http://www.google.com");
		System.out.println(driver.getPageSource());
		enterValue(getWebElement(Locators.NAME.toString(), "q"), "Lokesh");
		pressEnter();
	}

}
