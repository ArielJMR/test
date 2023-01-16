package com.eldar.wallet.ui.generate_payment.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.eldar.wallet.core.models.Card
import com.eldar.wallet.core.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GeneratePaymentService @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun getUserInformation(): Flow<User> {
        return withContext(Dispatchers.IO) {
            val gson = Gson()

            return@withContext dataStore.data.map { preferences ->
                User(
                    success = preferences[stringPreferencesKey("success")] === "true",
                    firstName = preferences[stringPreferencesKey("firstName")].orEmpty(),
                    lastName = preferences[stringPreferencesKey("lastName")].orEmpty(),
                    balance = preferences[stringPreferencesKey("balance")]!!.toInt(),
                    cards = gson.fromJson<ArrayList<Card>?>(preferences[stringPreferencesKey("cards")], object : TypeToken<ArrayList<Card>>() {}.type).toList()
                )
            }
        }
    }

    suspend fun generatePayment(balance: String): Preferences {
        return withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey("balance")] = balance;
            }
        }
    }

}