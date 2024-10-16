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
    viewModel: TopViewModel, navController: NavHostController,
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
    Top(navController)
}

@Composable
fun Top(navController: NavController) {

    Column(modifier = Modifier.size(width = 100.dp, height = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

        Spacer(modifier = Modifier.height(300.dp))
        Button(
            //modifier = Modifier.fillMaxHeight(),
            //modifier = Modifier.size(width = 10.dp, height = 10.dp),
            modifier = Modifier.width(150.dp),
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = {
                //
                navController.navigate(NavName.RegisterScreen.name)
            },) {
            Text("ユーザー登録")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Spacer(modifier = Modifier.width(30.dp))

        Button(
            //modifier = Modifier.size(width = 10.dp, height = 10.dp),
            modifier = Modifier.width(150.dp),
            shape = CutCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = {
                navController.navigate(NavName.LoginScreen.name)
            },

            ) {
            Text("ログイン")
        }
    }
}


