package testing_journey.appium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Ecommerce_Cart_Total_Validation extends BaseTest {
	
	Ecommerce_Cart_Total_Validation() {
		super(2);
	}

	// Test to add first two items into cart then go to cart and validate whether or not the price total is correct.
	@Test
	public void cartTotalValidation() throws InterruptedException  {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Elena");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Female\"]")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Argentina\"]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		
		driver.findElements(By.xpath("//android.widget.TextView[@text=\"ADD TO CART\"]")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text=\"ADD TO CART\"]")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.xpath("//android.widget.TextView[@text=\"Cart\"]")), "text", "Cart"));
		
		String firstProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getAttribute("text").substring(1); 
		Double firstProductPrice = Double.parseDouble(firstProduct);
		String secondProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getAttribute("text").substring(1);
		Double secondProductPrice = Double.parseDouble(secondProduct);
		
		String finalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getAttribute("text").substring(1);
		Double totalPrice = Double.parseDouble(finalPrice);
		
		Assert.assertEquals(totalPrice, firstProductPrice+secondProductPrice);
		
		
	}
	

}
