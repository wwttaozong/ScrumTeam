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
	 * ���Դ�Excel��ȡ���ݵĹ���
	 */
	@Test
	public void testGetDataFromExcel() {
		List<Problem> problems = new ArrayList<Problem>();
		try {
			problems = ExcelTools.getDataFromExcel("D:/Problems.xls");

			// ��ȡ���ݲ�Ϊ��
			Assert.assertNotNull(problems);

			// ��Ŀ���Ƶ�У��
			Assert.assertEquals("��Ŀһ", problems.get(0).getName());
			Assert.assertEquals("��Ŀ��", problems.get(1).getName());
			Assert.assertEquals("��Ŀ��", problems.get(2).getName());
			Assert.assertEquals("��Ŀ��", problems.get(3).getName());
			Assert.assertEquals("��Ŀ��", problems.get(4).getName());
			Assert.assertEquals("��Ŀ��", problems.get(5).getName());
			Assert.assertEquals("��Ŀ��", problems.get(6).getName());
			Assert.assertEquals("��Ŀ��", problems.get(7).getName());
			Assert.assertEquals("��Ŀ��", problems.get(8).getName());
			Assert.assertEquals("��Ŀʮ", problems.get(9).getName());

			// ��Ŀ����
			Assert.assertEquals("a+b?", problems.get(0).getDescription());
			Assert.assertEquals("a-b?", problems.get(1).getDescription());
			Assert.assertEquals("a*b?", problems.get(2).getDescription());
			Assert.assertEquals("a/b?", problems.get(3).getDescription());
			Assert.assertEquals("a%b?", problems.get(4).getDescription());
			Assert.assertEquals("a��ƽ��?", problems.get(5).getDescription());
			Assert.assertEquals("a������?", problems.get(6).getDescription());
			Assert.assertEquals("a�Ľ׳�?", problems.get(7).getDescription());
			Assert.assertEquals("a���ۼ�?", problems.get(8).getDescription());
			Assert.assertEquals("a��b�η�?", problems.get(9).getDescription());

			// ��Ŀ����
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

			// ��Ŀ���
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
