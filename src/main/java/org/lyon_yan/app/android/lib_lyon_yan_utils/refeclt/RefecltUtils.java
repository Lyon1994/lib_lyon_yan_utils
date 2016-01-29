package org.lyon_yan.app.android.lib_lyon_yan_utils.refeclt;

/**
 * refelect工具包
 * 
 * @author Lyon_Yan <br>
 *         <b>time</b>: 2015年10月31日 下午3:35:27
 */
public class RefecltUtils {

	private static final String SET_ = "set";
	private static final String GET_ = "get";

	/**
	 * 将属性的首字符大写，方便构造get，set方法
	 * 
	 * @author Lyon_Yan <br>
	 *         <b>time</b>: 2015年10月31日 下午3:35:46
	 * @param name
	 * @return
	 */
	public static String returnSetName(String name) {// 将属性的首字符大写，方便构造get，set方法
		return SET_ + returnUpperCaseFirstChar(name);
	}

	/**
	 * 将属性的首字符大写，方便构造get，set方法
	 * 
	 * @author Lyon_Yan <br>
	 *         <b>time</b>: 2015年10月31日 下午3:35:46
	 * @param name
	 * @return
	 */
	public static String returnGetName(String name) {// 将属性的首字符大写，方便构造get，set方法
		return GET_ + returnUpperCaseFirstChar(name);
	}

	/**
	 * 将属性的首字符大写，方便构造get，set方法
	 * 
	 * @author Lyon_Yan <br>
	 *         <b>time</b>: 2015年11月3日 上午11:03:52
	 * @param name
	 * @return
	 */
	public static String returnUpperCaseFirstChar(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
}
