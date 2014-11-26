package com.transround.dev.localizationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView webview = new WebView(this);
		setContentView(webview);
		
		webview.loadUrl(getResources().getString(R.string.localization_demo_transround_help_link));

	}

}
