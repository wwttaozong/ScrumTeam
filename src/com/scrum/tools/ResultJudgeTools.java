package com.scrum.tools;

public class ResultJudgeTools {

	/**
	 * ����б�
	 * 
	 * @param output
	 * @param expected
	 * @return
	 */
	public static boolean resultJudge(String output, String expected) {
		boolean judge = false;
		if (output.equals(expected)) {
			judge = true;
		}
		return judge;
	}

}
