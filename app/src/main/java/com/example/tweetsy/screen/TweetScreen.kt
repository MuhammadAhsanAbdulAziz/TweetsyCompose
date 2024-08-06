package com.example.tweetsy.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.tweetsy.models.TweetResponseItem
import com.example.tweetsy.viewmodel.TweetViewModel

@Composable
fun TweetScreen(backStackEntry: NavBackStackEntry) {
    val tweetViewModel: TweetViewModel = hiltViewModel()
    tweetViewModel.getTweets()
    val tweets = tweetViewModel.tweets.collectAsState()
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(tweets.value) {
            Log.d("Ahsan", "CategoryScreen: $it")
            TweetListItem(tweet = it.text)
        }
    }

}

@Composable
fun TweetListItem(tweet: String, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Text(text = tweet, modifier = Modifier.padding(16.dp))
    }
}