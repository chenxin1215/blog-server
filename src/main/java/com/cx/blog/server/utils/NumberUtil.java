/*
 * Copyright (c) 2016, All rights reserved.
 */
package com.cx.blog.server.utils;

import java.math.BigDecimal;

/**
 * 数字工具类
 *
 * @author nameless
 * @date 2018/10/26
 */
public class NumberUtil extends org.apache.commons.lang3.math.NumberUtils {

    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT =
        {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟"};
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    /**
     * 缩放比例：100
     */
    public static final int SCALE_HUNDRED = 100;

    /**
     * 缩放比例：1000
     */
    public static final int SCALE_THOUSAND = 1000;

    /**
     * 缩放比例：10000
     */
    public static final int SCALE_TEN_THOUSAND = 10000;

    /**
     * 把输入的金额转换为汉语中人民币的大写
     *
     * @param numberOfMoney 输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        // 这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int)(number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }

    /**
     * 补齐0
     *
     * @param number 序列
     * @param count 补齐到多少位
     * @return
     */
    public static String fillZero(Integer number, int count) {
        String numStr = number + "";
        String retStr = "";
        for (int i = 0; i < count; i++) {
            retStr += "0";
        }
        return retStr.substring(numStr.length()) + numStr;
    }

    /**
     * 获取最大值
     *
     * @param values
     * @return
     */
    public static Integer max(Integer... values) {
        Integer maxValue = Integer.MIN_VALUE;
        for (Integer value : values) {
            if (null == value) {
                continue;
            }
            maxValue = Math.max(maxValue, value);
        }
        if (maxValue == Integer.MIN_VALUE) {
            return 0;
        } else {
            return maxValue;
        }
    }

    /**
     * 获取最小值
     *
     * @param values
     * @return
     */
    public static Integer min(Integer... values) {
        Integer minValue = Integer.MAX_VALUE;
        for (Integer value : values) {
            if (null == value) {
                continue;
            }
            minValue = Math.min(minValue, value);
        }
        if (minValue == Integer.MAX_VALUE) {
            return 0;
        } else {
            return minValue;
        }
    }

    /**
     * 功能描述: 元转成分
     *
     * @Author: chenxin
     * @Date: 2020/3/25
     */
    public static BigDecimal changeFenToYuanToBigDecimal(Integer price) {
        if (price == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(price).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 功能描述: 元转成分
     *
     * @Author: chenxin
     * @Date: 2020/3/25
     */
    public static BigDecimal changeFenToYuanToBigDecimal(Long price) {
        if (price == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(price).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 功能描述: 获取税率 1300 ==> 0.13
     *
     * @Author: chenxin
     * @Param: [taxRate]
     * @Date: 2020/4/27
     */
    public static BigDecimal getTaxRateToBigDecimal(Integer taxRate) {
        if (taxRate == null) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal(taxRate).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算商城商品价格 单位为分 取整
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer getMultiplyPrice(BigDecimal a, BigDecimal b) {
        return a.multiply(b).divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

}
