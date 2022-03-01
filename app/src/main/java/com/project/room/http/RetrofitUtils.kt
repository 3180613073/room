package com.wangwei.lvyan.http

import com.project.room.contants.Contants
import com.wangwei.lvyan.http.service.ObservableService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//静态类名称
object RetrofitUtils {
    fun service():ObservableService{
        //设置超时
        var client=OkHttpClient.Builder()
            .addInterceptor(HhmOkInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        var retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //新的配置
            .baseUrl(Contants.baseurl)
            .client(client)
            .build()
        var service: ObservableService = retrofit.create(ObservableService::class.java)
        return service
    }

}