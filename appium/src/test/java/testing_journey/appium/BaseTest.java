package testing_journey.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

//A base test class for configurations so other classes can just extend it and implement test cases.
public class BaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public int appSelection = 0;
	
	BaseTest(int appSelection){
		this.appSelection = appSelection;
	}

	@BeforeClass
	public void setupAppium() throws MalformedURLException {

		service = new AppiumServiceBuilder().withAppiumJS(new File(
				"C:\\\\Users\\\\Muhammad Ali\\\\AppData\\\\Roaming\\\\npm\\\\node_modules\\\\appium\\\\build\\\\lib\\\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 2 API 28");

		if (appSelection == 1) {
			options.setApp(
					"C:\\Users\\Muhammad Ali\\git\\appium\\appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		} else {

			options.setApp(
					"C:\\Users\\Muhammad Ali\\git\\appium\\appium\\src\\test\\java\\resources\\General-Store.apk");
		}

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public void longPressAction(WebElement element) {

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));

	}

	public void scrollTillEnd() {
		boolean canScrollMore;

		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "height", 200, "width", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	public void scrollTillElementName(String elementName) {

		driver.findElement(AppiumBy.androidUIAutomator(
				String.format("new UiScrollable(new UiSelector()).scrollIntoView(text(%s));", elementName)));
	}

	public void swipe(WebElement swipingElement, String direction) {

		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) swipingElement).getId(), "direction", direction, "percent", 0.75

		));
	}

	@AfterClass
	public void clearAppiumProcess() {

		driver.quit();
		service.stop();

	}

}
