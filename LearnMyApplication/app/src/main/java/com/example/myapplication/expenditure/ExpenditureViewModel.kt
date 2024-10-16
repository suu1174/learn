package com.example.myapplication.expenditure

import android.util.Log
import com.example.myapplication.BaseViewModel

class ExpenditureViewModel : BaseViewModel()  {
    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
    }
}