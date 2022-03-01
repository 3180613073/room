package com.wangwei.lvyan.http

import android.util.Log
import okhttp3.*
import okhttp3.Interceptor.Chain
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException


class HhmOkInterceptor : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Chain): Response {
//        //获取request
//        val request: Request = chain.request()
//        //获取request的创建者builder
//        val builder: Request.Builder = request.newBuilder()
//        //从request中获取headers，通过给定的键url_name
//        //val headerValues: List<String> = request.headers("base_url")
//
//        //request添加请求头
//        builder.addHeader("Accept","*/*")
//
//        //返回参数
//        val response = chain.proceed(request)
//        val responseBody = response.body()
//        Log.e("pppp", "intercept: 返回参数"+getResponseBody(response) )
//        return response
//    }

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        //获取request
        val request: Request = chain.request()
        //获取request的创建者builder
        val builder: Request.Builder = request.newBuilder()
        //从request中获取headers，通过给定的键url_name
        //val headerValues: List<String> = request.headers("base_url")

        //request添加请求头
        builder.addHeader("Accept","*/*")
        //返回参数
        val response = chain.proceed(request)
        val responseBody = ResponseBody.create(MediaType.parse("text/plain"),getResponseBody(response))
        //val myBody: ResponseBody = ResponseBody.create(MediaType.get("text/plain"), getResponseBody(response))
        Log.e("pppp", "intercept: 返回参数"+getResponseBody(response) )

        var newResponse:Response=response.newBuilder().body(responseBody).build()
        Log.e("pppp", "intercept:新 返回参数"+getResponseBody(newResponse) )
        return response
    }

    fun getResponseBody(response: Response): String? {
        val UTF8: Charset = Charset.forName("UTF-8")
        val responseBody = response.body()
        val source = responseBody.source()
        try {
            source.request(Long.MAX_VALUE) // Buffer the entire body.
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val buffer: Buffer = source.buffer()
        var charset: Charset = UTF8
        val contentType: MediaType? = responseBody.contentType()
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8)
            } catch (e: UnsupportedCharsetException) {
                e.printStackTrace()
            }
        }
        return buffer.clone().readString(charset)
    }

}
