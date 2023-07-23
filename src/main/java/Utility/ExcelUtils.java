package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	public static File file;
	public static FileInputStream fis;
	public static FileOutputStream fout;
	
	public static XSSFWorkbook wb; 	
	public static XSSFSheet sheet1;

	public static HashMap<String, HashMap<String, String>> getUserData(String execelPath, String sheetName,
			String userType) {
		String sheetUserType, sheetColumnName, sheetColumnValue = null;
		file = new File(execelPath);
		try {
			fis = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		try {

			wb = new XSSFWorkbook(fis);

		} catch (IOException e) {
			e.getMessage();
		}

		sheet1 = wb.getSheet(sheetName);

		HashMap<String, HashMap<String, String>> hm = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> hc = new HashMap<String, String>();

		int rowCount = sheet1.getLastRowNum() + 1;

		int colCount = sheet1.getRow(0).getLastCellNum();

		for (int i = 1; i < rowCount; i++) {

			sheetUserType = sheet1.getRow(i).getCell(0).getStringCellValue();

			if (sheetUserType.equalsIgnoreCase(userType)) {

				for (int j = 1; j < colCount; j++) {
					sheetColumnName = sheet1.getRow(0).getCell(j).getStringCellValue();
					sheetColumnValue = sheet1.getRow(i).getCell(j).getStringCellValue();

					hc.put(sheetColumnName, sheetColumnValue);

				}
				break;
			}
		}
		hm.put(userType, hc);
		return hm;
	}

	public static String getExcelValue(String excelPath, String sheetName, String userType, String columnName) {

		HashMap<String, HashMap<String, String>> hm = getUserData(excelPath, sheetName, userType);
		String mapValue = hm.get(userType).get(columnName).trim();
		return mapValue;
	}
	
	public static void writeToExcel(String excelPath, String sheetName, String userType, String columnName, String Data) {
		String sheetUserType, sheetColumnName, sheetColumnValue = null;
		file = new File(excelPath);
		
		try {
			fis = new FileInputStream(file);
			fout = new FileOutputStream(file);

		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		sheet1 = wb.getSheet(sheetName);
		
		
		int rowCount = sheet1.getLastRowNum() + 1;

		int colCount = sheet1.getRow(0).getLastCellNum();
		
		for (int i = 1; i < rowCount; i++) {

			sheetUserType = sheet1.getRow(i).getCell(0).getStringCellValue();

			if (sheetUserType.equalsIgnoreCase(userType)) {

				for (int j = 1; j < colCount; j++) {
					sheetColumnName = sheet1.getRow(0).getCell(j).getStringCellValue();
				//	sheetColumnValue = sheet1.getRow(i).getCell(j).getStringCellValue();
					if(sheet1.getRow(i).getCell(j)==null) {
						sheet1.getRow(i).createCell(j);
					}
					sheet1.getRow(i).getCell(j).setCellValue(Data);

				}
				break;
			}
		}
		try {
			wb.write(fout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}