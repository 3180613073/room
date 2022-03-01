package com.project.room.news

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.room.R
import com.project.room.base.BaseActivity
import com.project.room.databinding.ActivityNewsBinding
import com.project.room.newsDetails.NewsDetailsActivity
import kotlinx.android.synthetic.main.activity_news.*
import org.json.JSONArray
import org.json.JSONObject

class NewsActivity : BaseActivity(), NewInterface {
    lateinit var binding: ActivityNewsBinding
    lateinit var vm: NewViewModel
    override fun getLayoutId(): Int {
        return R.layout.activity_news
    }

    override fun getViewMode(): ViewModel {
        return NewViewModel(this,application)
    }

    override fun attachDataAndVm() {
        binding= dataBind as ActivityNewsBinding
        vm= viewModel as NewViewModel
        (binding).vm=vm
    }

    override fun initData() {
        super.initData()
        vm.getNews()


    }

    override fun error() {

    }
    lateinit var mAdapter:NewsAdapter
    override fun sucess() {
        rv_new.layoutManager= LinearLayoutManager(this)

        mAdapter=NewsAdapter(this)

        rv_new.adapter=mAdapter
        mAdapter.setData(vm.newData.value as JSONArray)
        mAdapter.setOnClick(object :NewsAdapter.OnClick{
            override fun setOnClick(view: View?, position: JSONObject) {

                val detail = Intent(this@NewsActivity, NewsDetailsActivity::class.java)
                detail.putExtra("url",position.optString("url"))
                startActivity(detail)
            }
        })
    }
}