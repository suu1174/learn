package com.example.myapplication.register

import android.util.Log
import com.example.myapplication.BaseViewModel

class RegisterViewModel : BaseViewModel() {


    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
    }


}