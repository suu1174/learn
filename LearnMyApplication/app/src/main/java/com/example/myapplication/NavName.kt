package com.example.myapplication

enum class NavName(val value: String) {
    TopScreen("topScreen"), //ユーザー登録かログインか選択する画面
    RegisterScreen("registerScreen"), //ユーザー登録する画面
    LoginScreen("loginScreen"), //ログイン画面
    LoginAfterScreen("loginAfterScreen"), //ログイン後に表示する画面
    EditingScreen("editingScreen"), //収支登録・編集画面
    ;
    val route: String
        get() = when (this) {
            TopScreen,
            RegisterScreen,
            LoginScreen,
            LoginAfterScreen,
            EditingScreen
           -> basePath
        }
    private val basePath: String get() = "/$value"
}