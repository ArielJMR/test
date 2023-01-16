package com.eldar.wallet.ui.home.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.eldar.wallet.core.models.Card
import com.eldar.wallet.core.router.Navigator
import com.eldar.wallet.core.router.Routes
import com.eldar.wallet.ui.home.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name;

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> = _cards;

    private val _balance = MutableLiveData<Number>()
    val balance: LiveData<Number> = _balance;

    private val navigationController: NavHostController = Navigator.getController();

    fun getInformationUser() {
        viewModelScope.launch {
            homeUseCase.getUserInformation().collect {
                _cards.clear()
                _cards.addAll(it.cards);
                _name.value = it.firstName + " " + it.lastName;
                _balance.value = it.balance;
            }
        }
    }

    fun goToGenerateQr() {
        navigationController.navigate(Routes.GenerateQR.route)
    }

    fun goToGeneratePayment() {
        navigationController.navigate(Routes.GeneratePayment.route)
    }

    fun goToAddCard() {
        navigationController.navigate(Routes.AddNewCard.route)
    }
}