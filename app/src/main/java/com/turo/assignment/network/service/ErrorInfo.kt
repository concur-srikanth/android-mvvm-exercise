package com.turo.assignment.network.service

import com.google.gson.annotations.SerializedName

class ErrorInfo (

    @SerializedName("code")
    val code : String?,

    @SerializedName("description")
    val description : String?

)
