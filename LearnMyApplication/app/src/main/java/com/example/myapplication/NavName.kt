package com.example.myapplication

enum class NavName(val value: String) {
    TopScreen("topScreen"),
    RegisterScreen("registerScreen"),
    LoginScreen("loginScreen"),
    LoginAfterScreen("loginAfterScreen"),
    ExpenditureScreen("expenditureScreen"),
    EditingScreen("editingScreen")
    ;
    val route: String
        get() = when (this) {
            TopScreen,
            RegisterScreen,
            LoginScreen,
            LoginAfterScreen,
            ExpenditureScreen,
            EditingScreen
           -> basePath
        }
    private val basePath: String get() = "/$value"
}