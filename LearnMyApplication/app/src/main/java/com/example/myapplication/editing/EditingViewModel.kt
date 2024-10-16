package com.example.myapplication.editing

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BaseViewModel
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.DataCallEntity
import com.example.myapplication.db.DatabaseUse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class EditingViewModel : BaseViewModel()  {
    //収支登録・編集画面

    private val dao = DatabaseUse.database.dataCallDao()
    override fun initView() {
        Log.d("Debug", "${this.javaClass.simpleName}::initView")
    }

    //flagがtrueの時は支出かつラジオボタン選択状態、flagがfalseの場合は収入かつラジオボタン選択状態
    private val keepRatioButtonFlag : MutableState<Boolean> = mutableStateOf(false)

    val isRatioButtonEnabledState = derivedStateOf { keepRatioButtonFlag }

    fun ratioButtonFlagSet(flag: Boolean) {
        keepRatioButtonFlag.value = !flag
    }
    fun saveDataDB() {
        if (keepRatioButtonFlag.value) {
            DataKeep.intPlusMinus = 1
        } else {
            DataKeep.intPlusMinus = 0
        }

        var settingId: Int
        if (DataKeep.intId == 0)
            settingId = DataKeep.dataRecodeAllSize + 1
        else {
            settingId = DataKeep.intId
        }
        viewModelScope.launch(Dispatchers.IO) {
            dao.saveDataCall( DataCallEntity( id = settingId, itemDate = DataKeep.strDate ,category = DataKeep.intCategory,
                freeText = DataKeep.strFree, plusMinus = DataKeep.intPlusMinus,
                money = DataKeep.intMoney, dateYear = DataKeep.dateYear, dateMonth = DataKeep.dateMonth, dateDay = DataKeep.dateDay))
            DataKeep.intId = 0
        }
    }

    fun deleteData() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.dataChooseDelete(DataKeep.intId)
            DataKeep.intId = 0
        }
    }
}
object DataKeep {
    var intId: Int = 0
    var strDate: String = "kari"
    var dateYear: Int = LocalDate.now().year
    var dateMonth: Int = LocalDate.now().month.value
    var dateDay: Int = LocalDate.now().dayOfMonth

    var strFree: String = ""
    var intCategory: Int = 0
    var intPlusMinus: Int = 2
    var intMoney: Int = 0
    var dataRecodeSize = 0
    var dataRecodeAllSize = 0
}