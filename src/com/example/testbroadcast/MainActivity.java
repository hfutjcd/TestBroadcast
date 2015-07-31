package com.example.testbroadcast;

import java.io.DataOutputStream;
import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isrootbtn=(Button) findViewById(R.id.button1);
        getrootbtn=(Button) findViewById(R.id.button2);
        startbroadcast=(Button) findViewById(R.id.button3);
        startbroadcast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
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
    
    
    
    
    
}
