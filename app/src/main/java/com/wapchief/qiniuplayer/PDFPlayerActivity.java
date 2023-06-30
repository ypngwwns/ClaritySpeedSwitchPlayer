package com.wapchief.qiniuplayer;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * @author wapchief
 * @date 2018/3/7
 */

public class PDFPlayerActivity extends AppCompatActivity implements DownloadFile.Listener {

    private String TAG = "PDFPlayerActivity";
    private RemotePDFViewPager mWebView;
    private PDFPagerAdapter adapter;
    private String mUrl = "http://ese1a1b2c8d5xn.pri.qiqiuyun.net/coursematerial-456/20181128065602-8nbf94r1qcso0gww/a535dbad35acd0a8_pdf?e=1585036215&token=ExRD5wolmUnwwITVeSEXDQXizfxTRp7vnaMKJbO-:N-PnGd5N35BWH4rLrYZVk_ZRn2I=";
    private RelativeLayout pdf_root;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        initView();
        initData();
    }

    private void initData() {
        final DownloadFile.Listener listener = this;
        mWebView = new RemotePDFViewPager(this, mUrl, listener);
        mWebView.setId(R.id.pdf_web);

    }

    private void initView() {
        pdf_root = (RelativeLayout) findViewById(R.id.remote_pdf_root);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        mWebView.setAdapter(adapter);
        updateLayout();
    }

    @Override
    public void onFailure(Exception e) {
        Log.e(TAG, "onFailure:" + e.toString());
    }

    /*更新视图*/
    private void updateLayout() {
        pdf_root.removeAllViewsInLayout();
        pdf_root.addView(mWebView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onProgressUpdate(int progress, int total) {
        Log.e(TAG, "progress:" + progress+",total:"+total);
    }


}
