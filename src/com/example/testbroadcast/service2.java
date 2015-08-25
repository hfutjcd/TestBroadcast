package com.example.testbroadcast;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class service2 extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("liujun", "后台进程被创建。。。");
	    //服务启动广播接收器，使得广播接收器可以在程序退出后在后天继续执行，接收系统时间变更广播事件
	    myReceiver2 receiver=new myReceiver2();
	    registerReceiver(receiver,new IntentFilter(Intent.ACTION_TIME_TICK));
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("liujun", "后台进程。。。");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("liujun", "后台进程被销毁了。。。");
		super.onDestroy();
	}
	
	

}
