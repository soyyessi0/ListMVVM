package com.dragonfly.banklisttest.repository.rest

import com.dragonfly.banklisttest.model.Bank
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IBankWebService {
    @GET
    fun getBankList(@Url url: String): Call<List<Bank>>
}