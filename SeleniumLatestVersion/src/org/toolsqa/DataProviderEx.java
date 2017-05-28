package org.toolsqa;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class DataProviderEx {
	@Test(dataProvider="DP")
	public void verifySI(String principal,String tenure,String roi,String expcted)
	{
		int P = Integer.parseInt(principal);
		int N = Integer.parseInt(tenure);
		int R = Integer.parseInt(roi);
		int E = Integer.parseInt(expcted);
		int A = (P * N * R)/100;
		Assert.assertEquals(A,E);
	}
	@DataProvider(name="DP")
	public static  String[][]  readXLS() throws BiffException, IOException
	{
//		XLS->>>Workbook ->>>3 Sheet
		File f = new File("inputData.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet sh =wb.getSheet("Sanity");
//		Sheet1 -> fetch the rows & columns
		int rows = sh.getRows();
		int cols = sh.getColumns();
		
//		Declare an array with the size of rows & Columns
		String[][] input=new String[rows][cols];
		
//		fetch the Cell using row index and column index
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
					Cell cell=	sh.getCell(j, i);
					input[i][j]=cell.getContents();
			}
		}
		return input;	}}
