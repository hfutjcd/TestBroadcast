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
			System.out.println("���õ�ʱ��Ϊ��"+str+"  ����ʱ���ǣ�"+ simpleDateFormat.format(new Date(time)));
		}
		Toast.makeText(context, "�յ�������Ϣ", 0).show();

	}

}
