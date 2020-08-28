package com.turo.assignment.network.service

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Response


open class BusinessInteractor   {

    val businessService : BusinessService? =
        com.turo.assignment.network.RetrofitClient.getRetrofitInstance()?.create(BusinessService::class.java) // Due to time contraint not using DI tool

    fun getBusinessNearBy(  term : String,  location : String ) : Call<BusinessInfo>? {
        return businessService?.getBusinessesNearBy(term, location)
    }

}
