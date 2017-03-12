package ysg.gdcp.cn.p2pfinance.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/12 14:55.
 *
 * @author ysg
 */

public class CrashHnadler implements Thread.UncaughtExceptionHandler {

    public static CrashHnadler crashHnadler = null;
    private Context context;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private CrashHnadler() {
    }

    public static CrashHnadler getInstence() {
        if (crashHnadler == null) {
            crashHnadler = new CrashHnadler();
        }
        return crashHnadler;
    }

    public void init(Context context) {
        this.context = context;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        Log.e("这个是被我捕获的异常", ex.getMessage());
        if (isHandler(ex)) {
            handlerException(thread, ex);
        } else {
            defaultUncaughtExceptionHandler.uncaughtException(thread, ex);
        }
    }

    public boolean isHandler(Throwable ex) {
        if (ex == null) {
            return false;
        } else {
            return true;
        }
    }

    private void handlerException(Thread thread, Throwable ex) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "抱歉啊！！！！系统出现未知异常，即将退出", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collectException(ex);
        try {
            thread.sleep(2000);
            AppManager.getInstance().reomveAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收集异常信息
     *
     * @param ex
     */
    private void collectException(Throwable ex) {
        final String deviceInfo = Build.DEVICE + Build.VERSION.SDK_INT + Build.MODEL + Build.PRODUCT;
        final String errorInfo = ex.getMessage();
        new Thread(){
            @Override
            public void run() {
                Log.e("自定义异常抓捕","手机信息"+deviceInfo+"异常信息"+errorInfo);
            }
        }.start();
    }


}
