package capstone.schwendimankw.campingapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FireActivity extends Activity {
    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = new WebView(this);
        mWebView.loadUrl("file:///android_asset/fire.html");
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);

        setContentView(mWebView);
    }
}
