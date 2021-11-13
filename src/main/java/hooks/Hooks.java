package hooks;

import org.openqa.selenium.OutputType;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import wrappers.GenericWrappers;

public class Hooks extends GenericWrappers {
	@AfterStep
	public void afterEachStep(Scenario scenario) {
		byte[] screenshot = getDriver().getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getId());
	}
}