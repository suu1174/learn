package com.example.myapplication.loginafter

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BaseViewModel
import com.example.myapplication.db.DataCallDao
import com.example.myapplication.db.DataCallEntity
import com.example.myapplication.db.DatabaseUse
import com.example.myapplication.editing.DataKeep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LoginAfterViewModel : BaseViewModel()  {

    private val dao = DatabaseUse.database.dataCallDao()

     var loadSaveData: List<DataCallEntity> = listOf()

    var searchYear = 0
    var searchMonth = 0

    //flagがtrueの時は検索データを表示
    private val keepSearchButtonFlag : MutableState<Boolean> = mutableStateOf(false)

    val isButtonEnabledState = derivedStateOf { keepSearchButtonFlag }

    private val tabReceive :  MutableState<Boolean> = mutableStateOf(false)
    val tabFlag = derivedStateOf { tabReceive }

    var initViewSearchFlag = false


    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
        viewModelScope.launch(Dispatchers.IO) {
            //val dao = DatabaseUse.database.dataCallDao()
            loadSaveData = dao.loadAllDataCall()
            DataKeep.dataRecodeSize = loadSaveData.size
            DataKeep.dataRecodeAllSize = loadSaveData.size

            val now = LocalDateTime.now()
            val df = DateTimeFormatter.ofPattern("yyyy")
            val dfa = DateTimeFormatter.ofPattern("MM")
            searchYear = df.format(now).toIntOrNull()!!
            searchMonth = dfa.format(now).toIntOrNull()!!
            searchDataYearMonthDB()
            initViewSearchFlag = false

        }
    }

    fun searchButtonFlagSet(flag: Boolean) {
        keepSearchButtonFlag.value = flag
    }

    fun tabFlagSet(flag: Boolean) {
        tabReceive.value = flag
    }

    fun searchDataYearMonthDB() {
        viewModelScope.launch(Dispatchers.IO) {
            loadSaveData = dao.loadYearMonthDataCall(searchYear, searchMonth)
            DataKeep.dataRecodeSize = loadSaveData.size
        }
    }



}