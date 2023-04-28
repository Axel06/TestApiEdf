package com.example.apieeeedf

import retrofit2.Call
import retrofit2.http.GET




interface EdfService {


    @GET(value = "historicTEMPOStore?dateBegin=2022&dateEnd=2023")
    fun getEdfByHistoric(): Call<String>

}