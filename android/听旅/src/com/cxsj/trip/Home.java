package com.cxsj.trip;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("JavascriptInterface")
public class Home extends Activity {
	final Mp3Recorder recorder = new Mp3Recorder();
	TextView textView;
	ProgressBar progressBar;
	private Handler mHandler = new Handler();
	private ValueCallback<Uri> mUploadMessage;  
	private final static int FILECHOOSER_RESULTCODE=1; 
	 @Override  
	 protected void onActivityResult(int requestCode, int resultCode,  
	                                    Intent intent) {  
	  if(requestCode==FILECHOOSER_RESULTCODE)  
	  {  
	   if (null == mUploadMessage) return;  
	            Uri result = intent == null || resultCode != RESULT_OK ? null  
	                    : intent.getData();  
	            mUploadMessage.onReceiveValue(result);  
	            mUploadMessage = null;  
	  }
	  }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		final WebView web=(WebView)findViewById(R.id.webView1);
		textView=(TextView)findViewById(R.id.textView1);
		progressBar=(ProgressBar)findViewById(R.id.progressBar1);
		ImageView mImageView1=(ImageView)findViewById(R.id.image_friendfeed);
		ImageView mImageView2=(ImageView)findViewById(R.id.image_myfeed);
		ImageView mImageView3=(ImageView)findViewById(R.id.image_info);
		ImageView mImageView4=(ImageView)findViewById(R.id.image_map);
		ImageView mImageView5=(ImageView)findViewById(R.id.plus_btn);
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("http://shaoziqi.my.phpcloud.com/android/home.php");
		web.addJavascriptInterface(new JavaScriptInterface(), "trip");
		mImageView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				web.loadUrl("http://shaoziqi.my.phpcloud.com/android/hot.php");
			}
		});
		mImageView2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				web.loadUrl("http://shaoziqi.my.phpcloud.com/android/home.php");
			}
		});
		mImageView5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				web.loadUrl("http://shaoziqi.my.phpcloud.com/android/post.html");
			}
		});
		mImageView4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				web.loadUrl("http://shaoziqi.my.phpcloud.com/mobile/map/demo.html");
			}
		});
		if(!checkNewWorkStatus())
		{web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);}
		else{web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);}
		WebSettings webseting = web.getSettings();
		webseting.setDomStorageEnabled(true);        	
	    webseting.setAppCacheMaxSize(1024*1024*8);//设置缓冲大小，我设的是8M
		String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();    
	    webseting.setAppCachePath(appCacheDir);
	    webseting.setAllowFileAccess(true);
	    webseting.setAppCacheEnabled(true);
	    web.setWebViewClient(new myWebClient());
	    web.setWebChromeClient(m_chromeClient);
	}
	
	private boolean checkNewWorkStatus() {
		 
		boolean netStatus = false;
		boolean isConnected=false;
		ConnectivityManager connectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		 
		connectManager.getActiveNetworkInfo();
		        
		NetworkInfo activeInfo=connectManager.getActiveNetworkInfo();
		NetworkInfo mobInfo=connectManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (activeInfo != null) {
		netStatus = activeInfo.isAvailable();
		isConnected=activeInfo.isConnected();
		}
		if(!netStatus)
		{
			return false;
		}
		else{
			return true;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.home, menu);
	    return super.onCreateOptionsMenu(menu);
	}			

	public class JavaScriptInterface
	{
		public String record()
		{
			Log.e("adsadas","asfsafsf");
			try {
				recorder.startRecording();	
			} catch(IOException e) {
				Log.d("Home", "Start error");
			}
			String externalPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath()+ "/tinglv/record/recording.mp3";
			Log.e("adsad",externalPath);
			return externalPath;
		}
		public void stop()
		{
			try {
				recorder.stopRecording();	
			} catch(IOException e) {
				Log.d("Home", "Start error");
			}
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
           public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture){
               mUploadMessage = uploadMsg;  
               Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
               i.addCategory(Intent.CATEGORY_OPENABLE);  
               i.setType("image/audio/*");
               Home.this.startActivityForResult( Intent.createChooser( i, "File Chooser" ), Home.FILECHOOSER_RESULTCODE );

           }
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
		textView.setText(progress+"%");
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
