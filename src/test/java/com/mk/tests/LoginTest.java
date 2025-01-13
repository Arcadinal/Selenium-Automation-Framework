package com.mk.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.mk.utils.ExcelUtils;

import pages.SauceLabsLoginPage;

public class LoginTest extends BaseTest {

	private LoginTest() {
	}

	@Test(dataProvider = "getData", dataProviderClass = ExcelUtils.class)
	public void loginTest(Map<String, String> data) {
		System.out.println("Testing 123..");
		String title = new SauceLabsLoginPage()

				.enterUsername(data.get("username")).enterPassword(data.get("password"))

				.clickLogin().getTitle();

	}

	@Test
	public void alphaTest() {
		System.out.println("Apple");
	}

}
