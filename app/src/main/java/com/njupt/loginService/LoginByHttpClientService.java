package com.njupt.loginService;

import com.njupt.loginServicetool.StreamTools;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class LoginByHttpClientService {
    public static String LoginByHttpClientGETService(String username,
                                                     String password) {
        try {
            // 1、打开浏览器
            HttpClient client = new DefaultHttpClient();

            // 2、输入地址
            String path = "http://192.168.1.101:8080/VirusWebService/BadWebGet?username="
                    + URLEncoder.encode(username, "utf-8")
                    + "&password="
                    + URLEncoder.encode(password, "utf-8");
            HttpGet httpurl = new HttpGet(path);
            // 3、敲回车
            HttpResponse response = client.execute(httpurl);
            // 获得返回码
            int code = response.getStatusLine().getStatusCode();

            if (code == 200) {
                // 获得数据实体，由实体获得数据流
                InputStream is = response.getEntity().getContent();
                String responseResult = StreamTools.changeInputStream(is);
                System.out.println("responseResult" + responseResult);
                return responseResult;

            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    public static String LoginByHttpClientPOSTService(String username,
                                                      String password) {
        try {
            // 1、打开浏览器
            HttpClient client = new DefaultHttpClient();

            // 2、输入地址
            String path = "http://192.168.1.101:8080/VirusWebService/BadWebGet";
            HttpPost httpurl = new HttpPost(path);
            // 指定要提交的数据实体
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("username", username));
            parameters.add(new BasicNameValuePair("password", password));
            httpurl.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));

            // 3、敲回车
            HttpResponse response = client.execute(httpurl);
            // ��÷�����
            int code = response.getStatusLine().getStatusCode();

            if (code == 200) {
                // 获得数据实体，由实体获得数据流
                InputStream is = response.getEntity().getContent();
                String responseResult = StreamTools.changeInputStream(is);
                System.out.println("responseResult" + responseResult);
                return responseResult;

            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }
}
