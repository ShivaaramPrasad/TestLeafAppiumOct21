package wrappers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.DataInputProvider;

public class ProjectSpecificWrappers extends GenericWrappers {

	public String dataSheetName;

	@BeforeSuite
	public void bs() {
		startReport();
	}

	@BeforeClass
	public void bc() {
		startTestModule(testCaseName, testDescription);
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
		startTestCase(testNodes);
		launchApp(platformName, deviceName, udid, appPackage, appActivity, automationName, chromeDriverPort, systemPort,
				xcodeOrgId, xcodeSigningId, bundleId, app, mjpegServerPort, wdaLocalPort);
	}

	@AfterMethod(alwaysRun = true)
	public void am() {
		closeApp();
	}

	@AfterSuite(alwaysRun = true)
	public void as() {
		endReport();
	}

	@DataProvider(name = "fetchData")
	public Object[][] getData() {
		return DataInputProvider.getSheet(dataSheetName);
	}

}