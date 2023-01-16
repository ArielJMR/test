package com.eldar.wallet.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.eldar.wallet.core.router.Navigator
import com.eldar.wallet.core.router.Routes

import com.eldar.wallet.ui.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val navigatorController = Navigator.getController();

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email;

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password;

    private val _loginEnable = MutableLiveData<Boolean>();
    val loginEnable: LiveData<Boolean> = _loginEnable;

    private val _isLoading = MutableLiveData<Boolean>();
    val isLoading: LiveData<Boolean> = _isLoading;

    fun onLoginChanged(email: String, password: String) {
        _email.value = email;
        _password.value = password;
        _loginEnable.value = isValidEmail(email) && isValidPassword(password);
    }

    suspend fun onLoginSelected() {
        _isLoading.value = true;
        viewModelScope.launch {
            val result = loginUseCase(email.value!!, password.value!!)

            if (result) {
                goToHome()
            }

            _isLoading.value = false;
        }
    }

    private fun goToHome() {
        navigatorController.navigate(Routes.Home.route)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches();

    private fun isValidPassword(password: String): Boolean = password.length > 6;

}