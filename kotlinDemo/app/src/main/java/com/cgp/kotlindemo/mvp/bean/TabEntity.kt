package com.cgp.kotlindemo.mvp.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by ty on 2018/4/25.
 */
class TabEntity(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon;
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon;
    }

    override fun getTabTitle(): String {
        return title;
    }
}