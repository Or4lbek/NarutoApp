package com.sapar.narutoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapar.narutoapp.model.CharactersItem
import com.sapar.narutoapp.model.network.RetroInstance
import com.sapar.narutoapp.model.network.RetroServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private var liveDataCharacters: MutableLiveData<List<CharactersItem>> = MutableLiveData()

    fun getCharactersObserver(): MutableLiveData<List<CharactersItem>> {
        return liveDataCharacters
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetrofitInstance()
            val retroService = retroInstance.create(RetroServiceInterface::class.java)
            val call = retroService.getCharacters()
            liveDataCharacters.postValue(call)
        }
    }
}