package com.example.testbroadcast;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class alarmreciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals("com.alarm.TIME_ALARM")){
			String str=intent.getStringExtra("time");
			long time=System.currentTimeMillis();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			System.out.println("设置的时间为："+str+"  现在时间是："+ simpleDateFormat.format(new Date(time)));
		}
		Toast.makeText(context, "收到闹铃消息", 0).show();

	}

}
