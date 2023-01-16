package com.eldar.wallet.ui.generate_qr.data.network.response

import com.google.gson.annotations.SerializedName

data class GenerateQrResponse(
    @SerializedName("url") val url: String,
)