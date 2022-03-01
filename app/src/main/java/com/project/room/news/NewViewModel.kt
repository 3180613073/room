package com.project.room.news

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.wangwei.lvyan.http.RetrofitUtils
import org.json.JSONObject
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

//class NewViewModel: ViewModel(){
//AndroidViewModel有一个环境
class NewViewModel(listener: NewInterface, application: Application) : AndroidViewModel(application){
    var mContext: Context? = null
    lateinit var mListener: NewInterface
    //构造函数
    init {
        this.mContext = application
        this.mListener = listener
    }


    //用代替RXJAVA的liveData
    var tv: MutableLiveData<String> = MutableLiveData()

    var tvOne: MutableLiveData<String> = MutableLiveData()
    var newData: MutableLiveData<Any> = MutableLiveData()

    fun test(){
       tv.value="oooo"
       Log.e("TAG", "test: ces" +tvOne.value)
       Log.e("TAG", "test: ces tv" +tv.value)
   }

    fun getNews(){
        //请求新闻数据  key是我在聚合上的appkey
        RetrofitUtils.service()
            .getNews("6fb113655e2b36e2c9fb1d349d979c0a") //获取Observable对象
            .subscribeOn(Schedulers.newThread()) //请求在新的线程中执行
            ?.observeOn(Schedulers.io()) //请求完成后在io线程中执行
            ?.observeOn(AndroidSchedulers.mainThread()) //最后在主线程中执行
            ?.subscribe(object : Subscriber<Any?>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    Toast.makeText(mContext, e.message, Toast.LENGTH_LONG)
                            .show()
                    //请求失败

                }

                override fun onNext(t: Any?) {
                    var a = Gson().toJson(t)
//                        val obj: JSONObject = JSONObject().toJsonString()
                    val jsonResult = JSONObject(a)
                    Log.e("ppp", "onNext: getAllData" + a)
                    //请求成功


                        var array = jsonResult.optJSONObject("result")?.optJSONArray("data")
                        newData.value=array

                        mListener.sucess()
                    }



            })
    }
}