package com.eldar.wallet.ui.new_card.data

import androidx.datastore.preferences.core.Preferences
import com.eldar.wallet.core.models.Card
import com.eldar.wallet.core.models.User

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewCardRepository @Inject constructor(private val newCardStorageService: NewCardService){
    suspend fun addNewCard(arrayNewCards: List<Card>): Preferences {
        return newCardStorageService.addCard(arrayNewCards)
    }

    suspend fun getUserInformation(): Flow<User> {
        return newCardStorageService.getUserInformation()
    }
}