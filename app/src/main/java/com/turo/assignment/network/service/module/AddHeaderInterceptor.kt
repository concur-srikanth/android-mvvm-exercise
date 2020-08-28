package com.turo.assignment.network.service.module

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/*
   Interceptor to add Header into network request
 */
class AddHeaderInterceptor  : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val builder = chain.request().newBuilder()
        builder.addHeader(
            "Authorization",
            "Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx")
        return chain.proceed(builder.build())
    }
}