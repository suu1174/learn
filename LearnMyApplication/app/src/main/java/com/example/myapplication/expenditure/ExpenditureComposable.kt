package com.example.myapplication.expenditure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.NavName
import com.example.myapplication.login.Login
import com.example.myapplication.login.LoginViewModel

@Composable
fun ExpenditureWeekly(
    viewModel: ExpenditureViewModel, navController: NavHostController,
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
    Expenditure(navController)
}

@Composable
fun Expenditure(navController: NavController) {
    Column(modifier = Modifier.size(width = 100.dp, height = 100.dp),


        ) {

        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {

            Row {
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
                Text("収支", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(590.dp))

            MyScreen2(navController)


        }
    }
}

@Composable
fun MyScreen2(navController: NavController) {
    var selectedTabIndex by remember { mutableStateOf(1) }

    Surface(color = Color.White) {
        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = Color.Red,
                containerColor = Color.Yellow,
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
                        navController.navigate(NavName.LoginAfterScreen.name)},
                    text = { Text("入出金明細") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1
                    },
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