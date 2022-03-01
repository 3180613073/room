package com.project.room.newsDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.room.R
import kotlinx.android.synthetic.main.activity_news_details.*


class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        intent.getStringExtra("url")?.let { wv_detail.loadUrl(it) }
    }
}