package testing_journey.appium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Ecommerce_Fill_Form_Add_To_Cart extends BaseTest {

	Ecommerce_Fill_Form_Add_To_Cart() {
		super(2);
	}

	//Test to fill forms and adding items to cart of an e-commerce app and validating if the correct item is displayed in cart
	@Test
	public void fillFormAndAddToCart() throws InterruptedException  {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Elena");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Female\"]")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Germany\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Germany\"]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//String toastMsg = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		//Assert.assertEquals(toastMsg, "Please enter your name");
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"));"));
		
		int cartListSize = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).size();
		
		
		//Take the bounds of the text of the required product so that we only click add to cart for the button near it
		String productBounds[] = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Jordan Lift Off\"]")).getAttribute("bounds").split("]");
		String productBoundsSplit[] = productBounds[1].split(",");
		String productYaxis = productBoundsSplit[1];
		int shoeYaxis = Integer.parseInt(productYaxis);
		
		for(int i=0; i<cartListSize; i++) {
			// Take the bounds of the button add to cart and compare to see if its nearby to our required product name
			String btnBounds[] = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).getAttribute("bounds").split("]");
			String btnBoundsSplit[] = btnBounds[1].split(",");
			String btnYaxis = btnBoundsSplit[1];
			int btnShoeYaxis = Integer.parseInt(btnYaxis);
			
			if(btnShoeYaxis > shoeYaxis && btnShoeYaxis <= shoeYaxis+400) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();		
			}	
			
		}
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		//Wait until new screen is loaded
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.xpath("//android.widget.TextView[@text=\"Cart\"]")), "text", "Cart"));
		
		String correctItem = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(correctItem, "Jordan Lift Off");
		
	}
	

}
