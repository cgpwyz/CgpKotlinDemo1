package com.cgp.kotlindemo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cgp.kotlindemo.R

/**
 * 发现
 * Created by ty on 2018/4/27.
 */
class FoundFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_found,null)
    }
}