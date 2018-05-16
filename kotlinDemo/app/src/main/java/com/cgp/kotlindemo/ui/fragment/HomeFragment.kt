package com.cgp.kotlindemo.ui.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cgp.kotlindemo.R
import com.cgp.kotlindemo.adapter.HomeAdapter
import com.cgp.kotlindemo.utlis.Utlis
import com.scwang.smartrefresh.header.MaterialHeader
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 主页
 * Created by ty on 2018/4/27.
 */
class HomeFragment : Fragment() {
    private var mTitle: String? = null
    private var mScrollValue: Int = 0

    /**
     * 构造方法，返回自己
     */
    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {

        //得到下拉区块对象
        var mMaterialHeader = mSmartRefreshLayout.refreshHeader as MaterialHeader?
        //打开下拉刷新区域块背景:
        mMaterialHeader?.setShowBezierWave(true)
        //设置下拉刷新主题颜色
        mSmartRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)

        mRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //当前滚动状态
                //0（SCROLL_STATE_IDLE）表示recyclerview是不动的
                //1（SCROLL_STATE_DRAGGING）表示recyclerview正在被拖拽
                //2（SCROLL_STATE_SETTLING）表示recyclerview正在惯性下滚动
                Utlis.showLog("newState=" + newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mScrollValue += dy
                Utlis.showLog("dy=" + dy + "mScrollValue=" + mScrollValue)
                if (mScrollValue > 200) {
                    mToolbar.visibility = View.VISIBLE;
                } else {
                    mToolbar.visibility = View.GONE;
                }
            }

        })
        var mList = ArrayList<Int>()
        for (i: Int in 1..99) {
            mList.add(i)
        }
        var mAdapter = HomeAdapter(this.context, mList)
        mAdapter.setBannerItemSize(1)
        mRv.layoutManager = LinearLayoutManager(this.context)
        mRv.adapter = mAdapter
    }

}