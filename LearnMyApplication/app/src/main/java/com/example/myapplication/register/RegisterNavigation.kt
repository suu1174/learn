package com.example.myapplication.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.myapplication.NavName
import kotlinx.coroutines.delay

private val screen get() = NavName.RegisterScreen

@Composable
fun NavGraphBuilder.RegisterScreenNavigation(navController: NavHostController) {

    val vm: RegisterViewModel = viewModel()
    ProcessLifecycleOwner.get().lifecycle.addObserver(vm)
    vm.setNavController(navController)
    LaunchedEffect(vm, block = {
        delay(500L)
    })
    RegisterWeekly(viewModel = vm,navController, onBackClick = {
        navController.popBackStack()
    })
}




fun NavHostController.navigateCallRegisterScreen() {
    navigate(screen.route)
}
