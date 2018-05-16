package com.cgp.kotlindemo.utlis

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.cgp.kotlindemo.MyApplication

/**
 * Created by ty on 2018/4/27.
 */
class Utlis {
    companion object {
        var toast: Toast? = null
        val TAG = "测试"

        /**
         * 单例土司
         */
        fun showToast(context: Context, msg: String) {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            }else {
                toast?.setText(msg)
            }
            toast?.show()
        }
        /**
         * 单例土司
         */
        fun showToast(msg: String) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.context, msg, Toast.LENGTH_SHORT)
            }else {
                toast?.setText(msg)
            }
            toast?.show()
        }

        fun showLog(msg: String) {
            Log.i(TAG, msg)
        }
    }


}