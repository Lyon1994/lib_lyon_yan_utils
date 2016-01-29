package org.lyon_yan.app.android.lib_lyon_yan_utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * List工具集
 * 
 * @author Lyon_Yan
 *
 */
public class Utils_List {
	/**
	 * 获取t2在t1中不相同的值
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static <T> List<T> compare(T[] t1, T[] t2) {
		List<T> list1 = Arrays.asList(t1);
		List<T> list2 = new ArrayList<T>();
		for (T t : t2) {
			if (!list1.contains(t)) {
				list2.add(t);
			}
		}
		return list2;
	}
}
