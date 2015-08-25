package com.example.testbroadcast;

import java.io.DataOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	private Button isrootbtn;
	private Button getrootbtn;
	private Button startbroadcast;
	private Button registeralarm;
	private Button testalarm;
	private Button onemeniteButton;
	private Button alarmsButton;
	String filter = Intent.ACTION_TIME_CHANGED;// "android.provider.Telephony.SMS_RECEIVED"
    private IntentFilter intentFilter = new IntentFilter(filter );
    private timechangereciver receiver=new timechangereciver();
	private IntentFilter alarmFilter=new IntentFilter("com.alarm.TIME_ALARM");
	private alarmreciver alarmver=new alarmreciver();
	private static final int PERIOD = 60000; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isrootbtn=(Button) findViewById(R.id.button1);
        getrootbtn=(Button) findViewById(R.id.button2);
        startbroadcast=(Button) findViewById(R.id.button3);
        registeralarm=(Button) findViewById(R.id.button4);
        testalarm=(Button) findViewById(R.id.button5);
        onemeniteButton=(Button) findViewById(R.id.button6);
//        alarmsButton=(Button) findViewById(R.id.button7);
//        alarmsButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent("com.testalarm.ALARM_ALET");
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
//				intent.putExtra("time","测试alarm的广播！");
//				
//				if (android.os.Build.VERSION.SDK_INT >= 12) {
//		            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);// 3.1以后的版本需要设置Intent.FLAG_INCLUDE_STOPPED_PACKAGES
//		        }
//				sendBroadcast(intent);
//				System.out.println("发送完成");
//			}
//		});
        
        
//        PullUntil.startPollingService(this, 65, Pollservice.class, Pollservice.ACTION);
        
        onemeniteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			       Intent i = new Intent(MainActivity.this, PollReceiver.class);
			       i.setAction("great");
			       PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 23, i, 0);
			       mgr.cancel(pi);
			       long time=System.currentTimeMillis();
			       long triggerAtTime = SystemClock.elapsedRealtime() + 60 * 1000;
//			       mgr.set(0,time/60000*60000+PERIOD, pi);
			       mgr.set(AlarmManager.ELAPSED_REALTIME, triggerAtTime/60000*60000, pi);
					SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
					System.out.println("注册了一个一分钟闹钟现在时间是："+ simpleDateFormat.format(new Date(time)));

			}
		});
        testalarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent("com.alarm.TIME_ALARM");
				intent.setAction("com.alarm.TIME_ALARM");
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				intent.putExtra("time","测试alarm的广播！");
				sendBroadcast(intent);
				System.out.println("发送了alarm测试广播");
			}
		});
        registeralarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				// TODO Auto-generated method stub
				registerReceiver(alarmver, alarmFilter);
				long time=System.currentTimeMillis()+3*60*1000;
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");				
//				Intent intent=new Intent("com.alarm.TIME_ALARM");
//				intent.setAction("com.alarm.TIME_ALARM");
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 

//				intent.putExtra("time",simpleDateFormat.format(new Date(time)));
//				System.out.println("注册了一个循环闹钟事件："+simpleDateFormat.format(new Date(time)));
//				PendingIntent pIntent=PendingIntent.getBroadcast(MainActivity.this, 12, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//				AlarmManager aManager;
//				aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
////				aManager.set(AlarmManager.RTC_WAKEUP, time, pIntent);
//				aManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 0, 10*1000, pIntent);
//				
//				AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//				Intent intent = new Intent();
//				intent.setAction("com.alarm.TIME_ALARM");
//				PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 33,
//						intent, PendingIntent.FLAG_UPDATE_CURRENT);
//				long triggerAtTime = SystemClock.elapsedRealtime()+3*60*1000;
//				manager.set(AlarmManager.ELAPSED_REALTIME, triggerAtTime, pendingIntent);
				System.out.println("注册了一个三分钟闹钟事件："+simpleDateFormat.format(new Date(time)));
				
				PullUntil.startPollingBroadcast(MainActivity.this, 5, "com.alarm.TIME_ALARM");
			}
		});
        
        registerReceiver(receiver, intentFilter);
        
        Intent service=new Intent(this, service2.class);
        startService(service);
        
        startbroadcast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent("android.Intent.BROADCAST");
				intent.setClass(getBaseContext(), myReceiver.class);
				sendBroadcast(intent);
				
			}
		});
        isrootbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isRoot()){
					Toast.makeText(MainActivity.this, "手机已经root", Toast.LENGTH_SHORT).show();
					
			}
				else {
					Toast.makeText(MainActivity.this, "手机没有root", Toast.LENGTH_SHORT).show();						
					}
				}
		});
        
        getrootbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String apkRoot = "chmod 777 " + getPackageCodePath();
				if (RootCommand(apkRoot)) {
					Toast.makeText(MainActivity.this, "获取root权限成功！", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(MainActivity.this, "获取root权限失败！", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        
        
        
        
        
//        Intent intent=new Intent("com.test.receiver1");
//        intent.putExtra("sender_name", "hha");
//        sendBroadcast(intent);
//        Log.i("TAG", "send cast 1");
//        Intent intent2=new Intent("com.test.receiver2");
//        intent2.putExtra("sender_name", "hha2");
//        sendBroadcast(intent2);
//        Log.i("TAG", "send cast 2");
        
    }

    public boolean isRoot() {  
    	  
        boolean root = false;  
  
        try {  
            if ((!new File("/system/bin/su").exists())  
                    && (!new File("/system/xbin/su").exists())) {  
                root = false;  
            } else {  
                root = true;  
            }  
  
        } catch (Exception e) {  
        }  
  
        return root;  
    }
    
    public static boolean RootCommand(String command) {  
    	  
        Process process = null;  
        DataOutputStream os = null;  
  
        try {  
  
            process = Runtime.getRuntime().exec("su");  
            os = new DataOutputStream(process.getOutputStream());  
            os.writeBytes(command + "\n");  
            os.writeBytes("exit\n");  
            os.flush();  
            process.waitFor();  
  
        } catch (Exception e) {  
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());  
            return false;  
  
        } finally {  
  
            try {  
                if (os != null) {  
                    os.close();  
                }  
                process.destroy();  
            } catch (Exception e) {  
            }  
        }  
  
        Log.d("*** DEBUG ***", "Root SUC ");  
        return true;  
  
    }  
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "程序退出了！",Toast.LENGTH_SHORT).show();
		registerReceiver(receiver, intentFilter);
		super.onDestroy();
		Intent intent = new Intent(Intent.ACTION_MAIN);

		intent.addCategory(Intent.CATEGORY_HOME);

		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		this.startActivity(intent);

		System.exit(0);
	}
    
    
    
    
    
}
