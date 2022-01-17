package com.sapar.narutoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapar.narutoapp.model.CharactersItem
import com.sapar.narutoapp.model.retrofit.RetroInstance
import com.sapar.narutoapp.model.retrofit.RetroServiceInterface
import retrofit2.*

class CharactersFragmentViewModel: ViewModel() {

    private lateinit var liveDataList: MutableLiveData<List<CharactersItem>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver():MutableLiveData<List<CharactersItem>>{
        return liveDataList
    }

    fun makeApiCall(){
        val retroInstance = RetroInstance.getRetrofitInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getCharacters()
        call.enqueue(object : Callback<List<CharactersItem>>{
            override fun onResponse(
                call: Call<List<CharactersItem>>,
                response: Response<List<CharactersItem>>
            ) {
                liveDataList.postValue(response.body())
                Log.d("Android", "onResponse: ")

            }

            override fun onFailure(call: Call<List<CharactersItem>>, t: Throwable){
                Log.d("Android", "onFailure: ")
                //                liveDataList.postValue(null!!)

            }
        })
    }
}