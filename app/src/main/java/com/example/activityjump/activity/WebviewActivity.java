//package com.example.activityjump.activity;
//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.webkit.WebChromeClient;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebResourceResponse;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ProgressBar;
//
//import androidx.annotation.Nullable;
//import androidx.compose.ui.unit.Density;
//
//import com.example.activityjump.R;
//import com.example.activityjump.sonic.HostSonicRuntime;
//import com.example.activityjump.sonic.SonicJavaScriptInterface;
//import com.example.activityjump.sonic.SonicSessionClientImpl;
//import com.tencent.sonic.sdk.SonicConfig;
//import com.tencent.sonic.sdk.SonicEngine;
//import com.tencent.sonic.sdk.SonicSession;
//import com.tencent.sonic.sdk.SonicSessionConfig;
//
////public class WebviewActivity extends Activity {
////    public final static String PARAM_URL = "param_url";
////
////    public final static String PARAM_MODE = "param_mode";
////    private WebView wvWebView;
////    private ProgressBar pbWebView;
////    private SonicSession sonicSession;
////
////    private String url;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_webview);
////        wvWebView = findViewById(R.id.wvWebView);
////        pbWebView = findViewById(R.id.pbWebView);
////
////        Intent intent = getIntent();
////        String url = intent.getStringExtra(PARAM_URL);
////        int mode = intent.getIntExtra(PARAM_MODE, -1);
////        if (TextUtils.isEmpty(url) || -1 == mode) {
////            finish();
////            return;
////        }
////
////        // step 1: Initialize sonic engine if necessary, or maybe u can do this when application created
////        if (!SonicEngine.isGetInstanceAllowed()) {
////            SonicEngine.createInstance(new HostSonicRuntime(getApplication()), new SonicConfig.Builder().build());
////        }
////
////        SonicSessionClientImpl sonicSessionClient = null;
////
////        // step 2: Create SonicSession
////        sonicSession = SonicEngine.getInstance().createSession(url,  new SonicSessionConfig.Builder().build());
////        if (null != sonicSession) {
////            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
////        } else {
////            // this only happen when a same sonic session is already running,
////            // u can comment following codes to feedback as a default mode.
////            throw new UnknownError("create session fail!");
////        }
////
////        initData(intent,sonicSessionClient);
////
////    }
////
////
////    void initData(Intent intent, SonicSessionClientImpl sonicSessionClient) {
////        url = "https://www.baidu.com/";
////
////        WebSettings webSettings = wvWebView.getSettings();
////        webSettings.setJavaScriptEnabled(true);
////        webSettings.setDomStorageEnabled(true);
////        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
////        wvWebView.requestFocus();
////
////        // 设置setWebChromeClient对象
////        wvWebView.setWebChromeClient(new WebChromeClient() {
////            @Override
////            public void onReceivedTitle(WebView view, String title) {
////                super.onReceivedTitle(view, title);
////            }
////            @Override
////            public void onProgressChanged(WebView view, int newProgress) {
////                super.onProgressChanged(view, newProgress);
////                pbWebView.setProgress(newProgress);
////            }
////        });
////
////        wvWebView.setWebViewClient(new WebViewClient(){
////            @Override
////            public void onPageStarted(WebView view, String url, Bitmap favicon) {
////                super.onPageStarted(view, url, favicon);
////                pbWebView.setVisibility(View.VISIBLE);
////            }
////
////            @Override
////            public void onPageFinished(WebView view, String url) {
////                super.onPageFinished(view, url);
////                pbWebView.setVisibility(View.GONE);
////
////                if (sonicSession != null) {
////                    sonicSession.getSessionClient().pageFinish(url);
////                }
////            }
////
////            @Override
////            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
////                return shouldInterceptRequest(view, request.getUrl().toString());
////            }
////
////            @Override
////            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
////                if (sonicSession != null) {
////                    //step 6: Call sessionClient.requestResource when host allow the application
////                    // to return the local data .
////                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
////                }
////                return null;
////            }
////        });
////
////
////        // step 4: bind javascript
////        // note:if api level lower than 17(android 4.2), addJavascriptInterface has security
////        // issue, please use x5 or see https://developer.android.com/reference/android/webkit/
////        // WebView.html#addJavascriptInterface(java.lang.Object, java.lang.String)
////        webSettings.setJavaScriptEnabled(true);
////        wvWebView.removeJavascriptInterface("searchBoxJavaBridge_");
////        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
////        wvWebView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");
////
////        // step 5: webview is ready now, just tell session client to bind
////        if (sonicSessionClient != null) {
////            Log.d("WebviewActivity", "webview is ready now, just tell session client to bind");
////            sonicSessionClient.bindWebView(wvWebView);
////            sonicSessionClient.clientReady();
////        } else { // default mode
////            wvWebView.loadUrl(url);
////        }
////    }
////
////    @Override
////    public void onBackPressed() {
////        super.onBackPressed();
////    }
////
////    @Override
////    protected void onDestroy() {
////        if (null != sonicSession) {
////            sonicSession.destroy();
////            sonicSession = null;
////        }
////        super.onDestroy();
////    }
////}
////
//
//
//public class WebviewActivity extends Activity {
//
//    private String url;
//
//    private SonicSession sonicSession;
//    private Intent intent;
//    private WebView webView;
//    private ProgressBar pbWebView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_webview);
//        Log.d("WebviewActivity", "onCreate");
//        initView();
//    }
//
//    private void initView() {
//        Log.d("WebviewActivity", "initView");
//
//        intent = getIntent();
//        url = "https://www.baidu.com/";
////        url = "https://www.tencent.com/zh-cn/";
//        if (TextUtils.isEmpty(url)){
//            return;
//        }
//        // step 1: Initialize sonic engine if necessary, or maybe u can do this when application created
//        Log.d("WebviewActivity", "SonicEngine.isGetInstanceAllowed()        " + SonicEngine.isGetInstanceAllowed());
//        if (!SonicEngine.isGetInstanceAllowed()) {
//            SonicEngine.createInstance(new HostSonicRuntime(getApplication()), new SonicConfig.Builder().build());
//            Log.d("WebviewActivity", "SonicEngine.getInstance() " + SonicEngine.getInstance());
//
//        }
//
//        SonicSessionClientImpl sonicSessionClient = null;
//
//        // step 2: Create SonicSession
//        SonicSessionConfig.Builder configBuilder = new SonicSessionConfig.Builder();
//        SonicSessionConfig config = configBuilder.build();
//
//        Log.d("WebviewActivity", "SonicEngine config" + config);
//        Log.d("WebviewActivity", "SonicEngine available: " +  SonicEngine.getInstance().isSonicAvailable());
//
//        sonicSession = SonicEngine.getInstance().createSession(url,  config);
//
//        Log.d("WebviewActivity", "SonicEngine create session: " + (sonicSession != null ? "success" : "fail"));
//        if (null != sonicSession) {
//            boolean b = sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
//            Log.d("WebviewActivity", "SonicEngine bindClient: " + b);
//        } else {
//            // this only happen when a same sonic session is already running,
//            // u can comment following codes to feedback as a default mode.
//            //  throw new UnknownError("create session fail!");
//        }
//
//        webView = findViewById(R.id.wvWebView);
//        pbWebView = findViewById(R.id.pbWebView);
//        // 设置setWebChromeClient对象
//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//            }
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                pbWebView.setProgress(newProgress);
//            }
//        });
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                pbWebView.setVisibility(View.GONE);
//                if (sonicSession != null) {
//                    sonicSession.getSessionClient().pageFinish(url);
//                }
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                pbWebView.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//                return shouldInterceptRequest(view, request.getUrl().toString());
//            }
//
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//                if (sonicSession != null) {
//                    //step 6: Call sessionClient.requestResource when host allow the application
//                    // to return the local data .
//                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
//                }
//                return null;
//            }
//        });
//
//        WebSettings webSettings = webView.getSettings();
//
//        // step 4: bind javascript
//        // note:if api level lower than 17(android 4.2), addJavascriptInterface has security
//        // issue, please use x5 or see https://developer.android.com/reference/android/webkit/
//        // WebView.html#addJavascriptInterface(java.lang.Object, java.lang.String)
//        webSettings.setJavaScriptEnabled(true);
//        webView.removeJavascriptInterface("searchBoxJavaBridge_");
//        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
//        webView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");
//
//        // init webview settings
//        webSettings.setAllowContentAccess(true);
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        webSettings.setSavePassword(false);
//        webSettings.setSaveFormData(false);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//
//
//        Log.d("WebviewActivity", "sonicSessionClient = " + sonicSessionClient);
//        // step 5: webview is ready now, just tell session client to bind
//        if (sonicSessionClient != null) {
//            sonicSessionClient.bindWebView(webView);
//            sonicSessionClient.clientReady();
//            Log.d("WebviewActivity", "sonic clientReady");
//        } else { // default mode
//            webView.loadUrl(url);
//        }
//    }
//}
//
