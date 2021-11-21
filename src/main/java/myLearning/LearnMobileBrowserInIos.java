package myLearning;

import org.openqa.selenium.Point;
import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnMobileBrowserInIos extends GenericWrappers{
	
	@Test
	public void runCode() {
		launchSafariBrowser("iPhone 8", "https://www.google.com", "3360A391-E212-48F0-9722-488C749F1D4E");
		enterValue(getWebElement(Locators.NAME.toString(), "q"), "Lokesh");
		click(getWebElement(Locators.NAME.toString(), "q"));
		printContext();
		switchNativeview();
		//click(getWebElement(Locators.ACCESSIBILITY_ID.toString(), "search"));
		Point location = getWebElement(Locators.ACCESSIBILITY_ID.toString(), "search").getLocation();
		int x = location.getX();
		int y = location.getY();
		clickInCoOrdinatesOfApp(x+20, y+20);
		switchWebview();
		
		
	}

}
