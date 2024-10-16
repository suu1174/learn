package com.example.myapplication.top

import android.util.Log
import com.example.myapplication.BaseViewModel

class TopViewModel : BaseViewModel() {
    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
    }
}