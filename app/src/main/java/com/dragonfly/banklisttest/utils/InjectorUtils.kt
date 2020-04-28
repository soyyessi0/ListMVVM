package com.dragonfly.banklisttest.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dragonfly.banklisttest.repository.BankRepository

class InjectorUtils(private val context: Context, private val url: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BankRepository::class.java)
            .newInstance(BankRepository(context, url))
    }
}
