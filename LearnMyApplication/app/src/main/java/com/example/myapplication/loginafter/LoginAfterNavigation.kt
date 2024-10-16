package com.example.myapplication.loginafter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.myapplication.NavName
import com.example.myapplication.login.LoginViewModel
import com.example.myapplication.login.LoginWeekly
import kotlinx.coroutines.delay

private val screen get() = NavName.LoginScreen

@Composable
fun NavGraphBuilder.LoginAfterScreenNavigation(navController: NavHostController) {

    val vm: LoginAfterViewModel = viewModel()
    ProcessLifecycleOwner.get().lifecycle.addObserver(vm)
    vm.setNavController(navController)
    LaunchedEffect(vm, block = {
        delay(500L)
    })
    LoginAfterWeekly(viewModel = vm,navController, onBackClick = {
        navController.popBackStack()
    })
}