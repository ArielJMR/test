package com.eldar.wallet.ui.home.data

import com.eldar.wallet.core.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeService: HomeService) {
    suspend fun getUserInformation(): Flow<User> {
        return homeService.getUserInformation()
    }
}