package com.dragonfly.banklisttest.repository

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dragonfly.banklisttest.model.Bank
import com.dragonfly.banklisttest.repository.rest.IBankWebService
import com.dragonfly.banklisttest.repository.room.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread


class BankRepository(private val context: Context, private val url: String) {

    private val request = Retrofit.Builder()
        .baseUrl("https://api.jsonbin.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bankListLiveData = MutableLiveData<List<Bank>>()
    val error = MutableLiveData<Int>()

    private fun callBankServiceWeb() {
        request?.create(IBankWebService::class.java)?.getBankList(url)?.enqueue(
            object : Callback<List<Bank>?> {
                override fun onResponse(call: Call<List<Bank>?>, response: Response<List<Bank>?>) {
                    response.body()?.let { saveAllInfoDB(it) }
                    bankListLiveData.value = response.body()
                }

                override fun onFailure(call: Call<List<Bank>?>, t: Throwable) {
                    Log.e(
                        "BankRepository",
                        "Ocurrió un error al obtener la información del servidor",
                        t
                    )
                    error.postValue(1)
                }
            })
    }

    fun getAllInfoDB() {
        val handler = Handler()
        thread {
            val database = AppDatabase.getInstance(context)

            val bankDao = database.bankDao()
            val infoDB = bankDao.getAllBanks()

            handler.post {
                if (infoDB.isNullOrEmpty()) {
                    callBankServiceWeb()
                } else
                    bankListLiveData.value = infoDB
            }

            database.close()
        }
    }

    fun saveAllInfoDB(banks: List<Bank>) {
        val handler = Handler()
        thread {
            val database = AppDatabase.getInstance(context)
            val bankDao = database.bankDao()

            bankDao.insertAll(banks)
            handler.post {
                Log.i("BankRepository", "Información guardada en BD")
            }

            database.close()
        }
    }
}