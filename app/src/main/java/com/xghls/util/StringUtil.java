package com.xghls.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

/**
 * 时间数字格式化
 */
public class StringUtil {
    private StringUtil() {
    }

    /**
     * 金额格式化
     *
     * @param count 金额
     * @return
     */
    public static String getStyleCount(String count) {
        String result;
        //保留两位小数,数字每隔三位加一个逗号
        try {
            BigDecimal bigDecimal = new BigDecimal(count);
            DecimalFormat df = new DecimalFormat("###,###.00");
            result = df.format(bigDecimal);
            if (result.startsWith(".")) {
                result = "0" + result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        }
        return result;
    }

    /**
     * 金额去掉“,”
     *
     * @param count 金额
     * @return 去掉“,”后的金额
     */
    public static String removeStyleCount(String count) {
        String formatString = "";
        if (count != null && count.length() >= 1) {
            formatString = count.replaceAll(",", "");
        }

        return formatString;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            return getDistanceTime(time1, time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "时" + min + "分" + sec + "秒";
    }

    /**
     * 两个时间相差距离多少个月
     *
     * @param d1 时间参数 1 格式：19900101
     * @param d2 时间参数 2 格式：20090101
     * @return String 返回值为：6
     */
    public static int getMonthDiff(String d1, String d2) throws ParseException {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = sdf.parse(d1);
        Date date2 = sdf.parse(d2);

        c1.setTime(date1);
        c2.setTime(date2);

        if (c2.getTimeInMillis() < c1.getTimeInMillis())
            return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        int yearInterval = year2 - year1;
        if (month2 < month1 || month1 == month2 && day2 < day1)
            yearInterval--;
        int monthInterval = (month2 + 12) - month1;
        if (day2 > day1)
            monthInterval++;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    /**
     * 对传入时间进行格式化
     *
     * @param str
     * @return
     */
    public static long getTime(String str) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date one;
        try {
            one = df.parse(str);
            return one.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 对传入时间毫秒值格式化
     *
     * @param time
     * @return
     */
    public static String getTime(long time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date one;
        one = new Date(time);
        return df.format(one);
    }

    /**
     * 传入两个毫秒值，返回相隔多少天多少小时多少分多少秒
     *
     * @param time1
     * @param time2
     * @return
     */
    public static String getDistanceTime(long time1, long time2) {
        return getDistanceDay(time1, time2) + "天" + getDistanceHour(time1, time2)
                + "时" + getDistanceMin(time1, time2) + "分" + getDistanceSec(time1, time2) + "秒";
    }


    public static long getDayTime(long time1, long time2) {
        long day;
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        return day;
    }

    public static long getHourTime(long time1, long time2) {
        long hour;
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        hour = (diff / (60 * 60 * 1000) - getDayTime(time1, time2) * 24);
        return hour;
    }

    public static long getMinTime(long time1, long time2) {
        long min;
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        min = ((diff / (60 * 1000)) - getDayTime(time1, time2) * 24 * 60 - getHourTime(time1, time2) * 60);
        return min;
    }

    public static long getSecTime(long time1, long time2) {
        long sec;
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        sec = (diff / 1000 - getDayTime(time1, time2) * 24 * 60 * 60 - getHourTime(time1, time2) * 60 * 60 - getMinTime(time1, time2) * 60);
        return sec;
    }

    public static String getDistanceDay(long time1, long time2) {
        String dayStr = String.valueOf(getDayTime(time1, time2));
        if (dayStr.length() == 1) {
            dayStr = "0" + dayStr;
        }
        return dayStr;
    }


    public static String getDistanceDay(String str1, String str2) {
        return getDistanceDay(getTime(str1), getTime(str2));
    }

    public static String getDistanceHour(long time1, long time2) {
        String hourStr = String.valueOf(getHourTime(time1, time2));
        if (hourStr.length() == 1) {
            hourStr = "0" + hourStr;
        }
        return hourStr;
    }

    public static String getDistanceHour(String str1, String str2) {
        return getDistanceHour(getTime(str1), getTime(str2));
    }

    public static String getDistanceMin(long time1, long time2) {
        String minStr = String.valueOf(getMinTime(time1, time2));
        if (minStr.length() == 1) {
            minStr = "0" + minStr;
        }
        return minStr;
    }

    public static String getDistanceMin(String str1, String str2) {
        return getDistanceMin(getTime(str1), getTime(str2));
    }

    public static String getDistanceSec(long time1, long time2) {
        String secStr = String.valueOf(getSecTime(time1, time2));
        if (secStr.length() == 1) {
            secStr = "0" + secStr;
        }
        return secStr;
    }

    public static String getDistanceSec(String str1, String str2) {
        return getDistanceSec(getTime(str1), getTime(str2));
    }

    /**
     * 加密卡号
     *
     * @param name
     * @return
     */
    public static String nameEncrypt(String name) {
        if (TextUtils.isEmpty(name) || name.length() < 2) {
            return name;
        }
        if (name.length() == 2) {
            return "*" + name.substring(1);
        }
        if (name.length() == 3) {
            return name.substring(0, 1) + "*" + name.substring(name.length() - 1);
        }
        if (name.length() == 4) {
            return name.substring(0, 1) + "**" + name.substring(name.length() - 1);
        }
        return name.substring(0, 1) + "***" + name.substring(name.length() - 1);
    }

    /**
     * 加密卡号
     *
     * @param cardNo
     * @return
     */
    public static String cardEncrypt(String cardNo) {
        if (TextUtils.isEmpty(cardNo) || cardNo.length() < 4) {
            return cardNo;
        }
        StringBuffer sb = new StringBuffer();
        if (!TextUtils.isEmpty(cardNo)) {
            sb.append(cardNo.substring(0, 4));
            sb.append(" **** **** ");
            sb.append(cardNo.substring(cardNo.length() - 4, cardNo.length()));
            return sb.toString();
        }
        return "";
    }

    /**
     * 加密卡号
     *
     * @param cardNo
     * @return
     */
    public static String cardEncrypt2(String cardNo) {
        if (cardNo.length() < 4) {
            return cardNo;
        }
        StringBuffer sb = new StringBuffer();
        if (!TextUtils.isEmpty(cardNo)) {
            sb.append(cardNo.substring(0, 4));
            sb.append(" **** ");
            sb.append(cardNo.substring(cardNo.length() - 4, cardNo.length()));
            return sb.toString();
        }
        return "";
    }

    /**
     * 加密卡号 只保留最后4位
     *
     * @param cardNo
     * @return
     */
    public static String cardEncryptTo2(String cardNo) {
        if (!TextUtils.isEmpty(cardNo) && cardNo.length() < 4) {
            return cardNo;
        }
        StringBuffer sb = new StringBuffer();
        if (!TextUtils.isEmpty(cardNo)) {
            sb.append(cardNo.substring(cardNo.length() - 4, cardNo.length()));
            return sb.toString();
        }
        return "";
    }

    /**
     * 加密卡号 保留最后4位
     *
     * @param cardNo
     * @return
     */
    public static String cardEncryptTo4(String cardNo) {
        if (TextUtils.isEmpty(cardNo) || cardNo.length() < 4) {
            return cardNo;
        }
        StringBuffer sb = new StringBuffer();
        if (!TextUtils.isEmpty(cardNo)) {
            sb.append("**");
            sb.append(cardNo.substring(cardNo.length() - 4, cardNo.length()));
            return sb.toString();
        }
        return "";
    }

    /**
     * @param insd  传入年月日格式 yyyy-MM-dd HH:mm:ss
     * @param outsd 输出年月日格式 yyyy年MM月dd日 HH:mm:ss
     * @param str   传入的年月日
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatDateStr(String insd, String outsd, String str) {
        String resultStr = "——";
        SimpleDateFormat insdf = new SimpleDateFormat(insd);
        SimpleDateFormat outsdf = new SimpleDateFormat(outsd);
        try {
            Date sdDate = insdf.parse(str);
            resultStr = outsdf.format(sdDate);
        } catch (ParseException e) {
            // format error
            e.printStackTrace();
        }
        return resultStr;
    }

    /**
     * 日期转换 000000---0000-00-00
     *
     * @param data
     * @return
     */
//    public static String dataForData(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 8) {
//            return data;
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(0, 4));
//        sb.append("-");
//        sb.append(data.substring(4, 6));
//        sb.append("-");
//        sb.append(data.substring(6, 8));
//        return sb.toString();
//    }
    /**
     * 日期转换 000000---0000/00/00
     *
     * @param data
     * @return
     */
//    public static String dataForData9(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 8) {
//            return data;
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(0, 4));
//        sb.append("/");
//        sb.append(data.substring(4, 6));
//        sb.append("/");
//        sb.append(data.substring(6, 8));
//        return sb.toString();
//    }

    /**
     * 日期转换 20180124 -- 2018年01月24日
     *
     * @param data
     * @return
     */
//    public static String dataForData10(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 8) {
//            return data;
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(0, 4));
//        sb.append("年");
//        sb.append(data.substring(4, 6));
//        sb.append("月");
//        sb.append(data.substring(6, 8));
//        sb.append("日");
//        return sb.toString();
//    }

    /**
     * 日期转换 000000--0000-00-00
     *
     * @param data
     * @return
     */
//    public static String dataForData5(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 8) {
//            return data;
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(0, 4));
//        sb.append("/");
//        sb.append(data.substring(4, 6));
//        sb.append("/");
//        sb.append(data.substring(6, 8));
//        return sb.toString();
//    }

    /**
     * 日期转换 00000000---00-00
     *
     * @param data
     * @return
     */
//    public static String dataForData2(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 8) {
//            return data;
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(4, 6));
//        sb.append("-");
//        sb.append(data.substring(6, 8));
//        return sb.toString();
//    }

    /**
     * 日期转换 000000---00月-00日
     *
     * @param data
     * @return
     */
//    public static String dataForData3(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 6) {
//            return "——";
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(4, 6));
//        sb.append("月");
//        sb.append(data.substring(6, data.length()));
//        sb.append("日");
//        return sb.toString();
//    }

    /**
     * 日期转换 20171108144449---11月-08日
     *
     * @param data
     * @return
     */
//    public static String dataForData7(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 8) {
//            return "——";
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(4, 6));
//        sb.append("月");
//        sb.append(data.substring(6, 8));
//        sb.append("日");
//        return sb.toString();
//    }

    /**
     * 日期转换 2017 1108 144449---14:44:49
     *
     * @param data
     * @return
     */
//    public static String dataForData8(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 14) {
//            return "——";
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(8, 10));
//        sb.append(":");
//        sb.append(data.substring(10, 12));
//        sb.append(":");
//        sb.append(data.substring(12, data.length()));
//        return sb.toString();
//    }

    /**
     * 日期转换 000000--- 00-00
     *
     * @param data
     * @return
     */
//    public static String dataForData4(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return "";
//        }
//        if (data.length() < 6) {
//            return "——";
//        }
//        StringBuffer sb = new StringBuffer();
//        sb.append(data.substring(0, 4));
//        sb.append("-");
//        sb.append(data.substring(4, 6));
//        return sb.toString();
//    }

    /**
     * 输入日期距离今天的天数
     *
     * @param data
     * @return
     */
    public static String dataForDay(String data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date d1 = null;
        long diffDays = 0L;
        try {
            d1 = format.parse(data);
            Date d2 = format.parse(format.format(new Date()));
            //毫秒ms
            long diff = d1.getTime() - d2.getTime();
            if (diff < 0) {
                diff = 0;
            }
            diffDays = diff / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(diffDays + "天");

    }

    /**
     * 金额千分位转换
     *
     * @param money
     * @return
     */
    public static String moneyForMoney(String money) {
        if (TextUtils.isEmpty(money)) {
            return "";
        }
        if (money.contains(",")) {
            return money;
        }
        BigDecimal b = new BigDecimal(money);
        DecimalFormat d1 = new DecimalFormat("#,##0.00##;#");
        return d1.format(b);
    }

    /**
     * 卡号加密1234567....8910 ---- 1234567....**** 8910
     */
    public static String cardEncrypt4(String rz) {
        StringBuffer s = new StringBuffer();
        s.append(rz.substring(0, rz.length() - 8));
        s.append("****");
        s.append(rz.substring(rz.length() - 4, rz.length()));
        return s.toString();
    }

    //手机号加密
    public static String phoneEncrypt(String rz) {
        StringBuffer s = new StringBuffer();
        s.append(rz.substring(0, 3));
        s.append("****");
        s.append(rz.substring(rz.length() - 4, rz.length()));
        return s.toString();
    }

    public static String getMac(Context context) {
        String strMac = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            LogUtil.e("=====", "6.0以下");
            strMac = getLocalMacAddressFromWifiInfo(context);
            return strMac;
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LogUtil.e("=====", "6.0以上7.0以下");
            strMac = getMacAddress(context);
            return strMac;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LogUtil.e("=====", "7.0以上");
            if (!TextUtils.isEmpty(getMacAddress())) {
                LogUtil.e("=====", "7.0以上1");
                strMac = getMacAddress();
                return strMac;
            } else if (!TextUtils.isEmpty(getMachineHardwareAddress())) {
                LogUtil.e("=====", "7.0以上2");
                strMac = getMachineHardwareAddress();
                return strMac;
            } else {
                LogUtil.e("=====", "7.0以上3");
                strMac = getLocalMacAddressFromBusybox();
                return strMac;
            }
        }

        return "02:00:00:00:00:00";
    }

    /**
     * 根据wifi信息获取本地mac
     *
     * @param context
     * @return
     */
    public static String getLocalMacAddressFromWifiInfo(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo winfo = wifi.getConnectionInfo();
        String mac = winfo.getMacAddress();
        return mac;
    }

    /**
     * android 6.0及以上、7.0以下 获取mac地址
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {

        // 如果是6.0以下，直接通过wifimanager获取
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            String macAddress0 = getMacAddress0(context);
            if (!TextUtils.isEmpty(macAddress0)) {
                return macAddress0;
            }
        }
        String str = "";
        String macSerial = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            LogUtil.e("----->" + "NetInfoManager", "getMacAddress:" + ex.toString());
        }
        if (macSerial == null || "".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("----->" + "NetInfoManager",
                        "getMacAddress:" + e.toString());
            }

        }
        return macSerial;
    }

    private static String getMacAddress0(Context context) {
        if (isAccessWifiStateAuthorized(context)) {
            WifiManager wifiMgr = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = null;
            try {
                wifiInfo = wifiMgr.getConnectionInfo();
                return wifiInfo.getMacAddress();
            } catch (Exception e) {
                LogUtil.e("----->" + "NetInfoManager",
                        "getMacAddress0:" + e.toString());
            }

        }
        return "";

    }

    /**
     * Check whether accessing wifi state is permitted
     *
     * @param context
     * @return
     */
    private static boolean isAccessWifiStateAuthorized(Context context) {
        if (PackageManager.PERMISSION_GRANTED == context
                .checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE")) {
            LogUtil.e("----->" + "NetInfoManager", "isAccessWifiStateAuthorized:"
                    + "access wifi state is enabled");
            return true;
        } else
            return false;
    }

    private static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    private static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }

    /**
     * 根据IP地址获取MAC地址
     *
     * @return
     */
    public static String getMacAddress() {
        String strMacAddr = null;
        try {
            // 获得IpD地址
            InetAddress ip = getLocalInetAddress();
            byte[] b = NetworkInterface.getByInetAddress(ip)
                    .getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {
        }
        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return
     */
    private static InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            // 列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface
                    .getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {// 是否还有元素
                NetworkInterface ni = (NetworkInterface) en_netInterface
                        .nextElement();// 得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();// 得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }

                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {

            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    private static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * android 7.0及以上 （2）扫描各个网络接口获取mac地址
     *
     */
    /**
     * 获取设备HardwareAddress地址
     *
     * @return
     */
    public static String getMachineHardwareAddress() {
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        String hardWareAddress = null;
        NetworkInterface iF = null;
        if (interfaces == null) {
            return null;
        }
        while (interfaces.hasMoreElements()) {
            iF = interfaces.nextElement();
            try {
                hardWareAddress = bytesToString(iF.getHardwareAddress());
                if (hardWareAddress != null)
                    break;
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        return hardWareAddress;
    }

    /***
     * byte转为String
     *
     * @param bytes
     * @return
     */
    private static String bytesToString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        StringBuilder buf = new StringBuilder();
        for (byte b : bytes) {
            buf.append(String.format("%02X:", b));
        }
        if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }
    /**
     * android 7.0及以上 （3）通过busybox获取本地存储的mac地址
     *
     */

    /**
     * 根据busybox获取本地Mac
     *
     * @return
     */
    public static String getLocalMacAddressFromBusybox() {
        String result = "";
        String Mac = "";
        result = callCmd("busybox ifconfig", "HWaddr");
        // 如果返回的result == null，则说明网络不可取
        if (result == null) {
            return "网络异常";
        }
        // 对该行数据进行解析
        // 例如：eth0 Link encap:Ethernet HWaddr 00:16:E8:3E:DF:67
        if (result.length() > 0 && result.contains("HWaddr") == true) {
            Mac = result.substring(result.indexOf("HWaddr") + 6,
                    result.length() - 1);
            result = Mac;
        }
        return result;
    }

    private static String callCmd(String cmd, String filter) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);

            while ((line = br.readLine()) != null
                    && line.contains(filter) == false) {
                result += line;
            }

            result = line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
