package com.sapar.narutoapp.model.retrofit

import com.sapar.narutoapp.model.CharactersItem
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("a5f2d08f-b2dd-4e18-aba2-cb3640f2bc25")
    fun getCharacters(): Call<List<CharactersItem>>
}