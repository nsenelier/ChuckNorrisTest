package com.example.chucknorristest.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QueryResponse(
    @Json(name = "result")
    val result: List<Joke?>?,
    @Json(name = "total")
    val total: Int?
)