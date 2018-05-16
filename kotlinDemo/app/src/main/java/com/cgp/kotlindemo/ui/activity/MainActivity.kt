package com.cgp.kotlindemo.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.cgp.kotlindemo.R
import com.cgp.kotlindemo.mvp.bean.TabEntity
import com.cgp.kotlindemo.ui.fragment.FoundFragment
import com.cgp.kotlindemo.ui.fragment.HomeFragment
import com.cgp.kotlindemo.ui.fragment.HotFragment
import com.cgp.kotlindemo.ui.fragment.MineFragment
import com.cgp.kotlindemo.utlis.Utlis
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    //导航标题集合
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标,int集合
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    //tab集合
    private val mTabEntities = ArrayList<CustomTabEntity>()
    //Fragment对象
    var mHomeFragment: HomeFragment? = null
    var mFoundFragment: FoundFragment? = null
    var mHotFragment: HotFragment? = null
    var mMineFragment: MineFragment? = null

    //默认为0
    private var mIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utlis.showToast(this, "我到主页了")
        initTab()
        initFragmnet()
        tab_layout.currentTab = mIndex
    }

    /**
     * 初始化fragment
     */
    private fun initFragmnet() {
        //fragment事物
        val transaction = supportFragmentManager.beginTransaction()
        //mHomeFragment ?: HomeFragment()
        mHomeFragment?.let {
            //不等于null才执行transaction.show(it)，等于null就执行?: HomeFragment.getInstance(mTitles[0])也就是创建对象
            //返回最后一句语句块，也就是返回transaction
            //it表示引用对象，也就是表示mHomeFragment
            //把it添加到事物
            transaction.show(it)
        } ?: HomeFragment.getInstance(mTitles[0]).let {
            mHomeFragment = it
            transaction.add(R.id.fl_container, mHomeFragment, "home")
        }

        mFoundFragment = FoundFragment()
        transaction.add(R.id.fl_container, mFoundFragment, "found")

        mHotFragment = HotFragment()
        transaction.add(R.id.fl_container, mHotFragment, "hot")

        mMineFragment = MineFragment()
        transaction.add(R.id.fl_container, mMineFragment, "mine")

        //隐藏其他显示主页
        hideAllFragment(transaction)
        transaction.show(mHomeFragment)
        //提交事物
        transaction.commitAllowingStateLoss()
    }


    private fun initTab() {
        //创建四个tab对象，并且存到tab集合中
        for (index in mTitles.indices) {
            val tab = TabEntity(mTitles[index], mIconSelectIds[index], mIconUnSelectIds[index])
            mTabEntities.add(tab)
        }
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                Utlis.showLog("onTabSelect" + position)
                val transaction = supportFragmentManager.beginTransaction()
                //先隐藏所有fragment
                hideAllFragment(transaction)

                when (position) {
                    0 -> mHomeFragment?.let {
                        transaction.show(it)
                    }
                    1 -> mFoundFragment?.let {
                        transaction.show(it)
                    }
                    2 -> mHotFragment?.let {
                        transaction.show(it)
                    }
                    3 -> mMineFragment?.let {
                        transaction.show(it)
                    }
                }
                transaction.commitAllowingStateLoss()
                mIndex = position
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    private fun hideAllFragment(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mFoundFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (tab_layout != null) {
            //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
            outState?.putInt("currTabIndex", mIndex)
        }
    }

    private var mExitTime: Long = 0
    override fun onBackPressed() {

        if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
            finish()
        } else {
            mExitTime = System.currentTimeMillis()
           Utlis.showToast("再按一次退出程序！")
        }

    }
}
