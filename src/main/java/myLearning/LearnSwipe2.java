package myLearning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnSwipe2 extends GenericWrappers {

	@Test
	public void runcode() throws InterruptedException {
		launchAndroidApp("Lokesh", "", "", "", System.getProperty("user.dir") + "\\APKs\\MultiTouch Tester_v1.2.apk");
		Thread.sleep(3000);
		swipeUpinAppUsingPointerInput();
		swipeUpInAppUsingTouchActions();
		swipeDownInAppUsingPointerInput();
		swipeDownInAppUsingTouchActions();
		swipeLeftInAppUsingPointerInput();
		swipeLeftInAppUsingTouchActions();
		swipeRightInAppUsingPointerInput();
		swipeRightInAppUsingTouchActions();
		pinchUsingPointerInput();
		pinchUsingTouchActions();
		zoomUsingPointerInput();
		zoomUsingTouchActions();
	}

}
