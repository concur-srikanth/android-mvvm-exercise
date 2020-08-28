package com.turo.assignment.network.service

import com.google.gson.annotations.SerializedName

open class Business   (

    @SerializedName("id")
    open val id: String?,

    @SerializedName("phone")
    open val phone: String?,

    @SerializedName("display_phone")
    open val display_phone: String?,

    @SerializedName("rating")
    open val rating: Double?,

    @SerializedName("alias")
    open val alias: String?,

    @SerializedName("name")
    open val name: String?,

    @SerializedName("url")
    open val url: String?,

    @SerializedName("image_url")
    open val image_url: String?,

    @SerializedName("distance")
    open val distance: Double?,

    @SerializedName("price")
    open val price: String?,

    @SerializedName("location")
    open val location: LocationInfo

    )