package com.xghls.okhttp;

import android.content.Context;
import android.text.TextUtils;

import com.xghls.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpApi implements MyfApi {
    private static final String TAG = "OkHttpApi";
    // TODO: 2019/3/8 加载时间
    private final static int CONNECT_TIMEOUT = 30;
    private final static int WRITE_TIMEOUT = 30;
    private final static int READ_TIMEOUT = 30;
    private static final OkHttpApi INSTANCE = new OkHttpApi();
    /**
     * 用于开关okHttp debug信息
     */
    private final static boolean DEBUG = true;
    private static OkHttpClient sOkHttpClient;

    /**
     * 获取OKHttp实例
     *
     * @return
     */
    public static OkHttpApi getInstance() {
        return INSTANCE;
    }

    /**
     * 创建实例
     *
     * @param context
     */
    public static void init(Context context) {
        if (sOkHttpClient == null) {
            synchronized (OkHttpApi.class) {
                if (sOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
//                            .cache(new Cache(context.getCacheDir(), cacheSize));
                    if (DEBUG) {
                        builder.addInterceptor(new LoggingInterceptor());
                    }
                    sOkHttpClient = builder.build();
                }
            }
        }
    }

    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            LogUtil.i(TAG, String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            LogUtil.i(TAG, String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    private void asyncGet(String Url, Map<String, String> params,
                          StringCallback callback) {
        Set<String> keySet = params.keySet();
        GetBuilder getBuilder = OkHttpUtils.get().url(Url);
        getBuilder.addHeader("Connection", "close");
        for (String key : keySet) {
            try {
                if (TextUtils.isEmpty(params.get(key))) {
                    getBuilder.addParams(key, params.get(key));
                } else {
                    getBuilder.addParams(key, URLEncoder.encode(params.get(key), "UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        getBuilder.build().execute(callback);
    }
    @Override
    public void getVerifyImageRespones(String logonId, StringCallback callback, String tag) {
        Map<String, String> params = new HashMap<>();
        params.put("update", "6666");
        asyncGet(VERIFYIMAGE, params, callback);
    }
}