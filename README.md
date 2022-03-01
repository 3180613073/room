# room
博客地址https://blog.csdn.net/qq_39286138/article/details/123185778
写一个开源项目，采用kotlin+MVVM+viewmodel+databinding+liveData+Retrofit搭框架 供大家参考
​
为什么选用的是kotlin呢，因为我个人更喜欢写kotlin，当然也因为kotlin语言发展前景不错。

这个开源项目内容不是很多，主要是框架的方面，页面很少，主要是给大家参考

首先：

欢迎页面三秒过后进去主页。

因为是kotlin，所以这里就不开线程了，用kotlin独有的协程。

   implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1"
协程要这么引入。

协程是代替线程的，性能不一定比线程优秀，但是协程更方便我们编码

用协程写非常方便

 //协程睡三秒，然后进去主页面
        GlobalScope.launch {
            delay(3000)//三秒 跳转
            startActivity(Intent(this@LoadActivity,MainActivity::class.java))
        }

这就是我的协程，写的非常简洁，很方便程序员开发

看一下效果




可以看到，过了3秒，跳转到主页面

还有一个点，是页面太丑了，有toorbar，所以很丑，我们可以调

toolbar是decorview里默认带有的，我们可以修改一下style

<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>

</resources>
这样就改成默认不要toolbar

当然，还有状态栏

但是状态栏不是的decorview里面的，网上很多小伙伴们都知道怎么写沉浸式状态栏。

但是原理很多小伙伴都不清楚

那我简单汪几句。

网上的做法是，先把activity改成透明的，然后把我们的XML文件设置成和屏幕一样大，这样就实现了沉浸式状态栏。

但是这是为什么呢。

因为activity里面有statusbar，就是我们的状态栏，把它设成透明的，那状态栏就也是透明的了，再把我们的contentview设置成和activity一样大，那么就完成了沉浸式状态栏，来看下效果




 这样就美观多了

其实原理很简单，我稍微手画了一个



 之后便是搭框架，viewmodel继承androidViewmodel，然后把context传进去，再来一个接口，让viewmodel和activity交互

viewmodel的代码大体如下


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

这样就完成了一个看新闻的小APP

虽然页面很少，但是该有的框架都有，liveData+retrofit+databinding

使用的接口是聚合数据的接口

效果如下



​
