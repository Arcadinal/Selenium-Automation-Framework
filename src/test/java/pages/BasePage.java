package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mk.driver.DriverManager;
import com.mk.enums.WaitStrategy;
import com.mk.factories.ExplicitiWaitFactory;

public class BasePage {
	//Wait is still under development
	
	protected void click(By by,WaitStrategy wait) {
			WebElement element = ExplicitiWaitFactory.performExplicitWait(wait, by);
			element.click();
	}
	protected void sendKeys(By by,String value) {
		DriverManager.getDriver().findElement(by).sendKeys(value);
	}

}
