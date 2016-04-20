package com.njupt.loginService;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.njupt.loginServicetool.StreamTools;

public class LoginByGetService {
	public static String LoginByGetService(String username, String password) {
		try {
			String path = "http://10.10.201.203:8080/VirusWebService/BadWebGet?username="
					+URLEncoder.encode(username, "utf-8")+ "&password=" + URLEncoder.encode(password, "utf-8") ;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setRequestMethod("GET");
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
