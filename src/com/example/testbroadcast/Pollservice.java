package com.example.testbroadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Pollservice extends Service {

public static final String ACTION = "com.ryantang.service.PollingService";
	
	private Notification mNotification;
	private NotificationManager mManager;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		new PollingThread().start();
	}

	class PollingThread extends Thread {
		@Override
		public void run() {
			System.out.println("Polling...");
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("Service:onDestroy");
	}

}
