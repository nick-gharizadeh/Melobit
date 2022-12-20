package com.example.melobit.data.model

data class ErrorResponse(
    val code: Int,
    val message: String,
    val subcode: Int,
    val type: String,
    val userMessage: String,
    val userTitle: String
)