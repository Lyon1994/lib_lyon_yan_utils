package org.lyon_yan.app.android.lib_lyon_yan_utils;

/**
 * Created by yanni on 2016/2/16.
 */
public class UtilsBoolean {
    /**
     * 用于final标记的boolean型可变变量
     */
    public static class FinalBoolean{
        private boolean isBoolean;

        public boolean isBoolean() {
            return isBoolean;
        }

        public void setIsBoolean(boolean isBoolean) {
            this.isBoolean = isBoolean;
        }
    }
}
