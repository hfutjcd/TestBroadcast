package com.example.testbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class myReceiver2 extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
			Toast.makeText(arg0, "ʱ�䷢���仯...", 0).show();
		    Log.i("liujun", "ʱ�䷢���仯������");
		    }

}
