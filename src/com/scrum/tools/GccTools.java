package com.scrum.tools;

import java.io.File;

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

	/**
	 * 判断文件是否存在
	 * 
	 * @return
	 */
	public static boolean isFileExists(String fileName) {
		boolean judge = false;
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			judge = true;
		}
		return judge;
	}
}
