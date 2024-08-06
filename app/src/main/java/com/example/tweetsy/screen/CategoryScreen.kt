package com.example.tweetsy.screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweetsy.R
import com.example.tweetsy.viewmodel.TweetViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun CategoryScreen(onClick:(category:String)->Unit) {

    val tweetViewModel : TweetViewModel = hiltViewModel()
    tweetViewModel.getCategory()
    val categories: State<List<String>> = tweetViewModel.categories.collectAsState()

    if(categories.value.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = Color.Black)
        }
    }
    else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value){
                Log.d("Ahsan", "CategoryScreen: $it")
                CategoryListItem(category = it,onClick)
            }
        }
    }

}

@Composable
fun CategoryListItem(category: String,onClick:(category:String)->Unit) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick(category)
            }
            .size(160.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, Color(0xFFEEEEEE)), contentAlignment = Alignment.BottomCenter
    ) {

        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(10.dp)
        )
    }
}