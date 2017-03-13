package ysg.gdcp.cn.p2pfinance.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2017/3/12 10:58.
 *
 * @author ysg
 */

public class MyApplication extends Application {
    public static Context context = null;
    public static Handler handler = null;
    public static Thread mainThread = null;
    public static int mainThreadId = 0;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
        //CrashHnadler.getInstence().init(this);
    }
}
