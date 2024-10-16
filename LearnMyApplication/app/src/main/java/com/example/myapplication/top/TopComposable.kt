package com.example.myapplication.top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.NavName

@Composable
fun TopWeekly(
    navController: NavHostController
) {
    Top(navController)
}

@Composable
fun Top(navController: NavController) {

    Column(modifier = Modifier.size(width = 100.dp, height = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(300.dp))

        //ユーザー登録ボタンの設定
        Button(
            modifier = Modifier.width(150.dp),
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = {
                //ユーザー登録画面へ遷移
                navController.navigate(NavName.RegisterScreen.name)
            },) {
            Text("ユーザー登録")
        }
        Spacer(modifier = Modifier.height(30.dp))

        //ログインボタンの設定
        Button(
            modifier = Modifier.width(150.dp),
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = {
                //ログイン画面へ遷移
                navController.navigate(NavName.LoginScreen.name)
            },

            ) {
            Text("ログイン")
        }
    }
}


