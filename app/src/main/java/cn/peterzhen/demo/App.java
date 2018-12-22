package cn.peterzhen.demo;

import android.app.Application;
import android.content.Context;

/**
 * 当前类注释:
 *
 * @author zhenyanjun
 * @date 2018/12/21 17:30
 */
public class App extends Application {

    public static Context sContext;
    public static String BASE_URL = "http://192.168.11.109:7001";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        Api.init();
    }
}
