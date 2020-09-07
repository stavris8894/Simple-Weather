package com.example.simpleweatherapplication.utils

import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricConstants
import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class BiometricHelper {

    fun authenticateWithBiometric(fragmentActivity: FragmentActivity, biometricDetails: BiometricDetails, authenticated: (check: Boolean) -> Unit) {
        val executor = ContextCompat.getMainExecutor(fragmentActivity)
        val biometricPrompt = BiometricPrompt(fragmentActivity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                Log.d(TAG, "onAuthenticationError errorCode: $errorCode with errString: $errString")
                super.onAuthenticationError(errorCode, errString)
                when (errorCode) {
                    BiometricConstants.ERROR_USER_CANCELED, BiometricConstants.ERROR_LOCKOUT -> {
                        Toast.makeText(fragmentActivity, biometricDetails.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {

                    }
                }
                authenticated(false)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                authenticated(true)
            }

            override fun onAuthenticationFailed() {
                Log.d(TAG, "onAuthenticationFailed")
                super.onAuthenticationFailed()
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(biometricDetails.title)
            .setSubtitle(biometricDetails.details)
            .setNegativeButtonText("Cancel")
            .setAllowedAuthenticators(Authenticators.BIOMETRIC_STRONG )
            .build()
        biometricPrompt.authenticate(promptInfo)
    }

    companion object {
        private val TAG = BiometricHelper::class.java.simpleName
    }
}

data class BiometricDetails(
    val title: String,
    val details: String,
    val error: String
)