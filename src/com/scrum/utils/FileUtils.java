package com.scrum.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 用于执行文件的相关操作
 * 
 * @author wwt
 *
 */
public class FileUtils {

	/**
	 * 写入文件
	 * 
	 * @param path目标文件路径
	 * @param content写入文件的内容
	 * @throws IOException
	 */
	public static void writeToFile(String path, String content)
			throws IOException {
		BufferedOutputStream outputStream = new BufferedOutputStream(
				new FileOutputStream(path));
		outputStream.write(content.getBytes());
		outputStream.close();
	}

}
