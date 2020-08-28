package com.turo.assignment.ui.main.model

import com.turo.assignment.network.service.LocationInfo

class BusinessUIModel(
    val businessName : String,
    val imageUrl : String,
    val rating : String,
    val distance : String,
    val phoneNum : String,
    val location : LocationInfo,
    val webUrl : String
)