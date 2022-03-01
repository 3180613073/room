package com.project.room.main

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    //用代替RXJAVA的liveData
    var tv: MutableLiveData<String> = MutableLiveData()

    var tvOne: MutableLiveData<String> = MutableLiveData()


    fun test(){
       tv.value="oooo"
       Log.e("TAG", "test: ces" +tvOne.value)
       Log.e("TAG", "test: ces tv" +tv.value)
   }
}