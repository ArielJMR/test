package com.eldar.wallet.ui.generate_qr.data.network

import com.eldar.wallet.ui.generate_qr.data.network.response.GenerateQrResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenerateQrNetworkClient {
    @GET("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=Juan")
    suspend fun getQR(): Response<GenerateQrResponse>
}