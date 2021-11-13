package runner;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import wrappers.GenericWrappers;

@CucumberOptions(features = "src/test/resources/features", glue = { "pages",
		"hooks" }, monochrome = true, publish = true,snippets = SnippetType.CAMELCASE)

public class TestRunner extends GenericWrappers {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@Parameters({ "platformName", "deviceName", "udid", "appPackage", "appActivity", "automationName",
			"chromeDriverPort", "systemPort", "xcodeOrgId", "xcodeSigningId", "bundleId", "app", "mjpegServerPort",
			"wdaLocalPort" })
	@BeforeMethod
	public void bm(String platformName, String deviceName, @Optional("") String udid, @Optional("") String appPackage,
			@Optional("") String appActivity, @Optional("") String automationName,
			@Optional("") String chromeDriverPort, @Optional("") String systemPort, @Optional("") String xcodeOrgId,
			@Optional("") String xcodeSigningId, @Optional("") String bundleId, @Optional("") String app,
			@Optional("") String mjpegServerPort, @Optional("") String wdaLocalPort) {
		launchApp(platformName, deviceName, udid, appPackage, appActivity, automationName, chromeDriverPort, systemPort,
				xcodeOrgId, xcodeSigningId, bundleId, app, mjpegServerPort, wdaLocalPort);
	}

	@AfterMethod(alwaysRun = true)
	public void am() {
		closeApp();
	}

}