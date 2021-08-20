package com.site.blog.my.core.util;

import java.security.MessageDigest;


public class MD5Util {
    //byte十进制整数数组转换成16进制字符串表示形式
    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }
    //byte十进制整数转换成16进制字符串表示
    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            //返回实现指定摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                //resultString.getBytes()使用平台默认字符集将字符串编码为字节数值e10adc3949ba59abbe56e057f20f883e
                //md.digest(resultString.getBytes())使用指定的 byte 数组对摘要进行最后更新，然后完成摘要计算,返回值为byte数组
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
