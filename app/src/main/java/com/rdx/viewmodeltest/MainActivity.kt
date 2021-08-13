package com.rdx.viewmodeltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.Navigator
import coil.compose.rememberImagePainter
import com.rdx.viewmodeltest.data.Pokedex
import com.rdx.viewmodeltest.ui.theme.ViewModelTestTheme
import com.rdx.viewmodeltest.viewmodel.MainViewModel
import com.rdx.viewmodeltest.views.PokemonLIstScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ViewModelTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Greeting("Android"

                    //ScreenDemo()

                    //PokemonUiDemo()
                    Navigator(PokemonLIstScreen(""))

                   // ListViewDisplay()
                }
            }
        }
    }


   /* @Composable
    fun ScreenDemo(model: MainViewModel = viewModel()) { // 2.
        val count by model.counterLiveData.observeAsState(0)


        val pokemonname by model.pokemonLiveData.observeAsState("check")


        //Demo("This is $count") { model.increaseCounter() }
        PokemonUiDemo("This is $pokemonname") { model.restApi()
        }
    }*/

    // 4.
    @Composable
    fun Demo(text: String, onClick: () -> Unit = {}) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text)
            Button(
                onClick = onClick,
            ) {
                Text(text = "Add 1")
            }

        }
    }


    @Composable
    fun PokemonUiDemo(model: MainViewModel = viewModel()) {
        var view = remember {
            mutableStateOf(false)
        }
        val pokemonname by model.pokemonLiveData.observeAsState("check")
        val pokemonll by model.pokedexLiveData.observeAsState()

        var viewstat = remember {
            mutableStateOf(pokemonll)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(pokemonname)
            Button(
                onClick = {},
            ) {
                Text(text = "Add 1")
            }
            Button(
                onClick = {
                    model.show()
                    view.value = true
                          },
            ) {
                Text(text = "Show")
            }

            if(view.value == true) {
                if (pokemonll!!.pokemon.isEmpty()) {
                    LinearProgressIndicator()
                } else {
                    pokemonll!!.pokemon.forEach { message ->
                        Text(message.name)
                    }
                }
            }

            /*if(view.value == true) {

            }*/


        }


    }




    @Composable
    fun NotFound() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator()
        }
    }

    
    @Composable
    fun ListViewDisplay(model: MainViewModel = viewModel()) {

        model.show()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(10) { items ->
                Text(text = "Item: ${model.pokedexLiveData.value!!.pokemon[0].name}")
            }

            /*items(5) { index ->
                Text(text = "Item: $index")
            }*/
        }
    }

    // 5.
    @Preview
    @Composable
    fun PreviewDemo() {
        ViewModelTestTheme {
           // ListView(pokemonll = null)
        }
    }

}

/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelTestTheme {
        Greeting("Android")
    }
}*/
