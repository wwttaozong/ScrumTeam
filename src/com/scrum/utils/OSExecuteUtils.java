package com.scrum.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ����ִ�в���ϵͳ�ϵ�������������������ʹ�������Ϊ�������������
 * 
 * @author wwt
 *
 */
public class OSExecuteUtils {

	/**
	 * ִ��ָ���ĳ��򲢻�ȡ�������������
	 * 
	 * @param commandִ��ָ����������Ҫ�Ĳ���
	 * @throws IOException
	 */
	public static String[] command(String command) {
		// back[0]�洢�����
		// back[1]�洢������
		String[] feedbacks = new String[2];
		try {
			// ����ָ������
			Process process = new ProcessBuilder(command.split(" ")).start();

			String s = null;
			// ��ȡ�������������������������������
			BufferedReader results = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			StringBuilder sb1 = new StringBuilder();
			while ((s = results.readLine()) != null) {
				sb1.append(s);
			}
			feedbacks[0] = sb1.toString();

			// ��ȡ��������Ĵ����������������������
			BufferedReader errors = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			StringBuilder sb2 = new StringBuilder();
			while ((s = errors.readLine()) != null) {
				sb2.append(s);
			}
			feedbacks[1] = sb2.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbacks;
	}// end of command

}
