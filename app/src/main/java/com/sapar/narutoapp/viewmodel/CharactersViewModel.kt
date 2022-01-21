package com.sapar.narutoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapar.narutoapp.model.CharactersItem
import com.sapar.narutoapp.model.network.RetroInstance
import com.sapar.narutoapp.model.network.RetroServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {

    private lateinit var liveDataList: MutableLiveData<List<CharactersItem>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver():MutableLiveData<List<CharactersItem>>{
        return liveDataList
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetrofitInstance()
            val retroService = retroInstance.create(RetroServiceInterface::class.java)
            val call = retroService.getCharacters()
            liveDataList.postValue(call)
        }
//        call.enqueue(object : Callback<List<CharactersItem>>{
//            override fun onResponse(
//                call: Call<List<CharactersItem>>,
//                response: Response<List<CharactersItem>>
//            ) {
//                liveDataList.postValue(response.body())
//                Log.d("Android", "onResponse: ")
//
//            }
//
//            override fun onFailure(call: Call<List<CharactersItem>>, t: Throwable){
//                Log.d("Android", "onFailure: ")
//                Log.d("Android", "Throwable: " + t.toString())
//                Log.d("Android", "Throwable message: " + t.message)
//                //                liveDataList.postValue(null!!)
//
//            }
//        })
    }
}