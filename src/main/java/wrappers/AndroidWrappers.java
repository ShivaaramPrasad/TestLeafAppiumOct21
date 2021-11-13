package wrappers;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

public class AndroidWrappers extends CommonWrappers {

	public boolean launchAndroidApp(String deviceName, String appPackage, String appActivity, String automationName,
			String app) {
		return launchApp("Android", deviceName, "", appPackage, appActivity, automationName, "", "", "", "", "", app,
				"", "");
	}

	public boolean launchAndroidAppInParallel(String deviceName, String udid, String appPackage, String appActivity,
			String automationName, String chromeDriverPort, String mjpegServerPort, String systemPort, String app) {
		return launchApp("Android", deviceName, udid, appPackage, appActivity, automationName, chromeDriverPort,
				systemPort, "", "", "", app, mjpegServerPort, "");
	}

	public boolean launchChromeBrowser(String deviceName, String URL) {
		return launchBrowser("Android", "Chrome", deviceName, URL, "", "", "", "");
	}

	public boolean launchChromeBrowserInParallel(String deviceName, String URL, String udid, String chromeDriverPort,
			String mjpegServerPort) {
		return launchBrowser("Android", "Chrome", deviceName, URL, udid, chromeDriverPort, "", mjpegServerPort);
	}

	public boolean startAnAppUsingActivity(String appPackage, String appActivity) {
		try {
			((StartsActivity) getDriver()).startActivity(new Activity(appPackage, appActivity));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void showNotificationMenu() {
		((AndroidDriver<WebElement>) getDriver()).openNotifications();
	}

	public boolean pressEnter() {
		((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
		return true;
	}

	public boolean pressBack() {
		((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
		return true;
	}

	public void hideNotificationMenu() {
		pressBack();
	}

	public void toggleLocationServices() {
		((AndroidDriver<WebElement>) getDriver()).toggleLocationServices();
	}

	public boolean dataOffInAndroid() {
		((HasNetworkConnection) getDriver())
				.setConnection(new ConnectionStateBuilder().withAirplaneModeEnabled().build());
		return true;
	}

	public boolean dataOnInAndroid() {
		((HasNetworkConnection) getDriver()).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
		((HasNetworkConnection) getDriver()).setConnection(new ConnectionStateBuilder().withDataEnabled().build());
		return true;
	}

	public String getCurrentActivity() {
		return ((StartsActivity) getDriver()).currentActivity();
	}

	public boolean deleteChromeCookies() {
		getDriver().manage().deleteAllCookies();
		return true;
	}

}
