package com.eldar.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eldar.wallet.core.router.Navigator
import com.eldar.wallet.core.router.Routes
import com.eldar.wallet.ui.generate_payment.ui.GeneratePaymentScreen
import com.eldar.wallet.ui.generate_payment.ui.GeneratePaymentViewModel
import com.eldar.wallet.ui.generate_qr.ui.GenerateQrScreen
import com.eldar.wallet.ui.generate_qr.ui.GenerateQrViewModel
import com.eldar.wallet.ui.home.ui.HomeScreen
import com.eldar.wallet.ui.home.ui.HomeViewModel
import com.eldar.wallet.ui.login.ui.LoginScreen
import com.eldar.wallet.ui.login.ui.LoginViewModel
import com.eldar.wallet.ui.new_card.ui.NewCardScreen
import com.eldar.wallet.ui.new_card.ui.NewCardViewModel
import com.eldar.wallet.ui.theme.WalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val generateQrViewModel: GenerateQrViewModel by viewModels()
    private val generatePaymentViewModel: GeneratePaymentViewModel by viewModels()
    private val newCardViewModel: NewCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ) {
                    val navigationController = rememberNavController()
                    Navigator.setController(navigationController);

                    NavHost(navController = navigationController, startDestination = Routes.Login.route) {
                        composable(Routes.Login.route) {
                            LoginScreen(loginViewModel)
                        }

                        composable(Routes.Home.route) {
                            HomeScreen(homeViewModel)
                        }

                        composable(Routes.GenerateQR.route) {
                            GenerateQrScreen(generateQrViewModel)
                        }

                        composable(Routes.GeneratePayment.route) {
                            GeneratePaymentScreen(generatePaymentViewModel)
                        }

                        composable(Routes.AddNewCard.route) {
                            NewCardScreen(newCardViewModel)
                        }
                    }
                }
            }
        }
    }
}