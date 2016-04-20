package com.njupt.loginServicetool;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTools {

    /**
     * @param is输入流
     * @return 输入流转成的字符串
     * @throws Exception
     */
    public static String changeInputStream(InputStream is) throws Exception {
        // 生成字节数组的输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int length = 0;
        // 创建1K大小的字节数组
        byte[] buffer = new byte[1024];
        // is输入流每次输出一个1K大小的字节数组至buffer[]中
        while ((length = is.read(buffer)) != -1) {
            // 将buffer[]内容写入内存输出流
            bos.write(buffer, 0, length);
        }
        is.close();
        bos.close();
        // bos输出流转成byte[]
        byte[] result = bos.toByteArray();
        // 必须判断服务器的数据编码格式，否则返回的是乱码
        String temp = new String(result);
        if (temp.contains("utf-8")) {
            return temp;
        } else if (temp.contains("gbk")) {
            return new String(result, "gbk");
        } else if (temp.contains("gb2312")) {
            return new String(result, "gb2312");
        }

        return new String(result, "gbk");

    }
}
