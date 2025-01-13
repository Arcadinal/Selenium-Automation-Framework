package com.mk.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.mk.constants.FrameworkConstants;

public class ExcelUtils {

	public static Object[] getData(String filePath, String sheetName) {
		try {
			FileInputStream file = new FileInputStream(filePath);

			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sh = wb.getSheet(sheetName);

			int row = sh.getLastRowNum();
			int col = sh.getRow(0).getLastCellNum();

			Map<Object, Object> dataMap;
			Object[] data = new Object[row];

			Row headerRow = sh.getRow(0);
			String headers[] = new String[col];

			for (int i = 0; i < col; i++) {
				headers[i] = headerRow.getCell(i).getStringCellValue();
			}
			for (int i = 1; i <= row; i++) {

				Row rows = sh.getRow(i);

				dataMap = new HashMap<Object, Object>();
				for (int j = 0; j < col; j++) {
					Cell cell = rows.getCell(j);
					if (Objects.isNull(cell)) {
						dataMap.put("", "");
					} else {
						switch (cell.getCellType()) {
						case STRING:
							dataMap.put(headers[j], cell.getStringCellValue());
							break;
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								SimpleDateFormat dft = new SimpleDateFormat("dd-MM-YYYY");
								dataMap.put(headers[j], dft.format(cell.getDateCellValue()));
							} else {
								dataMap.put(headers[j], String.valueOf(cell.getNumericCellValue()));
							}
							break;
						default:
							dataMap.put(headers[j], "");
							break;
						}
					}

					data[i - 1] = dataMap;
				}
			}

			return data;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@DataProvider(name = "getData", parallel = true)
	public Object[] data() {
		return getData(
				"D:\\SeleniumAutomationFramework\\SeleniumAutomation\\src\\test\\resources\\excel\\SauceLabsCredentials.xlsx",
				"Sheet1");
	}

	public static List<Map<String, String>> getDataAsList(String sheetName) {
		List<Map<String, String>> dataList = new ArrayList<>();
		
		try (FileInputStream file = new FileInputStream(FrameworkConstants.getTestrunner()); 
				XSSFWorkbook wb = new XSSFWorkbook(file)) {
			XSSFSheet sh = wb.getSheet(sheetName);
			int row = sh.getLastRowNum();
			int col = sh.getRow(0).getLastCellNum();

			Row headerRow = sh.getRow(0);
			String[] headers = new String[col];
			for (int i = 0; i < col; i++) {
				headers[i] = headerRow.getCell(i).getStringCellValue();
			}

			for (int i = 1; i <= row; i++) {
				Row rows = sh.getRow(i);
				Map<String, String> dataMap = new HashMap<>();
				for (int j = 0; j < col; j++) {
					Cell cell = rows.getCell(j);
					if (Objects.isNull(cell)) {
						dataMap.put(headers[j], "");
					} else {
						switch (cell.getCellType()) {
						case STRING:
							dataMap.put(headers[j], cell.getStringCellValue());
							break;
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								SimpleDateFormat dft = new SimpleDateFormat("dd-MM-yyyy");
								dataMap.put(headers[j], dft.format(cell.getDateCellValue()));
							} else {
								dataMap.put(headers[j], String.valueOf(cell.getNumericCellValue()));
							}
							break;
						default:
							dataMap.put(headers[j], "");
							break;
						}
					}
				}
				dataList.add(dataMap);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
	


}
