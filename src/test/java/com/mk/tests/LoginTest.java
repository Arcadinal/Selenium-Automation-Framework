package com.mk.tests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.mk.utils.DataProviderUtils;

import pages.SauceLabsLoginPage;

public class LoginTest extends BaseTest {

	private LoginTest() {
	}

	@Test(dataProvider = "getData", dataProviderClass = DataProviderUtils.class)
	public void loginTest(Map<String, String> data) {
		String title = new SauceLabsLoginPage().enterUsername(data.get("username")).enterPassword(data.get("password"))
				.clickLogin().getCurrentURL();
		Assertions.assertThat(title).contains("inventory");

	}

}
