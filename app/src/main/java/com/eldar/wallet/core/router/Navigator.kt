package com.eldar.wallet.core.router

import androidx.navigation.NavHostController

object Navigator {
    private lateinit var navController: NavHostController

    fun setController(controller: NavHostController) {
        navController = controller
    }

    fun getController(): NavHostController {
        return navController
    }
}