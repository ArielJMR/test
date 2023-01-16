package com.eldar.wallet.core.services

import android.util.Log
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object Criptography {
    private val algorithm = "AES";
    private var bytesSecretKey = byteArrayOf();

    fun encrypt(content: String): String {
        val keyGenerator: KeyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(128);
        val secretKey: SecretKey = keyGenerator.generateKey();
        bytesSecretKey = secretKey.encoded;
        val secretKeySpec = SecretKeySpec(bytesSecretKey, algorithm);
        var cipher: Cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        val contentEncrypt = cipher.doFinal(content.toByteArray())

        Log.i("ENCRIPTACION", String(contentEncrypt))

        return String(contentEncrypt);
    }

    fun decrypt(contentEncrypt: ByteArray): String {
        val secretKeySpec = SecretKeySpec(bytesSecretKey, algorithm);
        var cipher: Cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return String(cipher.doFinal(contentEncrypt))
    }
}
