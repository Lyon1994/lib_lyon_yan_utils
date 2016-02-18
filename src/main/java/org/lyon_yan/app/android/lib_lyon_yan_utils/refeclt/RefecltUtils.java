package org.lyon_yan.app.android.lib_lyon_yan_utils.refeclt;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
     * @param name
     * @return
     * @author Lyon_Yan <br>
     * <b>time</b>: 2015年10月31日 下午3:35:46
     */
    public static String returnSetName(String name) {// 将属性的首字符大写，方便构造get，set方法
        return SET_ + returnUpperCaseFirstChar(name);
    }

    /**
     * 将属性的首字符大写，方便构造get，set方法
     *
     * @param name
     * @return
     * @author Lyon_Yan <br>
     * <b>time</b>: 2015年10月31日 下午3:35:46
     */
    public static String returnGetName(String name) {// 将属性的首字符大写，方便构造get，set方法
        return GET_ + returnUpperCaseFirstChar(name);
    }

    /**
     * 将属性的首字符大写，方便构造get，set方法
     *
     * @param name
     * @return
     * @author Lyon_Yan <br>
     * <b>time</b>: 2015年11月3日 上午11:03:52
     */
    public static String returnUpperCaseFirstChar(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * 获取泛型
     *
     * @param field
     * @return
     */
    public static List<Class> getGenericClassesFromType(Field field) {
        field.setAccessible(true);
        String genericTypes = field.getGenericType().toString().replace(field.getType().getName(), "").replace("<", "").replace(">", "");
        genericTypes = genericTypes.replace(" ", "");
        List<Class> classes = new ArrayList<>();
        try {
            if (genericTypes.contains(",")) {
                String[] clazzes = genericTypes.split(",");
                for (String clazz : clazzes) {
                    classes.add(Class.forName(clazz));
                }
            } else {
                classes.add(Class.forName(genericTypes));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 获取泛型
     *
     * @param field
     * @return
     */
    public static Class getGenericClassFromType(Field field) {
        List<Class> classes = getGenericClassesFromType(field);
        if (classes.size() > 0) {
            return classes.get(0);
        }
        return null;
    }
}
