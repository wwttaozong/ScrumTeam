package com.scrum.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * ����ִ�в���ϵͳ�ϵ�������������������ʹ�������Ϊ�������������
 * 
 * @author wwt
 *
 */
public class OSExecuteUtils {

	private static Process process;

	/**
	 * ִ��ָ���ĳ��򲢻�ȡ�������������
	 * 
	 * @param commandִ��ָ����������Ҫ�Ĳ���
	 * @throws IOException
	 */
	public static String[] command(String command, String... params) {
		// back[0]�洢�����
		// back[1]�洢������
		String[] feedbacks = new String[2];
		try {
			process = new ProcessBuilder(command.split(" ")).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintStream init = System.out;
			// ������������
			PrintStream printStream = new PrintStream(process.getOutputStream());
			System.setOut(printStream);
			for (String param : params) {
				System.out.println(param);
			}
			printStream.close();
			System.setOut(init);

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

			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return feedbacks;
	}// end of command

	/**
	 * ���ϵͳ���Ƿ����GCC���뻷��
	 * 
	 * @return
	 */
	public static boolean checkGccEnv() {
		Process process;
		try {
			process = new ProcessBuilder("gcc --version".split(" ")).start();
		} catch (IOException e) {
			return false;
		}
		process.destroy();
		return true;
	}

}
