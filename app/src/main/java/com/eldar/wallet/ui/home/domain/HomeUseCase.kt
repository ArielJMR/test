package com.eldar.wallet.ui.home.domain

import com.eldar.wallet.core.models.User
import com.eldar.wallet.ui.home.data.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend fun getUserInformation(): Flow<User> {
        return repository.getUserInformation();
    }
}