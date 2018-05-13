package co.edureka.edurekamay12session;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowNewsActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        getSupportActionBar().setTitle("Zee News");

        webView = findViewById(R.id.webView);

        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://zeenews.india.com/");
    }
}
