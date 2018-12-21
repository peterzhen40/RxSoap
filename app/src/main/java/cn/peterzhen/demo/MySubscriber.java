package cn.peterzhen.demo;

import android.widget.Toast;

import cn.peterzhen.rxsoap.net.ErrorUtils;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 当前类注释:自定义Subscriber
 * @author zhenyanjun
 * @date   2018/12/21 10:08
 */

public abstract class MySubscriber<T> extends ResourceSubscriber<T> {

    private boolean isNeedShowToast = true;

    public MySubscriber() {
    }

    public MySubscriber(boolean isNeedShowToast) {
        this.isNeedShowToast = isNeedShowToast;
    }

    @Override
    public void onError(Throwable t) {
        //全局错误处理
        Toast.makeText(App.sContext, ErrorUtils.getErrorMessage(t,ErrorUtils.SYSTEM.MINBAO), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {

    }

}
