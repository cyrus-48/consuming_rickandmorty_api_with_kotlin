package com.example.kui.api

import com.example.kui.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getCharacters(): Call<CharactersResponse>
}