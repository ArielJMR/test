package com.eldar.wallet.ui.new_card.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldar.wallet.R
import com.eldar.wallet.core.models.Card
import com.eldar.wallet.core.router.Navigator
import com.eldar.wallet.core.router.Routes
import com.eldar.wallet.ui.new_card.domain.NewCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCardViewModel @Inject constructor(private val newCardUseCase: NewCardUseCase) :
    ViewModel() {
    private val navigatorController = Navigator.getController();

    private var userName = "";

    private val _number = MutableLiveData<String>()
    val number: LiveData<String> = _number;

    private val _type = MutableLiveData<String>()
    val type: LiveData<String> = _type;

    private val _cards = MutableLiveData<List<Card>>()
    private val cards: LiveData<List<Card>> = _cards;

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name;

    private val _expiration = MutableLiveData<String>()
    val expiration: LiveData<String> = _expiration;

    private val _cvv = MutableLiveData<String>()
    val cvv: LiveData<String> = _cvv;

    private val _addNewCardEnabled = MutableLiveData<Boolean>();
    val addNewCardEnabled: LiveData<Boolean> = _addNewCardEnabled;

    fun getInformationCards() {
        viewModelScope.launch {
            newCardUseCase.getUserInformation().collect {
                userName = it.firstName + " " + it.lastName;
                _cards.value = it.cards;
            }
        }
    }

    fun onAddCardFormChanged(number: String, name: String, expiration: String, cvv: String) {
        _number.value = number;
        _name.value = name;
        _expiration.value = expiration;
        _cvv.value = cvv;

        var isTypeCardValid = false;

        if (number != "") {
            _type.value = getTypeCard(number)
            isTypeCardValid =
                (number[0]) == '3' || (number[0]) == '4' || number[0] == '5'
        }


        val isValidNumber = number.length == 16;
        val isCardNameOfUser = name != "" && name == userName;
        val isCvvValid = cvv != "" && cvv.length == 3;


        _addNewCardEnabled.value =
            isValidNumber && isCardNameOfUser && expiration != "" && isCvvValid && isTypeCardValid;
    }

    private fun getTypeCard(firstNumber: String): String {
        var type = "";

        if (firstNumber != "") {
            when (firstNumber[0]) {
                '3' -> type = "AE"
                '4' -> type = "V"
                '5' -> type = "M"
            }
        }

        return type;
    }

    fun onAddNewCardSelected(newCard: Card) {
        viewModelScope.launch {
            val cards = cards.value;
            if (cards != null) {
                if (cards.isNotEmpty()) {
                    val newArrayOfCards: ArrayList<Card> = ArrayList(cards);
                    newArrayOfCards.add(newCard);
                    newCardUseCase.addNewCard(newArrayOfCards.toList());
                    goToHome()
                }
            }
        }
    }

    private fun goToHome() {
        navigatorController.navigate(Routes.Home.route)
    }

    fun getImageTypeCard(firstNumber: String): Int {
        var imageType = R.drawable.cards;

        if (firstNumber != "") {
            when (firstNumber[0]) {
                '3' -> imageType = R.drawable.american_express
                '4' -> imageType = R.drawable.visa
                '5' -> imageType = R.drawable.mastercard
            }
        }



        return imageType;
    }
}