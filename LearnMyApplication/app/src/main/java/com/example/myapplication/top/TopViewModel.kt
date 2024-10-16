package com.example.myapplication.top

import android.util.Log
import com.example.myapplication.BaseViewModel

class TopViewModel : BaseViewModel() {
    //ユーザー登録かログインか選択
    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
    }
}