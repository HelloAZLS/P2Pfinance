package ysg.gdcp.cn.p2pfinance.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2017/3/12 12:17.
 *
 * @author ysg
 */

public class AppManager {
    private Stack<Activity> activityStack = new Stack<>();
    public  static  AppManager appManager = null;

    private AppManager() {}

    public  static AppManager getInstance(){
        if (appManager==null){
            appManager = new AppManager();
        }
        return appManager;
    }

    public  void addActivity(Activity activity){
        activityStack.add(activity);
    }

    public  void  removeActivity(Activity activity){
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity1 = activityStack.get(i);
            if (activity1.getClass().equals(activity.getClass())){
                activity1.finish();
                activityStack.remove(activity1);
                break;
            }
        }
    }

    public  void removeCurent(){
        Activity lastElement  =activityStack.lastElement();
        lastElement.finish();
        activityStack.remove(lastElement);
    }
    public  void  reomveAll(){
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }
    public  int  getSize(){
        return activityStack.size();
    }
}
