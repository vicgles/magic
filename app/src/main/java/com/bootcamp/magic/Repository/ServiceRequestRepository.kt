package com.bootcamp.magic.repository

import android.util.Log
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.Sets
import com.bootcamp.magic.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRequestRepository() {

    var total_count_Sets:Int? = null


    fun getSetsFromApi(Succes: (data: Sets) -> Unit,error: (message: String) -> Unit) {
        val request = RetrofitConfig().interfaceData()
        request.getSets().enqueue(object : Callback<Sets>{
            override fun onFailure(call: Call<Sets>, t: Throwable) {
                Log.d("resultado", t.message.toString())
                error(t.message.toString())
            }

            override fun onResponse(call: Call<Sets>, response: Response<Sets>) {
                Log.d("resultado", response.toString())
                if (response.isSuccessful) {

                    if (response.body()!=null){
                        Succes(response.body()!!)
                        total_count_Sets = response.headers().get("total-count")?.toInt()
                    }
                }
            }
        })
    }

    fun getCardsFromApi(set:String?,page:Int,Succes: (data: Cards,total_count:Int?) -> Unit,Error: (message: String) -> Unit) {
        val request = RetrofitConfig().interfaceData()
        request.getCards(set = set, page = page).enqueue(object : Callback<Cards>{
            override fun onFailure(call: Call<Cards>, t: Throwable) {
                Log.d("resultado", t.message.toString())
                Error(t.message)
            }

            override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
                Log.d("resultado", response.toString())
                if (response.isSuccessful) {

                    if (response.body()!=null){
                        Succes(response.body()!!,response.headers().get("total-count")?.toInt())

                        //Log.d("total",total_count_Cards.toString())
                    }
                }
            }
        })
    }
}