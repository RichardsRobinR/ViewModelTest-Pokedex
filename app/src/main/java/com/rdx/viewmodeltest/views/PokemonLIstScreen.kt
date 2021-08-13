package com.rdx.viewmodeltest.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.rememberImagePainter
import com.rdx.viewmodeltest.data.Pokedex
import com.rdx.viewmodeltest.viewmodel.MainViewModel

class PokemonLIstScreen(override val key: String) : Screen {



    @Composable
    override fun Content() {
        PokeListView()
    }

    @Composable
    fun PokeListView(model: MainViewModel = viewModel()) {
        var view = remember {
            mutableStateOf(false)
        }

        val pokemonll by model.pokedexLiveData.observeAsState()


        Scaffold(
            topBar = {
                Button(onClick = {
                    model.show()
                    view.value = true
                }
                ) {
                    Text("Make True")
                }
            }
        ) {
            ListView(pokemonll)
        }
    }

    @Composable
    fun ListView(pokemonll: Pokedex?) {

        val navigator = LocalNavigator.currentOrThrow

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            if (pokemonll != null) {

                items(pokemonll.pokemon) { message ->
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable {
                            navigator.push(PokemonDetailsScreen(message.name,message.imageurl,""))
                        }
                        .background(color = Color.Green),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(message.name)
                        Image(
                            painter = rememberImagePainter(message.imageurl),
                            contentDescription = null,
                            modifier = Modifier.size(128.dp)
                        )
                    }
                }

            } else {
                item {
                    LinearProgressIndicator()
                }
            }


        }
    }
}