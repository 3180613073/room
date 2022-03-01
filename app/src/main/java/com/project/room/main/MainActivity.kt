package com.project.room.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.project.room.R
import com.project.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var dataBind:ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind= DataBindingUtil.setContentView(this, R.layout.activity_main)
        //最新的方法，旧的ViewModelProviders过时了
        viewModel= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(
            MainViewModel::class.java)
        dataBind.vm=viewModel
        //lifeCycler
        //建立感应关系 让view和model感应起来  少了这句界面不会改变
        dataBind.lifecycleOwner=this

    }
}