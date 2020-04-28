package com.dragonfly.banklisttest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonfly.banklisttest.model.Bank
import com.dragonfly.banklisttest.repository.BankRepository

class BankListViewModel(private val bankRepository: BankRepository) : ViewModel() {

    fun startService() = bankRepository.getAllInfoDB()

    val banks: LiveData<List<Bank>> = bankRepository.bankListLiveData

    val error : LiveData<Int> = bankRepository.error

}