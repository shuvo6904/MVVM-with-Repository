package com.example.mvvmwithkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithkotlin.repository.SubscriberRepository

class SubscriberViewModelFactory(private val subscriberRepository: SubscriberRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(subscriberRepository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")

    }

}