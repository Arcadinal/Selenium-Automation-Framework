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

import com.mk.constants.FrameworkConstants;

public final class ExcelUtils {
	
	//Private Constructor
	private ExcelUtils() {}

	public static List<Map<String, String>> getDataAsList(String sheetName) {
		List<Map<String, String>> dataList = new ArrayList<>();

		try (FileInputStream file = new FileInputStream(FrameworkConstants.getExcelfilepath());
				XSSFWorkbook wb = new XSSFWorkbook(file)) {
			XSSFSheet sh = wb.getSheet(sheetName);
			if (sh == null) {
                System.out.println("Sheet not found: " + sheetName);
                return dataList;
            }
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
