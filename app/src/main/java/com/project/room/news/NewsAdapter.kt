package com.project.room.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.room.R
import com.project.room.databinding.ItemMyNewsBinding
import org.json.JSONArray
import org.json.JSONObject


class NewsAdapter(context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var mContext: Context? = null
    var mData:JSONArray=JSONArray()


    //构造函数
    init{
        this.mContext = context
    }
    fun setData(data:JSONArray){
        this.mData=data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(mContext).inflate(R.layout.item_my_news, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return mData.length()
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.mBinding?.tvTime?.text=mData.getJSONObject(position).optString("author_name")
        holder.mBinding?.tvDocTitle?.text=mData.getJSONObject(position).optString("title")
        holder.mBinding?.tvNum?.text=mData.getJSONObject(position).optInt("category").toString()
        holder.mBinding?.tvCount?.text=mData.getJSONObject(position).optInt("date").toString()
        holder.itemView.setOnClickListener {
            if(onClick!=null){
                onClick!!.setOnClick(it,mData.getJSONObject(position))
            }
        }


    }

    //点击取消
    interface OnClick {
        fun setOnClick(
            view: View?,
            entity: JSONObject
        )
    }
    private var onClick: OnClick? = null

    fun setOnClick(onClick: OnClick?) {
        this.onClick = onClick
    }

     class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
         var mBinding: ItemMyNewsBinding? = ItemMyNewsBinding.bind(view!!)

    }
}