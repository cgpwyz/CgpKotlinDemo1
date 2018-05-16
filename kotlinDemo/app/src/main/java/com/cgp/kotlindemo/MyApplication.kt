package com.cgp.kotlindemo

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by ty on 2018/4/24.
 */
class MyApplication : Application() {

    //静态类
    companion object {
        //静态变量
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}