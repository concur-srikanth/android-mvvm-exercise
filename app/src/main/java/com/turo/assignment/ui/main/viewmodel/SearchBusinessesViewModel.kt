package com.turo.assignment.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.turo.assignment.network.service.Business
import com.turo.assignment.network.service.BusinessInfo
import com.turo.assignment.network.service.BusinessInteractor
import com.turo.assignment.ui.main.model.BusinessUIModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Singles
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

//@Component( modules = [ServiceModule::class])
open class SearchBusinessesViewModel  : ViewModel()  {

    var businessInteractor  : BusinessInteractor = BusinessInteractor()

    val businessInfoData by lazy { MutableLiveData<BusinessInfo>() }

    val networkError : MutableLiveData<Throwable> by lazy { MutableLiveData<Throwable>() }

    private val bagDisposable = CompositeDisposable()

    fun callGetBusinessesRx(term : String, location : String) {

        businessInteractor?.businessService?.getBusinessesNearByRx(term, location = location)
        ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe(this::handleSuccess , this::handleError)?.let {
                bagDisposable?.add(
                    it
                )
            }
     }

    fun handleSuccess(businessesInfo: BusinessInfo){
        businessInfoData?.postValue(businessesInfo)
    }

    fun handleError(t : Throwable){
        networkError.postValue(t)
    }


    fun getMiles(meters : Double ) : Double {
        return meters * 0.000621371192
    }

    fun mapToUIModel(businessesInfo : BusinessInfo): MutableList<BusinessUIModel> {
        val businessUIModelList : MutableList<BusinessUIModel> = mutableListOf()
        businessesInfo.businesses.forEach { business ->
            val businessUIModel = business?.location?.let {
                BusinessUIModel(business.name ?: "",
                    business.image_url ?: "", "*" + business.rating.toString() ?: "",
                    getMiles(business.distance?: 0.0).toString() ?: "0.0" ,
                    business.phone ?: "",
                    it, business.url ?: ""
                )
            }
            if (businessUIModel != null) {
                businessUIModelList.add(businessUIModel)
            }
        }
        return businessUIModelList
    }

    fun unsubsribe() {
        // unsubsribe
        bagDisposable.dispose()
    }
}
