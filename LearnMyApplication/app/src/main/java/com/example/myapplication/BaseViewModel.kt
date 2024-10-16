package com.example.myapplication

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    private var isFirstOnCreate: Boolean = false
    private lateinit var navHostController: NavHostController


    fun setNavController(navHostController: NavHostController) {
        this.navHostController = navHostController
    }





    override fun onCreate(owner: LifecycleOwner) {
        Log.d("Debug", "${this.javaClass.simpleName}::onViewModelCreate")

        //初期表示
        if (!isFirstOnCreate) {
            initView()
            //特定の画面のみ
            if (this.javaClass.simpleName.contains("RegisterModalViewModel")) {
                isFirstOnCreate = true
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d("Debug", "${this.javaClass.simpleName}::onViewModelStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d("Debug", "${this.javaClass.simpleName}::onViewModelResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d("Debug", "${this.javaClass.simpleName}::onViewModelPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d("Debug", "${this.javaClass.simpleName}::onViewModelStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d("Debug", "${this.javaClass.simpleName}::onViewModelDestroy")
    }

    open fun initView() {
    }

}

object RegisterData {
    var mail: String? = ""
    var password: String? = ""
}