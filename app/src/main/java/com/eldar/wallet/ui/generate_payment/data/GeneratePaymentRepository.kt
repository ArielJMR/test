package com.eldar.wallet.ui.generate_payment.data

import androidx.datastore.preferences.core.Preferences
import com.eldar.wallet.core.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeneratePaymentRepository @Inject constructor(private val generatePaymentService: GeneratePaymentService) {
    suspend fun getUserInformation(): Flow<User> {
        return generatePaymentService.getUserInformation()
    }

    suspend fun generatePayment(balance: String): Preferences {
        return generatePaymentService.generatePayment(balance)
    }
}