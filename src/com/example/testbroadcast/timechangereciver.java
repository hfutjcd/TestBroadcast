package com.example.testbroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class timechangereciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "���յ�ʱ��仯�㲥��", Toast.LENGTH_SHORT).show();
	}

}
