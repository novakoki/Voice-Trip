package com.cxsj.trip;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class Welcome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		final Intent intent=new Intent(this,Home.class);
		Timer timer = new Timer();
		  TimerTask task = new TimerTask() {
		   @Override
		   public void run() {
		    startActivity(intent);
		   }
		  };
		timer.schedule(task, 1000 * 1);
	}
}
