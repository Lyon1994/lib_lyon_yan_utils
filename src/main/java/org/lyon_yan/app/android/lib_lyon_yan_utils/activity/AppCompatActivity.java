package org.lyon_yan.app.android.lib_lyon_yan_utils.activity;

import android.os.Bundle;
import android.view.WindowManager;

/**
 * 自定义Activity
 * 
 * @author 颜宁
 *
 */
public class AppCompatActivity extends android.support.v7.app.AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sysinitForFullScreen();
	}
	
	/**
	 * Activity全屏属性设置
	 */
	private void sysinitForFullScreen() {
		// TODO Auto-generated method stub
		// 隐去标题栏（应用程序的名字）
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 隐去状态栏部分(电池等图标和一切修饰部分)
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
