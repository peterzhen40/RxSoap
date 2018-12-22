package cn.peterzhen.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import cn.peterzhen.R;
import cn.peterzhen.rxsoap.net.NetConfig;
import cn.peterzhen.rxsoap.net.NetProvider;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static cn.peterzhen.demo.App.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        final EditText etAddress = findViewById(R.id.et_address);
        final EditText etUsername = findViewById(R.id.et_username);
        final EditText etPassword = findViewById(R.id.et_password);
        Button button = findViewById(R.id.button);
        etAddress.setText(BASE_URL);
        etUsername.setText("test");
        etPassword.setText("88888");

        initNet();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etAddress.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (!TextUtils.isEmpty(address) && isValidUrl(address)) {
                    App.BASE_URL = address;
                    Api.init();
                    if ( !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                        Api.getInstance().getService().login(username, password, "1")
                                .subscribe(new Subscriber<UserInfo>() {
                                    @Override
                                    public void onSubscribe(Subscription s) {
                                        Log.d(TAG, "onSubscribe: ");
                                        s.request(Long.MAX_VALUE);
                                    }

                                    @Override
                                    public void onNext(UserInfo userInfo) {
                                        textView.setText(userInfo.toString());
                                        Log.d(TAG, "onNext: " + userInfo.toString());
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        textView.setText(t.getMessage());
                                        Log.d(TAG, "onError: ", t);
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d(TAG, "onComplete: ");
                                    }
                                });
                    }

                }

            }
        });
    }

    private void initNet() {
        NetConfig.registerProvider(new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                //配置拦截器
                Interceptor[] interceptors = new Interceptor[1];
                interceptors[0] = new RequestInterceptor();
                return interceptors;
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {
                //配置https
                //builder.sslSocketFactory(SSLHelper.getSSLSocketFactory(AppKit.getContext()));
            }

            @Override
            public CookieJar configCookie() {
                //配置cookie
                //CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(AppKit.getContext()));
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                //配置连接超时
                return 20 * 1000L;
            }

            @Override
            public long configReadTimeoutMills() {
                //配置读取超时
                return 20 * 1000L;
            }

        });
    }

    public static boolean isValidUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        if (Patterns.WEB_URL.matcher(url).matches()) {
            try {
                new Retrofit.Builder()
                        .baseUrl(url)
                        .build();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
