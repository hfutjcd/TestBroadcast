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
		Log.i("liujun", "��̨���̱�����������");
	    //���������㲥��������ʹ�ù㲥�����������ڳ����˳����ں������ִ�У�����ϵͳʱ�����㲥�¼�
	    myReceiver2 receiver=new myReceiver2();
	    registerReceiver(receiver,new IntentFilter(Intent.ACTION_TIME_TICK));
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("liujun", "��̨���̡�����");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("liujun", "��̨���̱������ˡ�����");
		super.onDestroy();
	}
	
	

}
