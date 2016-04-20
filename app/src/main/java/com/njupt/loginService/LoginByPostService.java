package com.njupt.loginService;

import com.njupt.loginServicetool.StreamTools;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginByPostService {
	public static String LoginByPostService(String username, String password) {
		try {
			String path = "http://192.168.1.101:8080/VirusWebService/BadWebGet";
			String username_utf = URLEncoder.encode(username, "utf-8");
			String data = "username=" +username_utf+ "&password=" +password;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", data.length() + "");
			// post方法实质是生成输出流给服务器传值
			// 允许连接开启输出流
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());

			int code = conn.getResponseCode();
			System.out.println(code);
			if (code == 200) {
				InputStream ins = conn.getInputStream();
				String responseResult = StreamTools.changeInputStream(ins);
				if (responseResult.length() != 0) {
					return responseResult;
				} else {
					return null;
				}

			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
