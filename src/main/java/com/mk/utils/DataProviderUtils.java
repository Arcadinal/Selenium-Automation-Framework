package com.mk.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public final class DataProviderUtils {

	private DataProviderUtils() {
	}

	private static List<Map<String, String>> list = new ArrayList<>();

	@DataProvider(parallel = true)
	public static Object[] getData(Method m) {
		String testname = m.getName();
		List<Map<String, String>> result = new ArrayList<>();

		if (list.isEmpty()) {
			try {
				list = ExcelUtils.getDataAsList("testRunner");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (Map<String, String> map : list) {
			if (map.get("testName").equalsIgnoreCase(testname) && map.get("execute").equalsIgnoreCase("yes")) {
				// Setting the count
				int count = Integer.parseInt(map.get("count"));
				for (int i = 0; i < count; i++) {
					result.add(map);
				}

			}
		}

		return result.toArray();
	}
}
