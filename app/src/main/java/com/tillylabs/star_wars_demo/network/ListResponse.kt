package com.tillylabs.star_wars_demo.network

data class ListResponse<T>(val count: Int = 1,
                        val next: String = "",
                        val previous: String? = "",
                        val results: ArrayList<T> = ArrayList())