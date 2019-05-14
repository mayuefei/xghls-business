package com.xghls.okhttp;

import com.zhy.http.okhttp.callback.StringCallback;

public interface MyfApi {

    public String COMMON_IP = "http://179.169.13.113"; // uat3
    public String COM_PORT = "7007";// uat3
//    public String COMMON_IP = "http://59.48.96.118"; // 测试地址
//    public String COM_PORT = "7007";// 测试地址
    public String ADD_STRING = "mobileServer/"; // 附加串
    public String COMMON_URL_START = COMMON_IP + ":" + COM_PORT + "/" + ADD_STRING; // 拼接地址
    /**
     * 刷新EMP_SID 登录之前调用
     */
    public String VERIFYIMAGE = COMMON_URL_START + "VerifyImage";

    void getVerifyImageRespones(String logonId, StringCallback callback, String tag);
}

