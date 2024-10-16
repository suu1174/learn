package com.example.myapplication.login

import android.util.Log
import com.example.myapplication.BaseViewModel

class LoginViewModel : BaseViewModel() {
    //ログイン画面
    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
    }

}