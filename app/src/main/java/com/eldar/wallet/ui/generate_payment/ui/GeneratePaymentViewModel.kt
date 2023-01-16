package com.eldar.wallet.ui.generate_payment.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.eldar.wallet.core.models.Card
import com.eldar.wallet.core.router.Navigator
import com.eldar.wallet.core.router.Routes
import com.eldar.wallet.ui.generate_payment.domain.GeneratePaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratePaymentViewModel @Inject constructor(private val generatePaymentUseCase: GeneratePaymentUseCase) : ViewModel() {
    private val amountPayment = 200;

    private val _amountUser = MutableLiveData<Number>()
    private val amountUser: LiveData<Number> = _amountUser;

    private val _numberCardSelected = MutableLiveData<String>()
    val numberCardSelected: LiveData<String> = _numberCardSelected;

    private val _generatePaymentIsEnabled = MutableLiveData<Boolean>();
    val generatePaymentIsEnabled: LiveData<Boolean> = _generatePaymentIsEnabled;

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> = _cards;

    private val navigationController: NavHostController = Navigator.getController();

    fun getInformationUser() {
        viewModelScope.launch {
            generatePaymentUseCase.getUserInformation().collect {
                _amountUser.value = it.balance
                _cards.clear()
                _cards.addAll(it.cards);
            }
        }
    }

    fun onPaymentFormChanged(numberCardSelected: String) {
        _numberCardSelected.value = numberCardSelected;
        _generatePaymentIsEnabled.value = numberCardSelected != "" && amountUser.value?.toInt() ?: 0 >= amountPayment;
    }

    fun onSelectedGeneratePayment() {
        val payment: Number = (_amountUser.value?.toInt() ?: 0) - 200
        viewModelScope.launch {
            generatePaymentUseCase.generatePayment(payment);
            goBack()
        }
    }

    fun goBack() {
        navigationController.navigate(Routes.Home.route)
    }
}