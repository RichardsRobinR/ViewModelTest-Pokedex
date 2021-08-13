package com.rdx.viewmodeltest.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.rememberImagePainter


data class PokemonDetailsScreen(var name:String, var imageurl :String, override val key: String) : Screen {


    @Composable
    override fun Content() {
       DetailsView()
    }

    @Composable
    fun DetailsView() {
        Scaffold() {
            ListView()
        }
    }

    @Composable
    fun ListView() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable { }
                .background(color = Color.Green),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(name)
                Image(
                    painter = rememberImagePainter(imageurl),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
        }


    }

}
