package com.example.myapplication.editing

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.NavName
import java.util.Calendar

@Composable
fun EditingWeekly(
    viewModel: EditingViewModel, navController: NavHostController,
    onBackClick: () -> Unit
) {
    Editing(navController, viewModel)
}

//各viewの配置
@Composable
fun Editing(navController: NavController, viewModel: EditingViewModel) {

    val isRatioEnabled by viewModel.isRatioButtonEnabledState

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(255, 236, 179)))

    Column(modifier = Modifier.size(width = 100.dp, height = 100.dp),horizontalAlignment = Alignment.CenterHorizontally

        ) {

        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {

            Box(modifier = Modifier
                .size(width = 400.dp, height = 50.dp)
                .fillMaxSize()
                .background(Color(255, 255, 255)),) {
                Row {
                    Button(
                        shape = CutCornerShape(percent = 10),
                        colors = ButtonDefaults.buttonColors(Color.Unspecified),
                        onClick = {
                            navController.popBackStack()
                        },

                        ) {
                        Text("<", color = Color.Blue, fontSize = 30.sp)
                    }

                    Spacer(modifier = Modifier.width(65.dp))
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("登録・編集", color = Color.Black,textAlign = TextAlign.Center, fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.width(65.dp))
                    Column {
                        Spacer(modifier = Modifier.height(2.dp))
                        Button(
                            shape = CutCornerShape(percent = 10),
                            colors = ButtonDefaults.buttonColors(Color.Unspecified),
                            onClick = {
                                //data delete
                                viewModel.deleteData()
                                //back reload
                                navController.navigate(NavName.LoginAfterScreen.name)
                            },
                            ) {
                            Text("削除", color = Color.Blue, fontSize = 20.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text("収支", color = Color.Black, fontSize = 20.sp)

            Row {
                Spacer(modifier = Modifier.width(30.dp))
                RadioButton(selected = isRatioEnabled.value, onClick = {
                    viewModel.ratioButtonFlagSet(false)
                    DataKeep.intPlusMinus = 2
                /*TODO*/ })

                Column {
                    Spacer(modifier = Modifier.height(7.dp))
                    Text("支出", color = Color.Black, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.width(60.dp))
                RadioButton(selected = !isRatioEnabled.value, onClick = {
                    viewModel.ratioButtonFlagSet(true)
                    DataKeep.intPlusMinus = 1
                    /*TODO*/ })
                Column {
                    Spacer(modifier = Modifier.height(7.dp))
                    Text("収入", color = Color.Black, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("金額", color = Color.Black, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Spacer(modifier = Modifier.width(40.dp))
                TextFieldMoneyEditing()
                Column {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text("円", color = Color.Black, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("カテゴリー", color = Color.Black, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(5.dp))
            Row {
                //CategoryPickerExample()
                Spacer(modifier = Modifier.width(40.dp))
                DropListEditing()
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("内容", color = Color.Black, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Spacer(modifier = Modifier.width(40.dp))
                TextFieldFreeEditing()
            }

            Spacer(modifier = Modifier.height(20.dp))
            DatePickerEditing()

            Spacer(modifier = Modifier.height(80.dp))
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Button(
                    modifier = Modifier.size(width = 300.dp, height = 50.dp),
                    shape = CutCornerShape(percent = 10),
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    onClick = {
                        //データ登録
                        viewModel.saveDataDB()
                        //back reload
                        navController.navigate(NavName.LoginAfterScreen.name)
                    },
                    ) {
                    Text("登録")
                }
            }
        }
    }
}

@Composable
fun TextFieldMoneyEditing() {
    var text by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text, onValueChange = { newText ->
            text = newText

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

            DataKeep.intMoney = text.toIntOrNull() ?: 999999
            //RegisterData.password = text
            keyboardController?.hide()
        }),

        label = { Text("入力ボックス")}
    )
}

@Composable
fun TextFieldFreeEditing() {
    var text by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text, onValueChange = { newText ->
            text = newText

        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

            DataKeep.strFree = text
            keyboardController?.hide()
        }),

        label = { Text("入力ボックス")}
    )
}


@Composable
fun DropListEditing() {

    var str by remember { mutableStateOf("")}








    // メニューの開閉をコントロールするため
    var expanded by remember { mutableStateOf(false) }

    Text(str, modifier = Modifier.padding(start = 8.dp, top = 5.dp).height(40.dp).width(230.dp).background(
        color = Color(0xFFE0E0E0)
    ))

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {

        Button(
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = {
                expanded = true
            }) {
            Text("収支分類")
        }

        DropdownMenu(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                // タップされた時の背景を円にする
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.onSurface),
            expanded = expanded,
            // メニューの外がタップされた時に閉じる
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(text = {
                Text(
                    text = "趣味",
                    fontSize = 24.sp,
                    color = Color.White,
                )/*TODO*/ }, onClick = {
                str = "趣味"
                expanded = false
                DataKeep.intCategory = 1/*TODO*/ })

            DropdownMenuItem(text = {
                Text(
                    text = "給与",
                    fontSize = 24.sp,
                    color = Color.White,
                )

                /*TODO*/ }, onClick = {
                str = "給与"
                expanded = false
                DataKeep.intCategory = 2/*TODO*/ })

            DropdownMenuItem(text = {
                Text(
                    text = "旅行",
                    fontSize = 24.sp,
                    color = Color.White,
                )

                /*TODO*/ }, onClick = {
                str = "旅行"
                expanded = false
                DataKeep.intCategory = 3/*TODO*/ }
            )
        }
    }
}

@Composable
fun DatePickerEditing() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var str by remember { mutableStateOf("")}

    Row(modifier = Modifier.padding(30.dp)) {
        Text(str, modifier = Modifier.padding(start = 8.dp, top = 5.dp).height(40.dp).width(200.dp).background(
            color = Color(0xFFE0E0E0)))

        Button(
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = {
                DatePickerDialog(
                    context,
                    { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                        // ここに選択された日付に対する処理を書く
                        str = selectedYear.toString() + "年" + (selectedMonth + 1 ).toString() + "月"+ selectedDay.toString() +"日"
                        DataKeep.dateYear = selectedYear
                        DataKeep.dateMonth = selectedMonth + 1
                        DataKeep.dateDay = selectedDay
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

