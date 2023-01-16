package com.eldar.wallet.ui.login.data.network

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

class LoginNetworkService @Inject constructor(private val loginClient: LoginNetworkClient, private val dataStore: DataStore<Preferences>){

    suspend fun doLogin(email:String, password:String):Boolean{
        return withContext(Dispatchers.IO){
            val response = loginClient.doLogin()
            val body = response.body();

            if (body != null) {
                if (body.success) {
                    val gson = Gson();
                    val cardsJsonString = gson.toJson(body.cards);
                    dataStore.edit { preferences ->
                        preferences[stringPreferencesKey("firstName")] = body.firstName;
                        preferences[stringPreferencesKey("lastName")] = body.lastName;
                        preferences[stringPreferencesKey("balance")] = body.balance.toString();
                        preferences[stringPreferencesKey("cards")] = cardsJsonString;
                    }
                }
            }

            response.body()?.success ?: false
        }
    }
}