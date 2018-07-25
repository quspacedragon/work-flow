package com.quspacedragon.workflow.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
    /**
     * 匹配手机号码
     *
     * @param phone
     * @return
     */
    public static boolean isMobile(String phone) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(phone);
        return m.find();
    }

    /**
     * 验证密码格式是否正确 6-30位大小写字母可加 _-
     *
     * @param phone
     * @return
     */
    public static boolean isPwd(String pwd) {
        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{6,16}$");
        Matcher m = p.matcher(pwd);
        return m.find();
    }

    /**
     * 是否支付密码
     *
     * @param payPwd
     * @return
     */
    public static boolean isPayPwd(String payPwd) {
        Pattern p = Pattern.compile("^[0-9]{6}$");
        Matcher m = p.matcher(payPwd);
        return m.find();
    }


    /**
     * 昵称规则
     *
     * @param phone
     * @return
     */
    public static boolean isName(String name) {
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5A-Za-z0-9_-]{2,20}$");
        Matcher m = p.matcher(name);
        return m.find();
    }

    public static boolean isImg(String fileName) {
        Pattern p = Pattern
                            .compile(".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$");
        Matcher m = p.matcher(fileName);
        return m.find();

    }

    /**
     * 正则替换所有特殊字符
     *
     * @param orgStr
     * @return
     */
    public static String replaceSpecStr(String orgStr) {
        if (null != orgStr && !"".equals(orgStr.trim())) {
            String regEx = "[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(orgStr);
            return m.replaceAll("");
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(isPayPwd("123456"));

    }
}
