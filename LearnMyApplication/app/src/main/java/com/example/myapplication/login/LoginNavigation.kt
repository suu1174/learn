package com.example.myapplication.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.myapplication.NavName
import com.example.myapplication.register.RegisterViewModel
import com.example.myapplication.register.RegisterWeekly
import kotlinx.coroutines.delay

private val screen get() = NavName.LoginScreen

@Composable
fun NavGraphBuilder.LoginScreenNavigation(navController: NavHostController) {

    val vm: LoginViewModel = viewModel()
    ProcessLifecycleOwner.get().lifecycle.addObserver(vm)
    vm.setNavController(navController)
    LaunchedEffect(vm, block = {
        delay(500L)
    })
    LoginWeekly(viewModel = vm,navController, onBackClick = {
        navController.popBackStack()
    })
}