package cn.peterzhen.rxsoap.net;

import com.google.gson.Gson;

/**
 * Created by wanglei on 2016/12/11.
 * 单例gson
 */

public class GsonProvider {
    private Gson gson;

    private static GsonProvider instance;

    private GsonProvider() {
        gson = new Gson();
    }

    public static GsonProvider getInstance() {
        if (instance == null) {
            synchronized (GsonProvider.class) {
                if (instance == null) {
                    instance = new GsonProvider();
                }
            }
        }
        return instance;
    }

    public Gson getGson() {
        return gson;
    }
}
