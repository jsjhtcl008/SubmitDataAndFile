package com.njupt.submitserver;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.njupt.loginService.LoginByGetService;
import com.njupt.loginService.LoginByHttpClientService;
import com.njupt.loginService.LoginByPostService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText editUsername, editPassword;
	private Button button_get, button_post, button_httpclient,
			button_httpclient2, button_asynvhttpclient,button_change;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		editUsername = (EditText) findViewById(R.id.editUesrname);
		editPassword = (EditText) findViewById(R.id.editPassword);
		button_get = (Button) findViewById(R.id.buttonByGet);
		button_post = (Button) findViewById(R.id.buttonByPost);
		button_httpclient = (Button) findViewById(R.id.buttonByHttpGETClient);
		button_httpclient2 = (Button) findViewById(R.id.buttonByHttpPOSTClient);
		button_asynvhttpclient = (Button) findViewById(R.id.buttonByAsyncHttpPOSTClient);
		button_change=(Button)findViewById(R.id.buttonChange);
		button_get.setOnClickListener(listener);
		button_post.setOnClickListener(listener2);
		button_httpclient.setOnClickListener(listener3);
		button_httpclient2.setOnClickListener(listener4);
		button_asynvhttpclient.setOnClickListener(listener5);
		button_change.setOnClickListener(listener6);
	}
	
	Button.OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			final String username = editUsername.getText().toString().trim();
			final String password = editPassword.getText().toString().trim();

			// 启用get方法提交数据
			new Thread() {
				public void run() {
					final String result = LoginByGetService.LoginByGetService(
							username, password);

					if (result != null) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this, result, 0)
										.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this,
										"登陆失败，少年！！！", 0).show();
							}
						});
					}

				};
			}.start();

		}

	};

	Button.OnClickListener listener2 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			final String username = editUsername.getText().toString().trim();
			final String password = editPassword.getText().toString().trim();

			// 启用post方法提交数据
			new Thread() {
				public void run() {
					final String result = LoginByPostService
							.LoginByPostService(username, password);

					if (result != null) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this, result, 0)
										.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this,
										"登陆失败，少年！！！", 0).show();
							}
						});
					}

				};
			}.start();

		}

	};

	Button.OnClickListener listener3 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			final String username = editUsername.getText().toString().trim();
			final String password = editPassword.getText().toString().trim();

			new Thread() {
				public void run() {
					final String result = LoginByHttpClientService
							.LoginByHttpClientGETService(username, password);

					System.out.println("result-------" + result);
					System.out.println(result != null);

					if (result != null) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this, result, 0)
										.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this,
										"登陆失败，少年！！！", 0).show();
							}
						});
					}

				};
			}.start();

		}

	};

	Button.OnClickListener listener4 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			final String username = editUsername.getText().toString().trim();
			final String password = editPassword.getText().toString().trim();

			// 启用post方法提交数据
			new Thread() {
				public void run() {
					final String result = LoginByHttpClientService
							.LoginByHttpClientPOSTService(username, password);

					System.out.println("result-------" + result);
					System.out.println(result != null);

					if (result != null) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this, result, 0)
										.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(LoginActivity.this,
										"登陆失败，少年！！！", 0).show();
							}
						});
					}

				};
			}.start();

		}

	};

	Button.OnClickListener listener5 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String username = editUsername.getText().toString().trim();
			String password = editPassword.getText().toString().trim();

			// 1、打开浏览器
			AsyncHttpClient client = new AsyncHttpClient();

			// 2、输入地址
			String path = "http://10.10.201.203:8080/VirusWebService/BadWebGet";

			RequestParams params = new RequestParams();
			params.put("username", username);
			params.put("password", password);

			client.post(path, params, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode, Header[] headers,
						byte[] responseBody) {
					String result;
					try {
						result = new String(responseBody,"GBK");
						Toast.makeText(LoginActivity.this, result, 0).show();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {
					Toast.makeText(LoginActivity.this, "登陆失败，少年！！！", 0).show();
				}
			});

		}

	};
	
	
	Button.OnClickListener listener6 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent =new Intent();
			intent.setClass(LoginActivity.this, FileLoadActivity.class);
			startActivity(intent);
			
		}
	};

}
