package cn.peterzhen.demo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 当前类注释:请求拦截器
 * Author: zhenyanjun
 * Date  : 2017/9/26 09:20
 */

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Content-Type", "text/xml;charset=UTF-8")
                .method(original.method(), original.body())
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}
