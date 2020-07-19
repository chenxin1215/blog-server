/*
 * Copyright (c) 2015, All rights reserved.
 */
package com.cx.blog.server.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

/**
 * String utility functions
 * 
 * @author Zeal
 * @date 2015-12-14
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * @
     */
    public static final String AT = "@";

    /**
     * *
     */
    public static final String ASTERISK = "*";

    /**
     * :
     */
    public static final String COLON = ":";

    /**
     * ,
     */
    public static final String COMMA = ",";

    /**
     * -
     */
    public static final String DASH = "-";

    /**
     * $
     */
    public static final String DOLLAR = "$";

    /**
     * .
     */
    public static final String DOT = ".";

    /**
     * =
     */
    public static final String EQUALS = "=";

    /**
     * /
     */
    public static final String SLASH = "/";

    /**
     * #
     */
    public static final String HASH = "#";

    /**
     * %
     */
    public static final String PERCENT = "%";

    /**
     * |
     */
    public static final String PIPE = "|";

    /**
     * !
     */
    public static final String EXCLAMATION_MARK = "!";

    /**
     * <
     */
    public static final String LEFT_CHEV = "<";

    /**
     * >
     */
    public static final String RIGHT_CHEV = ">";

    /**
     * _
     */
    public static final String UNDERSCORE = "_";

    /** null字符串 */
    private static final String NULL = "null";

    /**
     * Convenience method to return a String array as a delimited (e.g. CSV) String. E.g. useful for {@code toString()}
     * implementations.
     * 
     * @param arr the array to display
     * @param delim the delimiter to use (probably a ",")
     * @return the delimited String
     */
    public static String arrayToDelimitedString(Object[] arr, String delim) {
        if (arr == null || arr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(delim);
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer. Trims tokens and omits empty tokens.
     * <p>
     * The given delimiters string is supposed to consist of any number of delimiter characters. Each of those
     * characters can be used to separate tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using {@code delimitedListToStringArray}
     * 
     * @param str the String to tokenize
     * @param delimiters the delimiter characters, assembled as String (each of those characters is individually
     *        considered as delimiter).
     * @return an array of the tokens
     * @see StringTokenizer
     * @see String#trim()
     */
    public static List<String> tokenizeToStringList(String str, String delimiters) {
        return tokenizeToStringList(str, delimiters, true, true);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * <p>
     * The given delimiters string is supposed to consist of any number of delimiter characters. Each of those
     * characters can be used to separate tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using {@code delimitedListToStringArray}
     *
     * @param str the String to tokenize
     * @param delimiters the delimiter characters, assembled as String (each of those characters is individually
     *        considered as delimiter)
     * @param trimTokens trim the tokens via String's {@code trim}
     * @param ignoreEmptyTokens omit empty tokens from the result array (only applies to tokens that are empty after
     *        trimming; StringTokenizer will not consider subsequent delimiters as token in the first place).
     * @return an array of the tokens ({@code null} if the input String was {@code null})
     * @see StringTokenizer
     * @see String#trim()
     */
    public static List<String> tokenizeToStringList(String str, String delimiters, boolean trimTokens,
        boolean ignoreEmptyTokens) {

        if (str == null) {
            return new ArrayList<>(0);
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    /**
     * Camel string to delimeted string like underline '_'
     * 
     * @param str
     * @param seperator
     * @return
     */
    public static String camelToDelimitedString(String str, String seperator) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder((int)((double)str.length() * 1.5));
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(seperator);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Convert to camel format
     * 
     * @param str
     * @param seperator like '_'
     * @return
     */
    public static String toCamelString(String str, char seperator) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        boolean toUppercase = false;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            // Convert next char to uppercase
            if (c == seperator) {
                toUppercase = true;
                continue;
            }
            if (toUppercase) {
                sb.append(Character.toUpperCase(c));
                toUppercase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String urlEncode(String src) {
        return urlEncode(src, "UTF-8");
    }

    public static String urlEncode(String src, String charset) {
        try {
            return URLEncoder.encode(src, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return src;
        }
    }

    /**
     * 随即生成指定位数的含验证码字符串
     * 
     * @author Peltason
     * 
     * @date 2007-5-9
     * @param bit 指定生成验证码位数
     * @return String
     */
    public static String randomStr(int bit) {
        if (bit == 0)
            bit = 6; // 默认6位
        // 因为o和0,l和1很难区分,所以,去掉大小写的o和l
        String str = "";
        str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }

    /**
     * 获取文件名称[不含后缀名]
     * 
     * @param
     * @return String
     */
    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
    }

    public static String insertTo(String source, String str, int index) {
        if (index > source.length())
            return source;

        return source.substring(0, index) + str + source.substring(index, source.length());
    }

    /**
     * 生成编码方法(自动补0)
     * 
     * @param prefix 编码前缀
     * @param sourceData 当前编码
     * @param formatLength 编码长度
     * @return 生成的新编码
     */
    public static String generateCode(String prefix, int sourceData, int formatLength) {
        String code = "";
        if (StringUtils.isNotBlank(prefix)) {
            code = prefix;
        }

        // 默认编码6位
        if (formatLength == 0)
            formatLength = 6;

        if (sourceData > 0) {
            code += String.format("%0" + formatLength + "d", sourceData);
        }
        return code;

    }

    /**
     * 字符串拼接
     * 
     * @author duanxiong
     * @date 2018/10/31 下午4:34:55
     * @param separator 分隔符,为空表示不加
     * @param skipNull skipNull 是否跳过为空的字符
     * @param args 待拼接的数组
     * @return
     */
    public static String join(String separator, boolean skipNull, Object... args) {
        if (separator == null)
            separator = EMPTY;
        StringBuilder buf = new StringBuilder();
        for (Object arg : args) {
            if (arg != null) {
                if (buf.length() > 0)
                    buf.append(separator);
                buf.append(arg.toString());
            } else if (!skipNull) {
                if (buf.length() > 0)
                    buf.append(separator);
                buf.append(NULL);
            }
        }
        return buf.toString();
    }

    /**
     * 补充字符串以满足最小长度，如padEnd("1", 3, '0')//"100"
     * 
     * @author duanxiong
     * @date 2018/10/31 下午4:39:49
     * @param str
     * @param minLength
     * @param padChar
     * @return
     */
    public static String padEnd(String str, int minLength, char padChar) {
        if (str == null || str.length() >= minLength) {
            return str;
        }
        StringBuilder sb = new StringBuilder(minLength);
        sb.append(str);
        for (int i = str.length(); i < minLength; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    /**
     * 拼接字符串
     * 
     * @author duanxiong
     * @date 2018/10/31 下午4:36:45
     * @param args
     * @return
     */
    public static String str(Object... args) {
        return join(null, true, args);
    }

    /**
     * 判断是否含有中文
     * 
     * @author duanxiong
     * @date 2019年1月25日 上午10:38:20
     * @param str
     * @return
     */
    public static boolean isContainChinese(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return false;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(keyword);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否含有“-”
     * 
     * @author duanxiong
     * @date 2019年1月25日 上午11:55:01
     * @param keyword
     * @return
     */
    public static boolean isContainLine(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return false;
        }
        if (keyword.indexOf("-") > -1) {
            return true;
        }
        return false;
    }

    public static String filter(String input) {
        // 过滤特殊字符
        String regEx = "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(input);
        return m.replaceAll("").trim();
    }

    /**
     * 
     * @Description: 将double转成字符串 格式化为保留两位小数（可防止数字过大出现科学计数法问题）
     * @author chenxin
     * @date 2019/05/09
     */
    public static String doubleToString(Double input) {
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(input);
        return str;
    }

    public static String objectToString(Object input) {
        if (input == null) {
            return null;
        }

        return input.toString();
    }

    public static void main(String[] args) throws Exception {
        Integer orderAmount = Math.abs(2100000056);
        String orderAmountStr = StringUtils.doubleToString(orderAmount / 100.0);
        System.out.println(orderAmountStr);
    }

}
