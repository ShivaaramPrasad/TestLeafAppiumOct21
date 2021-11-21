package myLearning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnPickerViewerInIOS extends GenericWrappers {

	@Test
	public void runCode() {
		launchIosApp("iPhone 8", "3360A391-E212-48F0-9722-488C749F1D4E", "", "com.example.apple-samplecode.UICatalog",
				"");
		click(getWebElement(Locators.ACCESSIBILITY_ID.toString(), "Picker View"));
		enterValue(getWebElement(Locators.ACCESSIBILITY_ID.toString(), "Red color component value"), "50");
		sleep(2000);
		for (int i = 0; i < 5; i++) {
			chooseNextOptionInPickerWheel(
					getWebElement(Locators.ACCESSIBILITY_ID.toString(), "Red color component value"));
		}

	}

}
