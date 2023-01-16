package com.eldar.wallet.ui.generate_qr.data

import com.eldar.wallet.ui.generate_qr.data.network.GenerateQrNetworkService
import javax.inject.Inject

class GenerateQrRepository @Inject constructor(private val generateQrNetworkService: GenerateQrNetworkService) {
    suspend fun getQR(): String {
        return generateQrNetworkService.getQR()
    }
}