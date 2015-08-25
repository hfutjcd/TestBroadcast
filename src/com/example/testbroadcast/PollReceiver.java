package com.example.testbroadcast;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class PollReceiver extends BroadcastReceiver {

	private static final int PERIOD = 60000; // 1 minutes

	@Override
	public void onReceive(Context ctxt, Intent i) {
		if (i.getAction().equals("great")) {
			long time=System.currentTimeMillis();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			System.out.println("接收到定时一分钟闹钟现在时间是："+ simpleDateFormat.format(new Date(time)));
			scheduleAlarms(ctxt);
			// Do your work
		}
	}

	static void scheduleAlarms(Context ctxt) {
		       AlarmManager mgr = (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);
		       Intent i = new Intent(ctxt, PollReceiver.class);
		       i.setAction("great");
		       PendingIntent pi = PendingIntent.getBroadcast(ctxt, 0, i, 0);
		       mgr.cancel(pi);
		       
		      
		       long time=System.currentTimeMillis();
//		       mgr.set(0,time/60000*60000+PERIOD, pi);
		       long triggerAtTime = SystemClock.elapsedRealtime() + 60 * 1000;
		       mgr.set(AlarmManager.ELAPSED_REALTIME, triggerAtTime/60000*60000, pi);
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				System.out.println("注册了一个一分钟闹钟现在时间是："+ simpleDateFormat.format(new Date(time)));

		   }
}
