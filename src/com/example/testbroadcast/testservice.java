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
    // ���ǲ�����ҪΪ notification.flags ���� FLAG_ONGOING_EVENT����Ϊ
    // ǰ̨����� notification.flags ����Ĭ�ϰ������Ǹ���־λ
    Notification notification =new Notification();
    // ע��ʹ��  startForeground ��id Ϊ 0 ��������ʾ notification
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
	// �ٴζ�̬ע��㲥
		Toast.makeText(getBaseContext(), "testservice �����ˣ�", Toast.LENGTH_SHORT).show();
	IntentFilter localIntentFilter = new IntentFilter("android.intent.action.USER_PRESENT");
	localIntentFilter.setPriority(Integer.MAX_VALUE);// �������ֵ
	myReceiver searchReceiver = new myReceiver();
	registerReceiver(searchReceiver, localIntentFilter);
	super.onStart(intent, startId);
	}

	 public void onDestroy()
	 {
		 stopForegroundCompat(1);
	  Intent localIntent = new Intent();
	  localIntent.setClass(this, testservice.class); // ����ʱ��������Service
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

	    // �Լ����Է�ʽֹͣǰ̨����
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
	        //   �� setForeground ֮ǰ���� cancel����Ϊ�����п�����ȡ��ǰ̨����֮��
	        //   ����һ˲�䱻kill�������ʱ�� notification ����Զ�����֪ͨһ���Ƴ�
	        mNM.cancel(id);
	    }

}
