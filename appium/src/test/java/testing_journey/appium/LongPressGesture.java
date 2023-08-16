package testing_journey.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class LongPressGesture extends BaseTest {

	LongPressGesture() {
		super(1);
	}

	@Test
	public void longPressPeopleItem() throws MalformedURLException {
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Custom Adapter\"]")).click();
		
		WebElement peopleNamesElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
		longPressAction(peopleNamesElement);
		
		String menuName = driver.findElement(By.id("android:id/title")).getText();
		Assert.assertEquals(menuName, "Sample menu");
		
		Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
		
	
		
	}
	
	

}
