package com.mk.pratice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BeforeMethodAcceptingDataProviderTrick {

	@BeforeMethod
	public void setup(Object[] data) {
		System.out.println("From Before Method " + Arrays.toString(data));
		System.out.println();

		Map<String, String> dataMap = convertToMap(data);
		System.out.println("Getting data from Map: " + dataMap.get("TestDataUser"));

	}

	@Test(dataProvider = "sampleData")
	public void testUsingSampleData(String testData, String testData1) {
		System.out.println("Inside the testMethod " + testData);
	}

	@DataProvider(name = "sampleData")
	public Object[][] getData() {
		return new Object[][] { { "TestDataUser", "TestDataPassword" } };
	}

	// Convert a 2d object array into map
	private Map<String, String> convertToMap(Object[] data) {
		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < data.length - 1; i++) {
			map.put(data[i].toString(), data[i + 1].toString());
		}

		return map;
	}
}
