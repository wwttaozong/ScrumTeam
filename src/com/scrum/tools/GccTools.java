package com.scrum.tools;

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
}
