package com.scrum.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * 用于执行操作系统上的其他程序并以其输出流和错误流作为本程序的输入流
 * 
 * @author wwt
 *
 */
public class OSExecuteUtils {

	private static Process process;

	/**
	 * 执行指定的程序并获取本程序的输入流
	 * 
	 * @param command执行指定程序所需要的参数
	 * @throws IOException
	 */
	public static String[] command(String command, String... params) {
		// back[0]存储结果流
		// back[1]存储错误流
		String[] feedbacks = new String[2];
		try {
			process = new ProcessBuilder(command.split(" ")).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintStream init = System.out;
			// 向程序输入参数
			PrintStream printStream = new PrintStream(process.getOutputStream());
			System.setOut(printStream);
			for (String param : params) {
				System.out.println(param);
			}
			printStream.close();
			System.setOut(init);

			String s = null;
			// 获取其他程序的输出流（本程序的输入流）
			BufferedReader results = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			StringBuilder sb1 = new StringBuilder();
			while ((s = results.readLine()) != null) {
				sb1.append(s);
			}
			feedbacks[0] = sb1.toString();

			// 获取其他程序的错误流（本程序的输入流）
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
	 * 检查系统中是否存在GCC编译环境
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
