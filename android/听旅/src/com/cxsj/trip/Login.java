package com.cxsj.trip;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebStorage;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Login extends Activity {
	
	Handler handler;
	TextView textView;
	ProgressBar progressBar;
	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		textView=(TextView)findViewById(R.id.textView1);
		progressBar=(ProgressBar)findViewById(R.id.progressBar1);
		WebView web=(WebView) findViewById(R.id.webview1);
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("http://shaoziqi.my.phpcloud.com/android/login.html");
		web.setWebViewClient(new myWebClient());
		WebSettings webseting = web.getSettings();
		webseting.setDomStorageEnabled(true);        	
	    webseting.setAppCacheMaxSize(1024*1024*8);//设置缓冲大小
		String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();    
	    webseting.setAppCachePath(appCacheDir);
	    webseting.setAllowFileAccess(true);
	    webseting.setAppCacheEnabled(true);
	    webseting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
	    web.setWebChromeClient(m_chromeClient);
	    web.addJavascriptInterface(new JavaScriptInterface(), "login");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	final class JavaScriptInterface
	{
		View v;
		public void step()
		{
			act(v);
		}
		public void act(View v)
		{
			Intent intent=new Intent(Login.this,Home.class);
			startActivity(intent);
		}
	}
	public class myWebClient extends WebViewClient
	{
		
		@Override
	    public void onPageStarted(WebView view, String url, Bitmap favicon) {
	        // TODO Auto-generated method stub
	        super.onPageStarted(view, url, favicon);
	    }

	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        // TODO Auto-generated method stub

	        view.loadUrl(url);
	        return true;

	    }

	    @Override
	    public void onPageFinished(WebView view, String url) {
	        // TODO Auto-generated method stub
	        super.onPageFinished(view, url);
	    }
	}
	public WebChromeClient m_chromeClient = new WebChromeClient(){
        //扩充缓存的容量  
	@Override
	public void onReachedMaxAppCacheSize(long spaceNeeded,  
	            long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {  
		    quotaUpdater.updateQuota(spaceNeeded * 2);  
		}
	public void onProgressChanged(WebView view, int progress) {
	     // Activities and WebViews measure progress with different scales.
	     // The progress meter will automatically disappear when we reach 100%
		super.onProgressChanged(view, progress);
		if (progress == 0) {
          textView.setVisibility(View.VISIBLE);
          progressBar.setVisibility(View.VISIBLE);
          }
		textView.setText("正在加载"+progress+"%");
		textView.postInvalidate();
		progressBar.setProgress(progress);
		progressBar.postInvalidate();
		if (progress == 100) {
		         textView.setVisibility(View.GONE);
		         progressBar.setVisibility(View.GONE);
		  }
	   }
	};
}
