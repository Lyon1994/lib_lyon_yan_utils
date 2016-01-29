package org.lyon_yan.app.android.lib_lyon_yan_utils.view;

import android.view.View;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * 计算器套件的实现
 * Created by yanni on 2016/1/15.
 */
public class CaculaterViewUtils {
    private View num_0;
    private View num_1;
    private View num_2;
    private View num_3;
    private View num_4;
    private View num_5;
    private View num_6;
    private View num_7;
    private View num_8;
    private View num_9;
    /**
     * 小数点
     */
    private View num_point;
    /**
     * 加法
     */
    private View num_plus;
    /**
     * 减法
     */
    private View num_subtract;
    /**
     * 乘法
     */
    private View num_multiplicative;
    /**
     * 除法
     */
    private View num_division;
    /**
     * 清空
     */
    private View num_ce;
    /**
     * 删除
     */
    private View num_c;
    /**
     * 等号
     */
    private View num_submit;
    /**
     * 計算結果，用於顯示
     */
//    private String caculaterResult = "";
    /**
     * 計算緩存，用於暫存計算數據
     */
//    private String caculaterTemp = "";
    private LinkedHashMap<Integer, String> caculaterNums = new LinkedHashMap<>();
    private LinkedHashMap<Integer, String> caculaterWays = new LinkedHashMap<>();
    //    private static final String _POINT = "POINT";
    private static final String _PLUS = "＋";
    private static final String _DIVISION = "÷";
    private static final String _MULTIPLICATIVE = "×";
    private static final String _SUBTRACT = "－";
    //    private static final String _SUBMIT="_SUBMIT";
//    private boolean is_click_submit = false;
//    private String nextOption = null;

    /**
     * 点击“等号”时触发的事件
     */
    public interface AfterSubmitListener {
        void after(String caculater_result);
    }


    private AfterSubmitListener afterSubmitListener = null;
    private RefreshShowResultHandler refreshShowResultHandler = null;
    private RefreshShowResultHandler refreshShowCaculateWayHandler = null;

    /**
     * 刷新暂存数据
     */
    private void refreshShowResult() {
        if (caculaterNums.size() > 0) {
            refreshShowResult(caculaterNums.get(caculaterNums.size() - 1));
        }
    }

    private void refreshShowResult(String t) {
        if (refreshShowResultHandler != null) {
            if (t == null) {
                refreshShowResultHandler.refresh("");
            } else {
                refreshShowResultHandler.refresh(t);
            }
        }
    }

    private void refreshShowCaculateWay(String t) {
        if (refreshShowCaculateWayHandler != null) {
            if (t == null) {
                refreshShowCaculateWayHandler.refresh("=");
            } else {
                refreshShowCaculateWayHandler.refresh(t);
            }
        }
    }

    /**
     * 設置刷新計算結果的事件，用於顯示
     *
     * @param refreshShowResultHandler
     */
    public void setRefreshShowResultHandler(RefreshShowResultHandler refreshShowResultHandler) {
        this.refreshShowResultHandler = refreshShowResultHandler;
    }

    /**
     * 设置运算符号的显示
     *
     * @param refreshShowCaculateWayHandler
     */
    public void setRefreshShowCaculateWayHandler(RefreshShowResultHandler refreshShowCaculateWayHandler) {
        this.refreshShowCaculateWayHandler = refreshShowCaculateWayHandler;
    }

    public interface RefreshShowCaculateWayHandler extends RefreshShowResultHandler {

    }

    /**
     * 刷新计算结果显示的回调
     */
    public interface RefreshShowResultHandler {
        void refresh(String result);
    }

    /**
     * 設置數字鍵的點擊事件
     *
     * @param v
     * @param num
     */
    private void setNumClickListener(View v, int num) {
        v.setTag(num);
        v.setOnClickListener(num_numClickListener);
    }

    /**
     * 用於暫存view中tag的自定義標籤的索引
     */
//    private static final int NUM_KEY = 346272;
    /**
     * 数字键被点击事触发
     */
    private View.OnClickListener num_numClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (is_click_submit) {
                ce();
            }
            int value = (int) v.getTag();
            addCaculateNum(value + "");
            refreshShowResult();
        }
    };

    /**
     * 清空
     */
    private void ce() {
        caculaterNums.clear();
        caculaterWays.clear();
        is_add_next_caculateNum = false;
        is_click_submit = false;
        refreshShowResult("");
        refreshShowCaculateWay("＝");
    }

    /**
     * 回調被綁定的提交事件
     */
    private void callSubmitBack(String result) {
        if (afterSubmitListener != null) {
            afterSubmitListener.after(result);
        }
    }


    /**
     * 執行計算
     */
    private String caculte() {
        float result = 0;
        if (caculaterNums.size() > 1) {
            String s1 = caculaterNums.get(0);
            float r;
            if (s1.lastIndexOf((String) num_point.getTag()) == s1.length() - 1) s1 += "0";
            result = Float.parseFloat(s1);
            for (int i = 1; i < caculaterNums.size(); i++) {
                String s2 = caculaterNums.get(i);
                if (s2.lastIndexOf((String) num_point.getTag()) == s2.length() - 1) s2 += "0";
                r = Float.parseFloat(s2);
                switch (caculaterWays.get(i)) {
                    case _DIVISION:
                        result /= r;
                        break;
                    case _MULTIPLICATIVE:
                        result *= r;
                        break;
                    case _PLUS:
                        result += r;
                        break;
                    case _SUBTRACT:
                        result -= r;
                        break;
                    default:
                        break;
                }
            }
        } else {
            String r = caculaterNums.get(0);
            if (r.lastIndexOf((String) num_point.getTag()) == r.length() - 1) r += "0";
            result = Float.parseFloat(r);
        }
        BigDecimal b = new BigDecimal(result);
        return "" + b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 清屏操作
     */
    private void clearResultShow() {
        refreshShowResult("");
    }

    private void clearWayShow() {
        if (num_submit != null)
            refreshShowCaculateWay((String) num_submit.getTag());
    }

    public void setNum_0(View num_0) {
        num_0.setOnClickListener(num_numClickListener);
        num_0.setTag(0);
        this.num_0 = num_0;
    }

    public void setNum_1(View num_1) {
        num_1.setOnClickListener(num_numClickListener);
        num_1.setTag(1);
        this.num_1 = num_1;
    }

    public void setNum_2(View num_2) {
        num_2.setOnClickListener(num_numClickListener);
        num_2.setTag(2);
        this.num_2 = num_2;
    }

    public void setNum_3(View num_3) {
        num_3.setOnClickListener(num_numClickListener);
        num_3.setTag(3);
        this.num_3 = num_3;
    }

    public void setNum_4(View num_4) {
        num_4.setOnClickListener(num_numClickListener);
        num_4.setTag(4);
        this.num_4 = num_4;
    }

    public void setNum_5(View num_5) {
        num_5.setOnClickListener(num_numClickListener);
        num_5.setTag(5);
        this.num_5 = num_5;
    }

    public void setNum_6(View num_6) {
        num_6.setOnClickListener(num_numClickListener);
        num_6.setTag(6);
        this.num_6 = num_6;
    }

    public void setNum_7(View num_7) {
        num_7.setOnClickListener(num_numClickListener);
        num_7.setTag(7);
        this.num_7 = num_7;
    }

    public void setNum_8(View num_8) {
        num_8.setOnClickListener(num_numClickListener);
        num_8.setTag(8);
        this.num_8 = num_8;
    }

    public void setNum_9(View num_9) {
        num_9.setOnClickListener(num_numClickListener);
        num_9.setTag(9);
        this.num_9 = num_9;
    }

    public void setNum_point(View num_point) {
        num_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = (String) v.getTag();
                if (caculaterNums.size() > 0) {
                    addCaculateNum(value + "");
                } else {
                    addCaculateNum("0" + value);
                }
                refreshShowResult();
            }
        });
        num_point.setTag(".");
        this.num_point = num_point;
    }

    public void setNum_plus(View num_plus) {
        num_plus.setOnClickListener(caculaterWayOnclickListener);
        num_plus.setTag("＋");
        this.num_plus = num_plus;
    }

    public void setNum_subtract(View num_subtract) {
        num_subtract.setOnClickListener(caculaterWayOnclickListener);
        num_subtract.setTag("－");
        this.num_subtract = num_subtract;
    }

    /**
     * 监听运算符号
     */
    private View.OnClickListener caculaterWayOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            is_click_submit = false;
            if (caculaterNums.size() > 0) {
                addCaculateWay((String) v.getTag());
                refreshShowCaculateWay((String) v.getTag());
                is_add_next_caculateNum = true;
                clearResultShow();
            }
        }
    };

    public void setNum_multiplicative(View num_multiplicative) {
        num_multiplicative.setOnClickListener(caculaterWayOnclickListener);
        num_multiplicative.setTag("×");
        this.num_multiplicative = num_multiplicative;
    }

    public void setNum_division(View num_division) {
        num_division.setOnClickListener(caculaterWayOnclickListener);
        num_division.setTag("÷");
        this.num_division = num_division;
    }

    public void setNum_ce(View num_ce) {
        num_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ce();
            }
        });
        num_ce.setTag("CE");
        this.num_ce = num_ce;
    }

    public void setNum_c(View num_c) {


        num_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_click_submit) {
                    ce();
                    return;
                }
                if (caculaterNums.size() > 0) {
                    int index = caculaterNums.size() - 1;
                    String caculaterResult = caculaterNums.get(index);
                    int l;
                    if (caculaterResult != null && (l = caculaterResult.length()) > 0) {
                        caculaterResult = new StringBuilder(caculaterResult).deleteCharAt(l - 1).toString();
                        caculaterNums.put(index, caculaterResult);
                        refreshShowResult(caculaterNums.get(index));
                    }
                }
            }
        });
        num_c.setTag("C");
        this.num_c = num_c;
    }

    public void setNum_submit(final View num_submit) {
        num_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_click_submit = true;
                if (caculaterNums.size() > 0) {
                    clearResultShow();
                    refreshShowCaculateWay((String) v.getTag());
                    String result = caculte();
                    refreshShowResult(result);
                    callSubmitBack(result);
                }
            }
        });
        num_submit.setTag("＝");
        this.num_submit = num_submit;
    }

    /**
     * 设置点击提交后的监听
     *
     * @param afterSubmitListener
     */
    public void setAfterSubmitListener(AfterSubmitListener afterSubmitListener) {
        this.afterSubmitListener = afterSubmitListener;
    }

    private boolean is_add_next_caculateNum = false;

    /**
     * 加入待运算的数
     */
    private void addCaculateNum(String caculaterResult) {
        if (caculaterResult != null || caculaterResult.length() > 0) {
            if (is_add_next_caculateNum || caculaterNums.size() == 0) {
                caculaterNums.put(caculaterNums.size(), caculaterResult);
                is_add_next_caculateNum = false;
            } else if (caculaterNums.size() > 0) {
                int i = caculaterNums.size() - 1;
                caculaterNums.put(i, caculaterNums.get(i) + caculaterResult);
            }
        }
    }

    /**
     * 加入运算符号
     */
    private void addCaculateWay(String caculaterWay) {
        if (caculaterWay != null || caculaterWay.length() > 0) {
            caculaterWays.put(caculaterNums.size(), caculaterWay);
        }
    }

    private boolean is_click_submit = false;
}
