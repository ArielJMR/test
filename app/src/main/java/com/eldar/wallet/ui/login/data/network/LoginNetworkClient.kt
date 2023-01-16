package com.eldar.wallet.ui.login.data.network

import com.eldar.wallet.core.models.User
import com.eldar.wallet.ui.generate_qr.data.network.response.GenerateQrResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginNetworkClient {
    @GET("v3/c5e1646b-24e7-4f75-a8e9-07914c542188")
    suspend fun doLogin():Response<User>

    @GET("v3/0e95fa8d-e329-4bc7-bad7-325d06f9a20a")
    suspend fun getQR(): Response<GenerateQrResponse>
}