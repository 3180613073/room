package com.wangwei.lvyan.http.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import rx.Observable


interface ObservableService {
    // 聚合数据，获取新闻
    @GET("toutiao/index")
    fun getNews(
        @Query("key") key: String?
    ): Observable<Any>

//    //手机验证码登录
//    @POST("user/newUser")
//    fun login(@Body loginReques: LoginReques): Observable<LoginResponse>

    //密码登录

    //POST /app/user/needs  提交需求
//    @POST("user/needs")
//    fun needs(@Body any: Any): Observable<Any>


}