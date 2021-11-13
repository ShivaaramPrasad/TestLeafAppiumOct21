package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;
import wrappers.ProjectSpecificWrappers;

public class LoginTC extends ProjectSpecificWrappers {

	@BeforeTest
	public void bt() {
		testCaseName = "Login TC";
		testNodes = "TC001";
		testDescription="Login funtionality";
		category = "Smoke";
		authors = "Lokesh Kumar";
	}
	
	@Test
	public void runMyTC() {
		new LoginPage(driver,test)
		.enterEmailAddress("rajkumar@testleaf.com")
		.enterPassword("Leaf@123")
		.clickLogin();
	}
}