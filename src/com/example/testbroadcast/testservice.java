package com.example.testbroadcast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class testservice extends Service {
	private static final Class[] mStartForegroundSignature = new Class[] {
        int.class, Notification.class };
private static final Class[] mStopForegroundSignature = new Class[] { boolean.class };
private NotificationManager mNM;
private Method mStartForeground;
private Method mStopForeground;
private Object[] mStartForegroundArgs = new Object[2];
private Object[] mStopForegroundArgs = new Object[1];
	
@Override
public void onCreate() {
    super.onCreate();
    mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    try {
        mStartForeground = testservice.class.getMethod("startForeground",
                mStartForegroundSignature);
        mStopForeground = testservice.class.getMethod("stopForeground",
                mStopForegroundSignature);
    } catch (NoSuchMethodException e) {
        mStartForeground = mStopForeground = null;
    }
    // 我们并不需要为 notification.flags 设置 FLAG_ONGOING_EVENT，因为
    // 前台服务的 notification.flags 总是默认包含了那个标志位
    Notification notification =new Notification();
    // 注意使用  startForeground ，id 为 0 将不会显示 notification
    startForegroundCompat(1, notification);
}


	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}



	 @Override
	 public int onStartCommand(Intent intent, int flags, int startId)
	 {
	  flags = START_STICKY;
	  return super.onStartCommand(intent, flags, startId);
	  // return START_REDELIVER_INTENT;
	 }

	@Override
	public void onStart(Intent intent, int startId)
	{
	// 再次动态注册广播
		Toast.makeText(getBaseContext(), "testservice 启动了！", Toast.LENGTH_SHORT).show();
	IntentFilter localIntentFilter = new IntentFilter("android.intent.action.USER_PRESENT");
	localIntentFilter.setPriority(Integer.MAX_VALUE);// 整形最大值
	myReceiver searchReceiver = new myReceiver();
	registerReceiver(searchReceiver, localIntentFilter);
	super.onStart(intent, startId);
	}

	 public void onDestroy()
	 {
		 stopForegroundCompat(1);
	  Intent localIntent = new Intent();
	  localIntent.setClass(this, testservice.class); // 销毁时重新启动Service
	  this.startService(localIntent);
	 }
	 
	 private void startForegroundCompat(int id, Notification n) {
	        if (mStartForeground != null) {
	            mStartForegroundArgs[0] = id;
	            mStartForegroundArgs[1] = n;
	            try {
	                mStartForeground.invoke(this, mStartForegroundArgs);
	            } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            }
	            return;
	        }
	        mNM.notify(id, n);
	    }

	    // 以兼容性方式停止前台服务
	    private void stopForegroundCompat(int id) {
	        if (mStopForeground != null) {
	            mStopForegroundArgs[0] = Boolean.TRUE;
	            try {
	                mStopForeground.invoke(this, mStopForegroundArgs);
	            } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            }
	            return;
	        }
	        //   在 setForeground 之前调用 cancel，因为我们有可能在取消前台服务之后
	        //   的那一瞬间被kill掉。这个时候 notification 便永远不会从通知一栏移除
	        mNM.cancel(id);
	    }

}
