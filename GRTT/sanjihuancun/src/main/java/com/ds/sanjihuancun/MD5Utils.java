package com.ds.sanjihuancun;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 *
 */
public class MD5Utils {
    /**
     * MD5加密函数
     *
     * @param password
     * @return
     */
    public static String encode(String password) {

        try {
            //获得MD5算法对象
            MessageDigest digester = MessageDigest.getInstance("MD5");
            //对字符串加密，返回字节数组
            byte[] bs = digester.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            for (byte b : bs) {
                int i = (b & 0xff);//获取字节的低八位有效值
                String hexString = Integer.toHexString(i);//将整数转化为16进制数
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;//如果是一位的话，改成2位的
                }
                buffer.append(hexString);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            // 没有输入的算法异常
            e.printStackTrace();
        }
        return password;

    }
}
