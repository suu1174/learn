package com.example.myapplication.login

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.Certification
import com.example.myapplication.NavName
import com.example.myapplication.RegisterData

@Composable
fun LoginWeekly(
    viewModel: LoginViewModel, navController: NavHostController,
    onBackClick: () -> Unit
) {
    //ライフサイクルをViewModelに紐づける
    /*viewModel.ObserveLifeCycleEvent()
    viewModel.
    Scaffold(
        topBar = { BaseBackButton(titleRes = null, onBackClick = onBackClick) },
        containerColor = AppColor.Screen_Background_White,
        contentColor = AppColor.Text_Black
    ) { paddingValues ->
        K04M01_GraphScreen(
            modifier = Modifier.padding(paddingValues),
            viewModel = viewModel
        )
    } */
    Login(navController)
}

@Composable
fun Login(navController: NavController) {



    Column(modifier = Modifier.size(width = 100.dp, height = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("メールアドレス",modifier = Modifier.padding(end = 175.dp, top = 25.dp))


            TextFieldPasswordLogin()
            Text("パスワード", modifier = Modifier.padding(end = 195.dp, top = 25.dp))
            TextFieldMailLogin()

            Spacer(modifier = Modifier.height(300.dp))


            Button(
                //modifier = Modifier.size(width = 10.dp, height = 10.dp),
                shape = CutCornerShape(percent = 10),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                onClick = {
                    navController.navigate(NavName.LoginAfterScreen.name)

                    val ins = Certification()
                    var mail: String = "aaa"
                    var password: String = "bbb"
                    RegisterData.mail?.let {
                        mail = it
                    }
                    RegisterData.password?.let {
                        password = it
                    }



                    ins.signIn(mail, password)
                },

                ) {
                Text("ログイン")
            }
        }
    }
}

@Composable
fun TextFieldPasswordLogin() {
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

@Composable
fun TextFieldMailLogin() {
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
