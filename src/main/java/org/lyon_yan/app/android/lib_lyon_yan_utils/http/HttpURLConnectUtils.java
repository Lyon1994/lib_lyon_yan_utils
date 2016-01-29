package org.lyon_yan.app.android.lib_lyon_yan_utils.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

/**
 * URL快速访问工具Lite
 * 
 * @author Lyon_Yan <br/>
 *         <b>time</b>: 2015年10月12日 下午2:53:44
 */
public class HttpURLConnectUtils {
	private String data = null;
	private HttpURLConnection urlConn = null;

	public HttpURLConnectUtils HttpURLConnectionInit(String tempUrl) {
		try {
			urlConn = (HttpURLConnection) new URL(tempUrl).openConnection();
			return this;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String postRequest(final String postData) {
		try {
			urlConn.setRequestMethod("POST");
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(true);
			// postData = "data=byPostMethod";
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConn.connect();
			DataOutputStream out = new DataOutputStream(
					urlConn.getOutputStream());
			out.writeBytes(postData);
			out.flush();
			out.close();
			InputStreamReader in = new InputStreamReader(
					urlConn.getInputStream());
			BufferedReader buffer = new BufferedReader(in);
			String temp = null;
			data = "";
			while ((temp = buffer.readLine()) != null) {
				data = data + temp;
			}
			in.close();
			urlConn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public String getRequest() {
		try {
			if(urlConn==null){
				Log.v("lyon2", "==========>" + "urlConn==null:" + data);
			}
			Log.v("lyon2", "==========>" + "1:" + data);
			urlConn.setRequestMethod("GET");
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(true);
			// postData = "data=byPostMethod";
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConn.connect();
			Log.v("lyon2", "==========>" + "2:" + data);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			Log.v("lyon2", "==========>" + "3:" + data);
			String temp = null;
			StringBuffer data = new StringBuffer("");
			Log.v("lyon2", "==========>" + "4:" + data);
			while ((temp = buffer.readLine()) != null) {
				data.append(temp);
			}
			Log.v("lyon2", "==========>" + "5:" + data);
			buffer.close();
			Log.v("lyon2", "==========>" + "6:" + data);
			this.data = data.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			urlConn.disconnect();
		}
		return data;
	}
}