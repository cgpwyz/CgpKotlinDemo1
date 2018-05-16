package com.cgp.kotlindemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cgp.kotlindemo.R
import com.cgp.kotlindemo.utlis.Utlis
import java.util.*

/**
 * Created by ty on 2018/5/7.
 */
class HomeAdapter(var mContext: Context, var mData: ArrayList<Int>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    /**
     * 什么静态常量
     */
    companion object {
        private val ITEM_TYPE_BANNER = 1    //Banner 类型
        private val ITEM_TYPE_TEXT_HEADER = 2   //textHeader
        private val ITEM_TYPE_CONTENT = 3    //item
    }

    private var bannerItemSize = 0
    private var mInflater: LayoutInflater? = null

    /**
     * 初始化代码块以init关键字开头
     */
    init {
        mInflater = LayoutInflater.from(mContext)
    }

    /**
     * 设置广告
     */
    fun setBannerItemSize(count: Int) {
        bannerItemSize = count
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
//        holder?.setData(position)

        when {
            position == 0 ->
               Utlis.showLog("广告")
            position == 1 ->
                Utlis.showLog("标题")
            else ->
                Utlis.showLog("内容")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when (viewType) {
            ITEM_TYPE_BANNER -> {
                //广告
                Utlis.showLog("加载广告")
                return ViewHolder(inflaterView(R.layout.item_home_banner, parent!!))
            }
            ITEM_TYPE_TEXT_HEADER -> {
                //标题
                Utlis.showLog("加载标题")
                return ViewHolder(inflaterView(R.layout.item_home_header, parent!!))
            }
            else -> {
                //内容
                Utlis.showLog("加载内容")
                return ViewHolder(inflaterView(R.layout.item_home_content, parent!!))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when {
            position == 0 ->
                return  ITEM_TYPE_BANNER//广告
            position == 1 ->
                return ITEM_TYPE_TEXT_HEADER//标题
            else ->
                return  ITEM_TYPE_CONTENT//内容
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var tv: TextView = view as TextView
        fun setData(position: Int) {
//            tv.setText("我是测试数据" + position)
//            tv.setTextSize(20f)
        }
    }

    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view!!
    }
}