package com.turo.assignment.network.service

import com.google.gson.annotations.SerializedName

open class BusinessInfo (

    @SerializedName("total")
    open val total: Long?,

    @SerializedName("businesses")
    open val businesses: List<Business?>,

    @SerializedName("errors")
    open val error : ErrorInfo?

)

