package cn.peterzhen.demo;

import cn.peterzhen.rxsoap.net.NetConfig;
import retrofit2.Retrofit;

/**
 * Soap使用相关的定义均在此，其他的网络请求功能可以移除
 * Created by zhaoyang on 2017/3/31.
 */

public class Api {

    private static ICommonService sCbswService;

    private static class ApiHolder{
        private static final Api instance = new Api();
    }

    public static Api getInstance() {
        return ApiHolder.instance;
    }

    private Api() {
    }

    public static void init() {
        Retrofit retrofit = NetConfig.getInstance()
                .getRetrofit(App.BASE_URL, true);
        sCbswService = retrofit.create(ICommonService.class);
    }


    public ICommonService getService() {
        return sCbswService;
    }
}
