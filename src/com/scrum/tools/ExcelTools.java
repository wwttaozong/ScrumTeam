package com.scrum.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scrum.main.Problem;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelTools {

	/**
	 * 从Excel文件中获取题目的数据
	 * 
	 * @param filePath
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static List<Problem> getDataFromExcel(String filePath)
			throws BiffException, IOException {
		List<Problem> problems = new ArrayList<Problem>();
		File targetFile = new File(filePath);
		if (targetFile.exists() && targetFile.isFile()) {
			Workbook document = Workbook.getWorkbook(targetFile);
			Sheet sheet = document.getSheet(0);
			int rows = sheet.getRows();
			// 遍历sheet中的所有行（Sheet中的单元格从下标0开始，行数跟Excel中看到的一样，注意这里获取单元格行列颠倒）
			for (int line = 1; line < rows; line++) {
				Problem problem = new Problem();
				problem.setName(sheet.getCell(0, line).getContents());
				problem.setDescription(sheet.getCell(2, line).getContents());
				problem.setInput(sheet.getCell(4, line).getContents());
				problem.setOutput(sheet.getCell(6, line).getContents());
				problems.add(problem);
			}
		}
		return problems;
	}

}
