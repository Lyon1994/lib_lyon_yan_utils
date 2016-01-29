package org.lyon_yan.app.android.lib_lyon_yan_utils.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * SharedPreferences方式进行日志文件的存取(仅Android平台)
 * 
 * @author 颜宁
 *
 */
public class DataRecordByAndroid {

	private SharedPreferences preferences = null;

	public DataRecordByAndroid(Context c) {
		preferences = PreferenceManager.getDefaultSharedPreferences(c);
	}

	public void saveValue(String key, String value) throws Exception {
		// TODO Auto-generated method stub
		// 获得编辑器
		Editor editor = preferences.edit();
		// 存值
		editor.putString(key, value);
		// 提交
		editor.commit();
	}

	public void saveValueFloat(String key, float value) throws Exception {
		// TODO Auto-generated method stub
		// 获得编辑器
		Editor editor = preferences.edit();
		// 存值
		editor.putFloat(key, value);
		// 提交
		editor.commit();
	}
	

	public void saveValueInt(String key, int value) throws Exception {
		// TODO Auto-generated method stub
		// 获得编辑器
		Editor editor = preferences.edit();
		// 存值
		editor.putInt(key, value);
		// 提交
		editor.commit();
	}

	public float getValueFloat(String key) throws Exception {
		// TODO Auto-generated method stub
		// 取值，第二个参数是当key不存在时返回的默认值。
		return getValueFloat(key, 0);
	}

	public float getValueInt(String key) throws Exception {
		// TODO Auto-generated method stub
		// 取值，第二个参数是当key不存在时返回的默认值。
		return getValueInt(key, 0);
	}

	public float getValueFloat(String key, float defValue) throws Exception {
		// TODO Auto-generated method stub
		// 取值，第二个参数是当key不存在时返回的默认值。
		if (preferences.contains(key)) {
			float values = preferences.getFloat(key, defValue);
			return values;
		} else {
			return defValue;
		}
	}public float getValueInt(String key, int defValue) throws Exception {
		// TODO Auto-generated method stub
		// 取值，第二个参数是当key不存在时返回的默认值。
		if (preferences.contains(key)) {
			float values = preferences.getInt(key, defValue);
			return values;
		} else {
			return defValue;
		}
	}

	public String getValue(String key) throws Exception {
		// TODO Auto-generated method stub
		// 取值，第二个参数是当key不存在时返回的默认值。
		return getValue(key, "");
	}

	public String getValue(String key, String defValue) throws Exception {
		// TODO Auto-generated method stub
		// 取值，第二个参数是当key不存在时返回的默认值。
		if (preferences.contains(key)) {
			String values = preferences.getString(key, defValue);
			return values;
		} else {
			return defValue;
		}
	}

	public boolean remove(String key) {
		// TODO Auto-generated method stub
		try {

			if (preferences.contains(key)) {
				// 获得编辑器
				Editor editor = preferences.edit();
				// 存值
				editor.remove(key);
				// 提交
				editor.commit();
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
