package com.xghls.util;


import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 正则校验
 */
public class PatternUtil {

    private PatternUtil() {

    }

    /**
     * 验证手机号码和固话
     *
     * @param phoneNumber 手机号码
     * @return boolean
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^(0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8})|(400|800)([0-9\\\\-]{7,10})|(([0-9]{4}|[0-9]{3})(-| )?)?([0-9]{7,8})((-| |转)*([0-9]{1,4}))?$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


    /**
     * 验证密码格式
     *
     * @param password 密码
     * @return boolean
     */
    public static boolean checkLoginPassword(String password) {
        return password.length() > 5 && password.length() < 21;
    }

    /**
     * 验证注册密码格式
     *
     * @param password 密码
     * @return boolean
     */
    public static boolean checkRegisterPassword(String password) {
//        Pattern pattern = Pattern.compile("^[0-9A-Za-z.@\\-_]{8,20}$");
        Pattern pattern = Pattern.compile("^(?![\\d]+$)(?![a-zA-Z]+$)(?![.@\\\\-_]+$)[\\da-zA-Z.@\\\\-_]{8,20}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * 验证注册密码格式两种以上字符
     *
     * @param password 密码
     * @return boolean
     */
    public static boolean checkRegisterPasswordStyle(String password) {
//        Pattern pattern = Pattern.compile("^[0-9A-Za-z.@\\-_]{8,20}$");
        Pattern pattern = Pattern.compile("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{8,20}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    /**
     * 验证金额格式
     *
     * @param money 金额
     * @return boolean
     */
    public static boolean checkMoneyNumber(String money) {
        Pattern pattern = Pattern.compile("^(([1-9]\\d*)|([0]))(\\.(\\d){1,2})?$");
        Matcher matcher = pattern.matcher(money);
        return matcher.matches();
    }


    /**
     * 验证身份证号格式
     *
     * @param identityNumber 身份证号
     * @return boolean
     */
    public static boolean checkIdentityNumber(String identityNumber) {
        Pattern pattern = Pattern.compile("^[1-9][0-9]{14}$|^[1-9][0-9]{16}([0-9]|[xX])$");
        Matcher matcher = pattern.matcher(identityNumber);
        return matcher.matches();
    }

    /**
     * 验证真实姓名格式 2-10个汉字
     *
     * @param identityName 真实姓名
     * @return boolean
     */
    public static boolean checkIndentityName(String identityName) {
        Pattern pattern = Pattern.compile("^[\\u4E00-\\u9FA5]{2,10}$");
        Matcher matcher = pattern.matcher(identityName);
        return matcher.matches();
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpeChat(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}
