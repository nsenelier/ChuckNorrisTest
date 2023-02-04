package com.example.chucknorristest.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "category")
@JsonClass(generateAdapter = true)
data class Category (
    @PrimaryKey @ColumnInfo(name = "id") val categoryId: String,
    @Json(name = "name")
    val name: String
    )