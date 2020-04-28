package com.dragonfly.banklisttest.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dragonfly.banklisttest.model.Bank

@Database(entities = [(Bank::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bankDao(): BankDao

    companion object {
        private const val DATABASE_NAME = "Bank_Data_Base"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME
                ).build()
            }
        }
    }
}