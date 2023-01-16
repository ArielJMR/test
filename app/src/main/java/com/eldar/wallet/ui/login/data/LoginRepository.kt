package com.eldar.wallet.ui.login.data

import com.eldar.wallet.ui.login.data.network.LoginNetworkService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginNetworkService: LoginNetworkService){
    suspend fun doLogin(user:String, password:String):Boolean{
        return loginNetworkService.doLogin(user, password)
    }
}