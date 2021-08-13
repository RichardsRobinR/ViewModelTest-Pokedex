package com.rdx.viewmodeltest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rdx.viewmodeltest.data.Pokedex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import java.net.URL

class MainViewModel : ViewModel() {

    val counterLiveData: LiveData<Int>
        get() = counter

    private val counter = MutableLiveData<Int>()
    private var count = 0

    val pokemonLiveData: LiveData<String>
        get() = name

    private val name = MutableLiveData<String>()

    //pokedex
    val pokedexLiveData: LiveData<Pokedex>
        get() = list

    private val list = MutableLiveData<Pokedex>()


    fun increaseCounter() {
        counter.value = ++count
    }


    var pokename = "qq"


     lateinit var pokedex : Pokedex
    suspend fun restApi()  {


             try {

                 coroutineScope {
                     val deferredOne = async {
                         val response = URL("https://api.jsonbin.io/b/60772efa0ed6f819beac4c31").readText()

                         val gson = Gson()


                         pokedex = gson.fromJson(response, Pokedex::class.java)
                     }

                     deferredOne.await()

                 }





                  //pokename = pokedex.pokemon[0].name

                // name.value = pokedex.pokemon[0].name

                // Log.d("Myapp", pokename)




             } catch (e: Exception) {
                 Log.d("Myapp1", e.toString())
             }
        list.postValue(pokedex)


         }


         //list.value = pokedex





    init {
        viewModelScope.launch(Dispatchers.IO) {
            restApi()

        }
    }
    fun show() {
        //name.value = pokename

       // list.postValue(pokedex)
    }
}

