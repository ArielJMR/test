package com.eldar.wallet.core.router

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Home: Routes("home")
    object GenerateQR: Routes("generate_qr")
    object GeneratePayment: Routes("generate_payment")
    object AddNewCard: Routes("add_new_card")
}