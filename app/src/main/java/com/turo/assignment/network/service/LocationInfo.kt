package com.turo.assignment.network.service

import com.google.gson.annotations.SerializedName

open class LocationInfo (
    @SerializedName("city")
    open val city: String?,

    @SerializedName("country")
    open val country: String?,

    @SerializedName("address1")
    open val address1: String?,

    @SerializedName("address2")
    open val address2: String?,

    @SerializedName("state")
    open val state: String?,

    @SerializedName("zip_code")
    open val zip_code: String?,

    @SerializedName("display_address")
    open val display_address: List<String?>

)
