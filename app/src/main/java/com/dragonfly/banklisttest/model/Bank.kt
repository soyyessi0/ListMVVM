package com.dragonfly.banklisttest.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "banks_table")
class Bank {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null

    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("bankName")
    var bankName: String? = null

    @NonNull
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null

    @NonNull
    @ColumnInfo(name = "age")
    @SerializedName("age")
    var age: Int? = null

    @NonNull
    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null
}