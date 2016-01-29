package org.lyon_yan.app.android.lib_lyon_yan_utils;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class Utils_Textview {
	private boolean change_flicker = false;

	public void startFlicker(final TextView textView, Looper looper,
			final long duration1, long duration2) {
		final Handler handler = new Handler(looper) {
			@Override
			public void dispatchMessage(Message msg) {
				if (change_flicker) {
					change_flicker = false;
					textView.setTextColor(Color.TRANSPARENT); // 这个是透明，=看不到文字
				} else {
					try {
						change_flicker = true;
						textView.setTextColor(Color.RED);
						Thread.sleep(duration1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				handler.sendMessage(msg);
			}
		};
		timer.schedule(task, 1, duration2); // 参数分别是delay（多长时间后执行），duration（执行间隔）
	}

}
