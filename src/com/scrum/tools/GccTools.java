package com.scrum.tools;

import com.scrum.utils.OSExecuteUtils;

public class GccTools {

	// 编译指令
	private static final String COMPILE_COMMAND = "gcc temp.c";
	// 运行指令
	private static final String RUN_COMMAND = "a.exe";

	/**
	 * 编译程序
	 */
	public static String[] complie() {
		return OSExecuteUtils.command(COMPILE_COMMAND);
	}

	/**
	 * 运行程序
	 */
	public static String[] run() {
		return OSExecuteUtils.command(RUN_COMMAND);
	}
}
