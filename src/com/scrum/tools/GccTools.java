package com.scrum.tools;

import java.io.File;

import com.scrum.utils.OSExecuteUtils;

public class GccTools {

	// ����ָ��
	private static final String COMPILE_COMMAND = "gcc temp.c";
	// ����ָ��
	private static final String RUN_COMMAND = "a.exe";

	/**
	 * �������
	 */
	public static String[] complie() {
		return OSExecuteUtils.command(COMPILE_COMMAND);
	}

	/**
	 * ���г���
	 */
	public static String[] run() {
		return OSExecuteUtils.command(RUN_COMMAND);
	}

	/**
	 * �ж��ļ��Ƿ����
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
