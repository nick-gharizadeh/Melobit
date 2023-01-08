package com.example.melobit.data.model.search

data class SearchResponse(
    val results: List<SearchItem>,
    val total: Int
)