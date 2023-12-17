package com.fa.junittesting.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val uid: Int,

    @ColumnInfo(name = "Name")
    val name: String?,

    @ColumnInfo(name = "Price")
    val price: Int,

    @ColumnInfo(name = "Amount")
    val amount: Float,

    @ColumnInfo(name = "Image")
    val imageUrl: String?

)