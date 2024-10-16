package com.example.myapplication.register

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.RegisterData
import java.util.Calendar

@Composable
fun RegisterWeekly(
    viewModel: RegisterViewModel, navController: NavHostController,
    onBackClick: () -> Unit
) {
    Register(navController)
}

//各viewの配置
@Composable
fun Register(navController: NavController) {
    Column(modifier = Modifier.size(width = 100.dp, height = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //縦スクロール設定
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("メールアドレス", modifier = Modifier.padding(end = 175.dp, top = 25.dp))
            TextFieldMailRegister()
            Text("パスワード", modifier = Modifier.padding(end = 195.dp, top = 25.dp))
            TextFieldPasswordRegister()
            Text("生年月日", modifier = Modifier.padding(end = 195.dp, top = 25.dp))

            DatePickerExample()
            CheckboxOne()

            Spacer(modifier = Modifier.height(200.dp))

            Button(
                shape = CutCornerShape(percent = 10),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                onClick = {

                    navController.popBackStack()
                    /*navController.navigate(NavName.LoginAfterScreen.name)
                    val ins = Certification()
                    val vm = AppViewModel()
                    var mail: String = "aaa"
                    var password: String = "bbb"

                    RegisterData.mail?.let {
                        mail = it
                    }
                    RegisterData.password?.let {
                        password = it
                    }
                    ins.createAccount(mail, password) */
                },
                ) {
                Text("登録")
            }
        }
    }
}

//チェックボックス
@Composable
fun CheckboxOne() {

    var checkedState by remember { mutableStateOf(false) }
    Row(modifier = Modifier.padding(16.dp)) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checked ->
                checkedState = checked
            }
        )
        Text(
            text = "利用規約に同意します",
            modifier = Modifier.padding(start = 8.dp, top = 13.dp)
        )
    }
}

//ピッカー
@Composable
fun DatePickerExample() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var str by remember { mutableStateOf("___________________")}


    Row(modifier = Modifier.padding(16.dp)) {
        Text(str, modifier = Modifier.padding(start = 8.dp, top = 25.dp))

        Button(
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = {
                DatePickerDialog(
                    context,
                    { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                        // ここに選択された日付に対する処理を書く
                        str = selectedYear.toString() + "年" + (selectedMonth + 1 ).toString() + "月"+ selectedDay.toString() +"日"
                    },
                    year,
                    month,
                    day
                ).show()
            }) {
            Text("日付を選択")
        }
    }
}

//メールを入力するためのテキストフィールド
@Composable
fun TextFieldMailRegister() {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text, onValueChange = { newText ->
            text = newText

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

            RegisterData.mail = text
            keyboardController?.hide()
        }), label = { Text("入力ボックス")}

    )
}

//パスワードを入力するためのテキストフィールド
@Composable
fun TextFieldPasswordRegister() {
    var text by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text, onValueChange = { newText ->
            text = newText
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

            RegisterData.password = text
            keyboardController?.hide()
        }),
        label = { Text("入力ボックス")}
    )
}





