package com.inetbanking.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 public class XLUtility
{
	 public static FileInputStream fi;
	 public static FileOutputStream fo;
	 public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static int getRowCount(String xFile, String xlSheet) throws IOException {
			fi=new FileInputStream(xFile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlSheet);
			int rowCount=ws.getLastRowNum();
			wb.close();
			fi.close();
			return rowCount;
			
		}
		
		public static int getCellCount(String xFile, String xlSheet, int rowNum) throws IOException {
			
			fi=new FileInputStream(xFile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlSheet);
			row=ws.getRow(rowNum);
			int cellCount=row.getLastCellNum();
			wb.close();
			fi.close();
			return cellCount;
				
		}
				
		public static String getCellData(String xFile,String xlSheet, int rowNum, int column) throws IOException
		{
			fi=new FileInputStream(xFile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlSheet);
			row=ws.getRow(rowNum);
			cell=row.getCell(column);
			String data;
			try {
				DataFormatter formatter=new DataFormatter();
				String CellData=formatter.formatCellValue(cell);
				return CellData;
			}
			catch(Exception e) {
				data="";
			}
			wb.close();
			fi.close();
			return data;
				
		}
		
		public static void setCellData(String xFile, String XlSheet,int rownNum, int column, String data) throws IOException {
			fi=new FileInputStream(xFile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(XlSheet);
			row=ws.getRow(rownNum);
			cell=row.createCell(column);
			cell.setCellValue(data);
			fo=new FileOutputStream(xFile);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
			
		}

}
