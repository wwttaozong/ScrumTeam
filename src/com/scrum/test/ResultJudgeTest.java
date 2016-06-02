package com.scrum.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.scrum.tools.GccTools;
import com.scrum.tools.ResultJudgeTools;
import com.scrum.utils.OSExecuteUtils;

public class ResultJudgeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testResultJudge() {

		// 编译运行HelloWorld程序并进行结果比对
		boolean isExists1 = GccTools.isFileExists("HelloWorld.c");
		Assert.assertTrue(isExists1);
		boolean hasGcc1 = OSExecuteUtils.checkGccEnv();
		Assert.assertTrue(hasGcc1);
		String[] compileResult1 = OSExecuteUtils.command("gcc HelloWorld.c");
		String compileOutput1 = compileResult1[0];
		String compileError1 = compileResult1[1];
		Assert.assertTrue(compileOutput1.trim().equals(""));
		File file1=new File("a.exe");
		Assert.assertTrue(file1.exists());
		String[] runResult1 = OSExecuteUtils.command("a.exe");
		String runOutput1 = runResult1[0];
		String runError1 = runResult1[1];
		Assert.assertTrue(runError1.trim().equals(""));
		Assert.assertTrue(ResultJudgeTools
				.resultJudge(runOutput1, "HelloWorld"));

		// 编译运行a+b程序并进行结果比对
		boolean isExists2 = GccTools.isFileExists("Add.c");
		Assert.assertTrue(isExists2);
		boolean hasGcc2 = OSExecuteUtils.checkGccEnv();
		Assert.assertTrue(hasGcc2);
		String[] compileResult2 = OSExecuteUtils.command("gcc Add.c");
		String compileOutput2 = compileResult2[0];
		String compileError2 = compileResult2[1];
		File file2=new File("a.exe");
		Assert.assertTrue(file2.exists());
		Assert.assertTrue(compileOutput2.trim().equals(""));
		String[] runResult2 = OSExecuteUtils.command("a.exe", "1", "2");
		String output2 = runResult2[0];
		String error2 = runResult2[1];
		Assert.assertTrue(error2.trim().equals(""));
		Assert.assertEquals("3", output2);
	}

}
