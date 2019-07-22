package com.example.practice2

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("breweries/")
    fun getBreweries(): Single<List<Brewery>>
}