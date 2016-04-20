package com.njupt.submitserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.apache.http.*;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileLoadActivity extends Activity implements OnClickListener {
	private Button submit;
	private EditText text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_load);
		submit = (Button) findViewById(R.id.submitbutton);
		text = (EditText) findViewById(R.id.editText);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submitbutton:
			String filepath = text.getText().toString().trim();
			File file = new File(filepath);
			if (file.length() > 0 && file.exists()) {
				AsyncHttpClient client = new AsyncHttpClient();
				String path = "http://10.10.201.203:8080/FlowDroid/UpLoad";
				RequestParams params = new RequestParams();
				try {
					params.put("myfile", file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				client.post(path, params, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						Toast.makeText(FileLoadActivity.this, "上传成功", 0).show();

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						Toast.makeText(FileLoadActivity.this, "上传失败", 0).show();

					}
				});
			} else {
				Toast.makeText(FileLoadActivity.this, "上传文件不存在", 0).show();
			}

			break;

		default:
			break;
		}
	}
}
