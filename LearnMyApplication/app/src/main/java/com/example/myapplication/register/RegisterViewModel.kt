package com.example.myapplication.register

import android.util.Log
import com.example.myapplication.BaseViewModel

class RegisterViewModel : BaseViewModel() {
    //ユーザー登録する画面
    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
    }
}