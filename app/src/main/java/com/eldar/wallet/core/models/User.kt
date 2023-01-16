package com.eldar.wallet.core.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("success") val success: Boolean,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("balance") val balance: Number,
    @SerializedName("cards") val cards: List<Card>,
)