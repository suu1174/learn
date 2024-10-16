package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.DatabaseUse
import com.example.myapplication.editing.EditingScreenNavigation
import com.example.myapplication.login.LoginScreenNavigation
import com.example.myapplication.loginafter.LoginAfterScreenNavigation
import com.example.myapplication.register.RegisterScreenNavigation
import com.example.myapplication.top.TopScreenNavigation
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    appNavigation()
                }
            }
        }
        //DBのオブジェクトを生成
        DatabaseUse.database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}

/*
*画面間遷移を行うための設定
*各画面の設定を行う
* */
@Composable
fun appNavigation() {
    val navController = rememberNavController()
    //初期表示　トップ画面
    NavHost(navController = navController, startDestination = NavName.TopScreen.name) {

        //トップ画面
        composable(NavName.TopScreen.name) {
            TopScreenNavigation(navController)
        }
        //ログイン画面
        composable(NavName.LoginScreen.name) {
            LoginScreenNavigation(navController)
        }
        //ユーザー登録画面
        composable(NavName.RegisterScreen.name) {
            RegisterScreenNavigation(navController)
        }
        //ログイン後画面
        composable(NavName.LoginAfterScreen.name) {
            LoginAfterScreenNavigation(navController)
        }
        //収支編集・登録画面
        composable(NavName.EditingScreen.name) {
            EditingScreenNavigation(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {

    }
}