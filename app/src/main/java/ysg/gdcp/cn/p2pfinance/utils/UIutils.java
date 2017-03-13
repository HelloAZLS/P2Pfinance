package ysg.gdcp.cn.p2pfinance.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.view.View;

import ysg.gdcp.cn.p2pfinance.common.MyApplication;

/**
 * Created by Administrator on 2017/3/12 11:03.
 *
 * @author ysg
 */

public class UIutils {
    public  static Context getContext(){
        return MyApplication.context;
    }
    public  static Handler getHandler(){
        return  MyApplication.handler;
    }

    public  static int getColor(int colorId){
        return  getContext().getResources().getColor(colorId);
    }
    public  static View getXMLView(int layoutId){
        return  View.inflate(getContext(), layoutId,null);
    }
    public  static String[] getStringArr(int arrId){
        return getContext().getResources().getStringArray(arrId);
    }
    public  static int dp2px(int dp){
        float  density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp*(density+0.5));
    }
    public  static  int px2do(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px/density+0.5);
    }

    /**
     * runable 运行在主线程
     * @param runnable
     */
    public  static void runOnUIThread(Runnable runnable){
        if (isInMainThread()){
            runnable.run();
        }else {
            getHandler().post(runnable);
        }
    }

    public static boolean isInMainThread() {
        int myTid = Process.myTid();
        if (myTid==MyApplication.mainThreadId){
            return  true;
        }
        return false;
    }
}
