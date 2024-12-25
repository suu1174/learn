package com.example.myapplication.loginafter

import android.os.Message
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.NavName
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.myapplication.R
import com.example.myapplication.RegisterData
import com.example.myapplication.editing.DataKeep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.text.style.TextAlign
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun LoginAfterWeekly(
    viewModel: LoginAfterViewModel, navController: NavHostController,
    onBackClick: () -> Unit
) {
    LoginAfter(navController ,viewModel)
}

//各viewの配置
@Composable
fun LoginAfter(navController: NavController , viewModel: LoginAfterViewModel) {


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(255, 236, 179)))

    val isTabFlag by viewModel.tabFlag


    val isSearchEnabled by viewModel.isButtonEnabledState
    Column(modifier = Modifier.size(width = 100.dp, height = 100.dp),horizontalAlignment = Alignment.CenterHorizontally


        ) {

        Box(modifier = Modifier
            .size(width = 400.dp, height = 50.dp)
            .fillMaxSize()
            .background(Color(255, 255, 255)),) {
            Row() {
                Button(
                    //modifier = Modifier.size(width = 10.dp, height = 10.dp),
                    shape = CutCornerShape(percent = 10),
                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
                    onClick = {
                        navController.navigate(NavName.TopScreen.name)
                    },

                    ) {
                    Text("ログアウト", color = Color.Blue)
                }
                Spacer(modifier = Modifier.width(20.dp))

                if (!isTabFlag.value) {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("入出金明細", color = Color.Black,textAlign = TextAlign.Center)
                    }

                } else {
                    Spacer(modifier = Modifier.width(20.dp))

                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("収支", color = Color.Black,textAlign = TextAlign.Center)
                    }
                }

                Spacer(modifier = Modifier.width(35.dp))

                Box() {
                    Button(
                        modifier = Modifier.background(
                            color = Color(0xFFE0E0E0)),
                        shape = CutCornerShape(percent = 10),
                        colors = ButtonDefaults.buttonColors(Color.Unspecified),
                        onClick = {

                            DataKeep.intId = 0
                            DataKeep.strDate = "kari"
                            DataKeep.intCategory = 0
                            DataKeep.strFree = ""
                            DataKeep.intPlusMinus = 2
                            DataKeep.intMoney = 0
                            navController.navigate(NavName.EditingScreen.name)

                        },

                        ) {
                        Text("収支新規登録", color = Color.Red)
                    }

                    Icon(Icons.Filled.Add, contentDescription = "お気に入り", modifier = Modifier.background(
                        color = Color(0xFFE0E0E0)).height(48.dp).align(Alignment.CenterEnd))
                }
            }
        }

        if (!isTabFlag.value) {
            //newRegisterData(navController, viewModel)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextFieldYear(viewModel)
                TextFieldMonth(viewModel)
                dataSearch(navController, viewModel)

            }
            Box(modifier = Modifier.size(390.dp)) {

                val scrollState = rememberScrollState()
                Column(modifier = Modifier.verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    //Text("--------------", color = Color.Black)
                    if (isSearchEnabled.value) {
                        MessageList(navController, viewModel)
                    }
                }
            }
        } else {
            Box(modifier = Modifier.size(598.dp))
        }
        Spacer(modifier = Modifier.height(85.dp))
        MyScreen(navController,viewModel)
    }

    if (!viewModel.initViewSearchFlag) {
        viewModel.initViewSearchFlag = true
        viewModel.searchButtonFlagSet(false)
        viewModel.searchButtonFlagSet(true)
    }
}

@Composable
fun MyScreen(navController: NavController, viewModel: LoginAfterViewModel) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Surface(color = Color.White) {
        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = Color.Black,
                containerColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = Color.Black,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0
                        viewModel.tabFlagSet(false)},
                    text = { Text("入出金明細") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1
                        viewModel.tabFlagSet(true)
                        /*navController.navigate(NavName.ExpenditureScreen.name)*/},
                    text = { Text("収支") }
                )
                // Add more tabs as needed
            }

            // Content for each tab
            when (selectedTabIndex) {
                0 -> {
                    // Content for Tab 1
                    Text("Content for Tab 1")
                }
                1 -> {
                    // Content for Tab 2
                    Text("Content for Tab 2")
                }
                // Add more cases for additional tabs
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageList(navController: NavController, viewModel: LoginAfterViewModel) {

    LazyColumn(modifier = Modifier.height(390.dp)){


        if (DataKeep.dataRecodeSize != 0) {
            val makeCount: Int = DataKeep.dataRecodeSize - 1
            (0..makeCount).map {
                item {

                    Button(
                        modifier = Modifier
                            .width(400.dp)
                            .background(color = Color(0xFFFFFFFF)),
                        shape = CutCornerShape(percent = 10),
                        colors = ButtonDefaults.buttonColors(Color.Unspecified),
                        onClick = {

                            if (viewModel.loadSaveData != null &&  viewModel.loadSaveData.size != 0  &&  viewModel.loadSaveData.size > it) {
                                DataKeep.intId = viewModel.loadSaveData[it].id
                                DataKeep.strDate = viewModel.loadSaveData[it].itemDate
                                DataKeep.intCategory = viewModel.loadSaveData[it].category
                                DataKeep.strFree = viewModel.loadSaveData[it].freeText
                                DataKeep.intPlusMinus = viewModel.loadSaveData[it].plusMinus
                                DataKeep.intMoney = viewModel.loadSaveData[it].money
                            }
                            navController.navigate(NavName.EditingScreen.name)

                        },

                        ) {
                        if (viewModel.loadSaveData != null && viewModel.loadSaveData.size != 0 && viewModel.loadSaveData.size > it) {
                            val id = viewModel.loadSaveData[it].id
                            val dateStr = viewModel.loadSaveData[it].itemDate
                            val categoryStr : String
                            if (viewModel.loadSaveData[it].category == 1) {
                                categoryStr = "趣味"
                            } else if (viewModel.loadSaveData[it].category == 2) {
                                categoryStr = "給与"
                            } else {
                                categoryStr = "旅行"
                            }

                            val freeStr = viewModel.loadSaveData[it].freeText
                            val hufou: String = if (viewModel.loadSaveData[it].plusMinus == 1) {
                                "+"
                            } else {
                                "-"
                            }

                            val money = viewModel.loadSaveData[it].money

                            var allStr: String = categoryStr +  "   " + freeStr  +  "   " + hufou  + money.toString() + "円"

                            Text(allStr, color = Color.Blue)
                        } else {
                            Text(it.toString(), color = Color.Blue)
                        }
                    }

                    Divider(
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}

@Composable
fun newRegisterData(navController: NavController, viewModel: LoginAfterViewModel) {

    Box {
        Button(
            modifier = Modifier.background(
                color = Color(0xFFE0E0E0)),
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Unspecified),
            onClick = {

                DataKeep.intId = 0
                DataKeep.strDate = "kari"
                DataKeep.intCategory = 0
                DataKeep.strFree = ""
                DataKeep.intPlusMinus = 2
                DataKeep.intMoney = 0
                navController.navigate(NavName.EditingScreen.name)

            },

            ) {
            Text("収支新規登録", color = Color.Red)
        }

        Icon(Icons.Filled.Add, contentDescription = "お気に入り", modifier = Modifier.background(
            color = Color(0xFFE0E0E0)).height(50.dp))
    }




    Row {
        Text(text = "収支追加")
        IconButton(onClick = {
            DataKeep.intId = 0
            DataKeep.strDate = "kari"
            DataKeep.intCategory = 0
            DataKeep.strFree = ""
            DataKeep.intPlusMinus = 2
            DataKeep.intMoney = 0
            navController.navigate(NavName.EditingScreen.name)
        }) {
            // painterで指定する
            Icon(Icons.Filled.Add, contentDescription = "お気に入り")
        }
    }





}


@Composable
fun TextFieldYear(viewModel: LoginAfterViewModel) {

    val now = LocalDateTime.now()
    val df = DateTimeFormatter.ofPattern("yyyy")
    val fdate = df.format(now)
    var text by remember { mutableStateOf(fdate) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text, onValueChange = { newText ->
            text = newText

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

            viewModel.searchYear = text.toIntOrNull()!!
            keyboardController?.hide()
        }), label = { Text("年")}

    )
}

@Composable
fun TextFieldMonth(viewModel: LoginAfterViewModel) {

    val now = LocalDateTime.now()
    val df = DateTimeFormatter.ofPattern("MM")
    val fdate = df.format(now)
    var text by remember { mutableStateOf(fdate) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text, onValueChange = { newText ->
            text = newText

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

            viewModel.searchMonth = text.toIntOrNull()!!
            keyboardController?.hide()
        }), label = { Text("月")}

    )
}

@Composable
fun dataSearch(navController: NavController, viewModel: LoginAfterViewModel) {
    Button(
        shape = CutCornerShape(percent = 10),
        colors = ButtonDefaults.buttonColors(Color.Blue),
        onClick = {

            DataKeep.intId = 0
            DataKeep.strDate = "kari"
            DataKeep.intCategory = 0
            DataKeep.strFree = ""
            DataKeep.intPlusMinus = 2
            DataKeep.intMoney = 0
            //navController.navigate(NavName.EditingScreen.name)
            viewModel.searchDataYearMonthDB()
            viewModel.searchButtonFlagSet(false)
            viewModel.searchButtonFlagSet(true)

        },

        ) {
        Text("検索", color = Color.White)
    }
}



