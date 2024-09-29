package com.example.otchallenge.data.api.response

data class NYTBooksResponse(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    val results: BookResults,
    val status: String
)