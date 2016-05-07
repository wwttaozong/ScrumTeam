package com.scrum.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.scrum.main.Problem;
import com.scrum.tools.ExcelTools;

public class ExcelToolsTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 测试从Excel获取数据的功能
	 */
	@Test
	public void testGetDataFromExcel() {
		List<Problem> problems = new ArrayList<Problem>();
		try {
			problems = ExcelTools.getDataFromExcel("D:/Problems.xls");

			// 获取数据不为空
			Assert.assertNotNull(problems);

			// 题目名称的校验
			Assert.assertEquals("题目一", problems.get(0).getName());
			Assert.assertEquals("题目二", problems.get(1).getName());
			Assert.assertEquals("题目三", problems.get(2).getName());
			Assert.assertEquals("题目四", problems.get(3).getName());
			Assert.assertEquals("题目五", problems.get(4).getName());
			Assert.assertEquals("题目六", problems.get(5).getName());
			Assert.assertEquals("题目七", problems.get(6).getName());
			Assert.assertEquals("题目八", problems.get(7).getName());
			Assert.assertEquals("题目九", problems.get(8).getName());
			Assert.assertEquals("题目十", problems.get(9).getName());

			// 题目描述
			Assert.assertEquals("a+b?", problems.get(0).getDescription());
			Assert.assertEquals("a-b?", problems.get(1).getDescription());
			Assert.assertEquals("a*b?", problems.get(2).getDescription());
			Assert.assertEquals("a/b?", problems.get(3).getDescription());
			Assert.assertEquals("a%b?", problems.get(4).getDescription());
			Assert.assertEquals("a的平方?", problems.get(5).getDescription());
			Assert.assertEquals("a的立方?", problems.get(6).getDescription());
			Assert.assertEquals("a的阶乘?", problems.get(7).getDescription());
			Assert.assertEquals("a的累加?", problems.get(8).getDescription());
			Assert.assertEquals("a的b次方?", problems.get(9).getDescription());

			// 题目输入
			Assert.assertEquals("1 2", problems.get(0).getInput());
			Assert.assertEquals("2 1", problems.get(1).getInput());
			Assert.assertEquals("1 2", problems.get(2).getInput());
			Assert.assertEquals("3 1", problems.get(3).getInput());
			Assert.assertEquals("4 3", problems.get(4).getInput());
			Assert.assertEquals("2", problems.get(5).getInput());
			Assert.assertEquals("2", problems.get(6).getInput());
			Assert.assertEquals("3", problems.get(7).getInput());
			Assert.assertEquals("4", problems.get(8).getInput());
			Assert.assertEquals("2 3", problems.get(9).getInput());

			// 题目输出
			Assert.assertEquals("3", problems.get(0).getOutput());
			Assert.assertEquals("1", problems.get(1).getOutput());
			Assert.assertEquals("2", problems.get(2).getOutput());
			Assert.assertEquals("3", problems.get(3).getOutput());
			Assert.assertEquals("1", problems.get(4).getOutput());
			Assert.assertEquals("4", problems.get(5).getOutput());
			Assert.assertEquals("8", problems.get(6).getOutput());
			Assert.assertEquals("6", problems.get(7).getOutput());
			Assert.assertEquals("10", problems.get(8).getOutput());
			Assert.assertEquals("8", problems.get(9).getOutput());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
