package com.njupt.loginServicetool;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {

	/**
	 * @param is������
	 * @return ������ת�ɵ��ַ���
	 * @throws Exception
	 */
	public static String changeInputStream(InputStream is) throws Exception {
		// �����ֽ�����������
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int length = 0;
		// ����1K��С���ֽ�����
		byte[] buffer = new byte[1024];
		// is������ÿ�����һ��1K��С���ֽ�������buffer[]��
		while ((length = is.read(buffer)) != -1) {
			// ��buffer[]����д���ڴ������
			bos.write(buffer, 0, length);
		}
		is.close();
		bos.close();
		// bos�����ת��byte[]
		byte[] result = bos.toByteArray();
		// �����жϷ����������ݱ����ʽ�����򷵻ص�������
		String temp = new String(result);
		if (temp.contains("utf-8")) {
			return temp;
		} else if (temp.contains("gbk")) {
			return new String(result, "gbk");
		} else if (temp.contains("gb2312")) {
			return new String(result, "gb2312");
		}

		return  new String(result, "gbk");

	}
}
