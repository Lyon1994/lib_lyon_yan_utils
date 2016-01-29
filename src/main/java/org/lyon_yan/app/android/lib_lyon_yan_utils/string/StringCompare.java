package org.lyon_yan.app.android.lib_lyon_yan_utils.string;

import java.util.Arrays;

/**
 * 字符串比较类
 * 
 * @author Lyon_Yan <br>
 *         <b>time</b>: 2015年11月4日 下午3:16:34
 */
public class StringCompare {
	/**
	 * 比较两个字符串中的内容是否相同
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年11月4日 下午3:16:55
	 * @param text1
	 * @param text2
	 * @return
	 */
	public static boolean equals(String text1, String text2) {
		char[] array1 = text1.toCharArray();
		char[] array2 = text2.toCharArray();
		Arrays.sort(array1);
		Arrays.sort(array2);
		return Arrays.equals(array1, array2);
	}
}
