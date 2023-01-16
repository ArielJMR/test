package com.eldar.wallet.core.models

import com.google.gson.annotations.SerializedName

data class Card (
    @SerializedName("type") val type: String,
    @SerializedName("owner_name") val ownerName: String,
    @SerializedName("number") val number: String,
    @SerializedName("cvv") val cvv: String,
    @SerializedName("expiration") val expiration: String,
)

