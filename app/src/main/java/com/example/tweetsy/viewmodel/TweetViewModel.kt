package com.example.tweetsy.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.models.TweetResponseItem
import com.example.tweetsy.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(
    private val tweetRepository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val categories: StateFlow<List<String>>
        get() = tweetRepository.categories

    val tweets: StateFlow<List<TweetResponseItem>>
        get() = tweetRepository.tweets

    fun getCategory() {
        viewModelScope.launch {
            tweetRepository.getCategory()
        }
    }

    fun getTweets() {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category")?: "motivation"
            tweetRepository.getTweets(category)
        }
    }
}