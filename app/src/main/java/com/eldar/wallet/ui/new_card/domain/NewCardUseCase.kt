package com.eldar.wallet.ui.new_card.domain

import androidx.datastore.preferences.core.Preferences
import com.eldar.wallet.core.models.Card
import com.eldar.wallet.core.models.User
import com.eldar.wallet.ui.new_card.data.NewCardRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewCardUseCase @Inject constructor(private val repository: NewCardRepository) {

    suspend fun addNewCard(arrayNewCard: List<Card>): Preferences {
        return repository.addNewCard(arrayNewCard)
    }

    suspend fun getUserInformation(): Flow<User> {
        return repository.getUserInformation()
    }
}