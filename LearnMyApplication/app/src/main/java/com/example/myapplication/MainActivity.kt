package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.DatabaseUse
import com.example.myapplication.editing.EditingScreenNavigation
import com.example.myapplication.expenditure.ExpenditureScreenNavigation
import com.example.myapplication.login.Login
import com.example.myapplication.login.LoginScreenNavigation
import com.example.myapplication.loginafter.LoginAfter
import com.example.myapplication.loginafter.LoginAfterScreenNavigation
import com.example.myapplication.register.RegisterScreenNavigation
import com.example.myapplication.top.TopScreenNavigation
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Calendar

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
                    AppNavigation()
                }
            }
        }


        DatabaseUse.database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

    }
}

/*
* 画面間遷移を行うための設定
*
*
* */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavName.TopScreen.name) {

        composable(NavName.TopScreen.name) {
            //Greeting(navController)
            TopScreenNavigation(navController)
        }
        composable(NavName.LoginScreen.name) {
            LoginScreenNavigation(navController)
        }
        composable(NavName.RegisterScreen.name) {
            //FirstScreen(navController)
            RegisterScreenNavigation(navController)
        }
        composable(NavName.LoginAfterScreen.name) {
            LoginAfterScreenNavigation(navController)
        }
        composable(NavName.ExpenditureScreen.name) {
            ExpenditureScreenNavigation(navController)
        }
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