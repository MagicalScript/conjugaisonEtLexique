package com.example.magicalscript.powerconjugaison;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;


public class lexique_lesson extends Activity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lexique_lesson);
        ((WebView) (findViewById(R.id.webView))).getSettings().setBuiltInZoomControls(true);
        Intent intent=getIntent();
        ((WebView) (findViewById(R.id.webView))).loadUrl(intent.getStringExtra("url"));
    }

    @Override
    protected void onPause() {


        super.onPause();
    }
    //needs implimentation
    //@Override
    //public void _url(String url) {
    //    WB.loadUrl(url);
    //}

}
