package com.project.room.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.room.main.MainViewModel

abstract class BaseActivity: AppCompatActivity() {
//    lateinit var dataBind: ActivityMainBinding
//    lateinit var viewModel: MainViewModel
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        dataBind= DataBindingUtil.setContentView(this, R.layout.activity_main)
//        //最新的方法，旧的ViewModelProviders过时了
//        viewModel= ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
//            MainViewModel::class.java)
//        dataBind.vm=viewModel
//        //lifeCycler
//        //建立感应关系 让view和model感应起来  少了这句界面不会改变
//        dataBind.lifecycleOwner=this
//    }

    //得到layoutID
    abstract fun getLayoutId():Int
    //得到viewmodel
    abstract fun getViewMode():ViewModel
//    lateinit var dataBind: ViewDataBinding
    lateinit var dataBind: ViewDataBinding
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBind= DataBindingUtil.setContentView(this, getLayoutId())
        //最新的方法，旧的ViewModelProviders过时了
        viewModel= getViewMode()
        attachDataAndVm()
        //lifeCycler
        //建立感应关系 让view和model感应起来  少了这句界面不会改变
        dataBind.lifecycleOwner=this
        initData()
    }
    abstract fun attachDataAndVm()
    open fun initData() {}
}