package com.example.testbroadcast;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class testservice extends Service {

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
	  Intent localIntent = new Intent();
	  localIntent.setClass(this, testservice.class); // 销毁时重新启动Service
	  this.startService(localIntent);
	 }
}
