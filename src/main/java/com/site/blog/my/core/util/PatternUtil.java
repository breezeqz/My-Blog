package com.site.blog.my.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 */
public class PatternUtil {

    /**
     * 匹配邮箱正则
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);//Pattern.CASE_INSENSITIVE启用不区分大小写的匹配

    /**
     * 验证只包含中英文和数字的字符串
     *
     * @param keyword
     * @return
     */
    public static Boolean validKeyword(String keyword) {
        String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";//\ue400-\u9FA5表示中文，+表示出现1次或多次
        Pattern pattern = Pattern.compile(regex);//将给定的正则表达式编译为模式
        Matcher match = pattern.matcher(keyword);//创建一个匹配器，匹配给定的输入与此模式
        return match.matches();//尝试将整个区域与模式进行匹配
    }


    /**
     * 判断是否是邮箱
     *
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();//find只是部分匹配，只要能找到就返回true；mathes是要整个输入字符串与模式串都匹配
//        return  matcher.matches();
    }

    /**
     * 判断是否是网址
     *
     * @param urlString
     * @return
     */
    public static boolean isURL(String urlString) {
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(urlString).matches()) {
            return true;
        } else {
            return false;
        }
    }

}
