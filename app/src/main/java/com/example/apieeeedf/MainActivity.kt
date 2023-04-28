package com.example.apieeeedf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CacheResponse

class MainActivity : AppCompatActivity() {

    lateinit var tvResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResponse = findViewById(R.id.tvResponse)


        //TODO create instance Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://particulier.edf.fr/services/rest/referentiel/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()


        val edfService = retrofit.create(EdfService::class.java)



        // TODO : call to api

        val result = edfService.getEdfByHistoric()

        result.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    tvResponse.text = response.body()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(applicationContext, "Erreur serveur", Toast.LENGTH_SHORT).show()
            }

        })
    }
}