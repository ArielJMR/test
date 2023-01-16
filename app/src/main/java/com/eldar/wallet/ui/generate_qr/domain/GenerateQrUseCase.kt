package com.eldar.wallet.ui.generate_qr.domain

import com.eldar.wallet.ui.generate_qr.data.GenerateQrRepository
import javax.inject.Inject

class GenerateQrUseCase @Inject constructor(private val repository: GenerateQrRepository) {
    suspend operator fun invoke(): String {
        return repository.getQR();
    }
}