package com.project.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.room.main.MainActivity
import com.project.room.news.NewsActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        //协程睡三秒，然后进去主页面
        GlobalScope.launch {
            delay(3000)//三秒 跳转
            startActivity(Intent(this@LoadActivity,NewsActivity::class.java))
            finish()
        }
    }
}