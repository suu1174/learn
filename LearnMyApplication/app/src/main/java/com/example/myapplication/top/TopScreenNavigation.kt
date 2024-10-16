package com.example.myapplication.top

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
fun NavGraphBuilder.TopScreenNavigation(navController: NavHostController) {

    val vm: TopViewModel = viewModel()
    ProcessLifecycleOwner.get().lifecycle.addObserver(vm)
    vm.setNavController(navController)
    LaunchedEffect(vm, block = {
        delay(500L)
    })
    TopWeekly(navController)
}





