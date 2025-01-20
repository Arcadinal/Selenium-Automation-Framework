package pages;

import org.openqa.selenium.By;

import com.mk.driver.DriverManager;
import com.mk.enums.WaitStrategy;

public class SauceLabsLoginPage extends BasePage{
	
	private final By textbox_username = By.id("user-name");
	private final By textbox_password = By.id("password");
	private final By btnLogin = By.id("login-button");
	
	public SauceLabsLoginPage enterUsername(String username) {
		sendKeys(textbox_username,username);
		return this;
	}
	
	public SauceLabsLoginPage enterPassword(String password) {
		sendKeys(textbox_password,password);
		return this;
	}
	
	public SauceLabsLoginPage clickLogin() {
		click(btnLogin,WaitStrategy.NONE);
		return this;
	}
	
	public String getCurrentURL() {
		return DriverManager.getDriver().getCurrentUrl();
	}
	
	

}
