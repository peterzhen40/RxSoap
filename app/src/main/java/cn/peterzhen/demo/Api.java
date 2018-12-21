package cn.peterzhen.demo;

import com.zyy.soap.AndroidCallFactory;
import com.zyy.soap.Soap;

/**
 * Soap使用相关的定义均在此，其他的网络请求功能可以移除
 * Created by zhaoyang on 2017/3/31.
 */

public class Api {

    public static ILoginService createLogin() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(20 * 1000L, TimeUnit.MILLISECONDS)
//                .readTimeout(20 * 10000L, TimeUnit.MILLISECONDS)
//                .build();
        Soap soap = new Soap.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .timeout(20*1000)
                .callFactory(AndroidCallFactory.create())
                .build();
        return soap.create(ILoginService.class);
    }

    public static ICommonService commonService() {
        Soap soap = new Soap.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .timeout(20*1000)
                .callFactory(AndroidCallFactory.create())
                .build();
        return soap.create(ICommonService.class);
    }
}