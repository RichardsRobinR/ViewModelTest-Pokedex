package com.rdx.viewmodeltest.data

import com.google.gson.annotations.SerializedName


data class Pokedex(
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
)