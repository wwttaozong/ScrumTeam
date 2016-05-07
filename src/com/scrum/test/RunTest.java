package com.scrum.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.scrum.utils.OSExecuteUtils;

public class RunTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 测试运行功能
	 */
	@Test
	public void testRun() {

		// HelloWorld程序
		OSExecuteUtils.command("gcc HelloWorld.c");
		String[] feekback1 = OSExecuteUtils.command("a.exe");
		Assert.assertEquals("HelloWorld", feekback1[0]);
	}

}
