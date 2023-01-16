package com.eldar.wallet.ui.generate_payment.domain

import androidx.datastore.preferences.core.Preferences
import com.eldar.wallet.core.models.User
import com.eldar.wallet.ui.generate_payment.data.GeneratePaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeneratePaymentUseCase @Inject constructor(private val repository: GeneratePaymentRepository) {
    suspend fun getUserInformation(): Flow<User> {
        return repository.getUserInformation();
    }

    suspend fun generatePayment(balance: Number): Preferences {
        return repository.generatePayment(balance.toString());
    }
}