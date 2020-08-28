package com.turo.assignment.network.service

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusinessService {

    @GET("/v3/businesses/search")
    fun getBusinessesNearBy(@Query("term")  term : String, @Query("location")  location : String ) : Call<BusinessInfo>

    @GET("/v3/businesses/search")
    fun getBusinessesNearByRx(@Query("term")  term : String, @Query("location")  location : String ) : Single<BusinessInfo>

}