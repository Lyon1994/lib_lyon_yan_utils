package org.lyon_yan.app.android.lib_lyon_yan_utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class UtilsNet {
	public static String getLocalIpV6Address() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("lyon_1", ex.toString());
		}
		return null;
	}

	public static String getLocalIpV4Address() {

		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(inetAddress
									.getHostAddress())) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			// TODO: handle exception
			Log.e("lyon_1", e.toString());
		}

		return null;
	}

	// // 得到本机ip地址
	// public static String getLocalHostIp() {
	// String ipaddress = "";
	// try {
	// Enumeration<NetworkInterface> en = NetworkInterface
	// .getNetworkInterfaces();
	// // 遍历所用的网络接口
	// while (en.hasMoreElements()) {
	// NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
	// Enumeration<InetAddress> inet = nif.getInetAddresses();
	// // 遍历每一个接口绑定的所有ip
	// while (inet.hasMoreElements()) {
	// InetAddress ip = inet.nextElement();
	// if (!ip.isLoopbackAddress()
	// && InetAddressUtils.isIPv4Address(ip
	// .getHostAddress())) {
	// return ipaddress = "本机的ip是" + "：" + ip.getHostAddress();
	// }
	// }
	//
	// }
	// } catch (SocketException e) {
	// Log.e("feige", "获取本地ip地址失败");
	// e.printStackTrace();
	// }
	// return ipaddress;
	//
	// }

	// 得到本机Mac地址
	public static String getLocalMac(Activity activity) {
		String mac = "";
		// 获取wifi管理器
		WifiManager wifiMng = (WifiManager) activity
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfor = wifiMng.getConnectionInfo();
		mac = "本机的mac地址是：" + wifiInfor.getMacAddress();
		return mac;
	}
}
