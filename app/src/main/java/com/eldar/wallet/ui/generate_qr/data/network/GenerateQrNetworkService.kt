package com.eldar.wallet.ui.generate_qr.data.network

import android.util.Log
import com.eldar.wallet.ui.login.data.network.LoginNetworkClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenerateQrNetworkService @Inject constructor(private val generateQrClient: LoginNetworkClient){

    suspend fun getQR(): String {
        return withContext(Dispatchers.IO) {
            val response = generateQrClient.getQR()
            val body = response.body();
            if (body != null) {
                Log.i("QRRRRRRRRR", body.url)
            }

            body?.url ?: "";
        }
    }

}