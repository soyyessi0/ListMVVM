package com.dragonfly.banklisttest.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dragonfly.banklisttest.model.Bank

@Dao
interface BankDao {

    @Query("SELECT * from banks_table")
    fun getAllBanks(): List<Bank>

    @Insert
    fun insertAll(bank: List<Bank>)
}