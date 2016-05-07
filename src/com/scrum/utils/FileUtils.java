package com.scrum.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ����ִ���ļ�����ز���
 * 
 * @author wwt
 *
 */
public class FileUtils {

	/**
	 * д���ļ�
	 * 
	 * @param pathĿ���ļ�·��
	 * @param contentд���ļ�������
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
