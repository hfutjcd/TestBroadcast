package com.example.testbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class myReceiver extends BroadcastReceiver {

	 @Override
	 public void onReceive(Context context, Intent intent)
	 {
		 Toast.makeText(context, "接收到广播！"+intent.getAction(), Toast.LENGTH_SHORT).show();
		 System.out.println( "接收到广播！"+intent.getAction());
//	  context.startService(new Intent(context, testservice.class));
	 } 
	 
}
