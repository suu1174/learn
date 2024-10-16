package com.example.myapplication.expenditure

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.myapplication.login.LoginViewModel
import com.example.myapplication.login.LoginWeekly
import kotlinx.coroutines.delay

@Composable
fun NavGraphBuilder.ExpenditureScreenNavigation(navController: NavHostController) {

    val vm: ExpenditureViewModel = viewModel()
    ProcessLifecycleOwner.get().lifecycle.addObserver(vm)
    vm.setNavController(navController)
    LaunchedEffect(vm, block = {
        delay(500L)
    })
    ExpenditureWeekly(viewModel = vm,navController, onBackClick = {
        navController.popBackStack()
    })
}