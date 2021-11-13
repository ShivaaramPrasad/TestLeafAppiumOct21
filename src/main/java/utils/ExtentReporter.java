package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
//import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public abstract class ExtentReporter {

	// public ExtentHtmlReporter report;
	public static ExtentSparkReporter report;
	public static ExtentReports extent;
	public ExtentTest test, suiteTest;
	public String testCaseName, testNodes, testDescription, category, authors;

	public void startReport() {
		// report = new ExtentHtmlReporter("./reports/result.html");
		report = new ExtentSparkReporter("./reports/result.html");
		report.config().setDocumentTitle("Mobile Automation");
		// report.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(report);
	}

	public ExtentTest startTestModule(String testCaseName, String testDescription) {
		suiteTest = extent.createTest(testCaseName, testDescription);
		suiteTest.assignAuthor(authors);
		suiteTest.assignCategory(category);
		return suiteTest;
	}

	public ExtentTest startTestCase(String testNodes) {
		test = suiteTest.createNode(testNodes);
		return test;
	}

	public abstract long takeScreenShot();

	public void reportStep(String desc, String status, boolean bSnap) {

		// MediaEntityModelProvider img = null;
		Media img = null;
		if (bSnap && !status.equalsIgnoreCase("INFO")) {

			long snapNumber = 100000L;
			snapNumber = takeScreenShot();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath("./../reports/images/" + snapNumber + ".png")
						.build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (status.equalsIgnoreCase("PASS")) {
			test.pass(desc, img);
		} else if (status.equalsIgnoreCase("FAIL")) {
			test.fail(desc, img);
			throw new RuntimeException();
		} else if (status.equalsIgnoreCase("WARNING")) {
			test.warning(desc, img);
		} else if (status.equalsIgnoreCase("INFO")) {
			test.info(desc);
		}
	}

	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}

	public void endReport() {
		extent.flush();
	}

}