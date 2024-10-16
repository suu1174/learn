package com.example.myapplication.editing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun NavGraphBuilder.EditingScreenNavigation(navController: NavHostController) {

    val vm: EditingViewModel = viewModel()
    ProcessLifecycleOwner.get().lifecycle.addObserver(vm)
    vm.setNavController(navController)
    LaunchedEffect(vm, block = {
        delay(500L)
    })
    EditingWeekly(viewModel = vm,navController, onBackClick = {
        navController.popBackStack()
    })
}