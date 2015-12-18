package com.namran.mysmeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("sme.namran.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button updatesButton = (Button) findViewById(R.id.updates_button);
        Button productsButton = (Button) findViewById(R.id.products_button);

        Button shareButton = (Button) findViewById(R.id.share_button);

        ImageButton callButton = (ImageButton) findViewById(R.id.call_button);



        final WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.loadUrl("http://sme.namran.com");

        updatesButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        myWebView.loadUrl("http://sme.namran.com");
                        //editText.setText("");
                        //info.setText("");
                    }
                });

        productsButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        myWebView.loadUrl("http://sme.namran.com/cart/");
                        //editText.setText("");
                        //info.setText("");
                    }
                });

        shareButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                    //    myWebView.loadUrl("http://sme.namran.com/contact-us/");
                        //editText.setText("");
                        //info.setText("");


                   // Intent intent = new Intent(Intent.ACTION_MAIN);
                    //null intent.setComponent(new ComponentName("com.namran.fbc","com.namran.fbc.MainActivity"));
                    //startActivity(intent);
                    // Create the text message with a string
                  final Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                        String webUrl = myWebView.getUrl();
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi,I found this interesting item from SNZ Shoppe at "+ webUrl);
                    sendIntent.setType("text/plain");
                    //startActivity(sendIntent);
                    startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                    }
                });
        callButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                    Uri number = Uri.parse("tel:+60129860894");
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }


                });
    }





}

