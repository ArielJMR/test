package com.eldar.wallet.ui.generate_qr.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldar.wallet.core.router.Navigator
import com.eldar.wallet.core.router.Routes
import com.eldar.wallet.ui.generate_qr.domain.GenerateQrUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateQrViewModel @Inject constructor(private val generateQrUseCase: GenerateQrUseCase) : ViewModel() {
    private val navigatorController = Navigator.getController();

    private val _urlQR = MutableLiveData<String>()
    val urlQR: LiveData<String> = _urlQR;

    fun generateQrScreen() {
        viewModelScope.launch {
            _urlQR.value = generateQrUseCase()
        }
    }

    fun goBack() {
        navigatorController.navigate(Routes.Home.route)
    }
}