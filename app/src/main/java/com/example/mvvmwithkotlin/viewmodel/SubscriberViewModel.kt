package com.example.mvvmwithkotlin.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithkotlin.database.Subscriber
import com.example.mvvmwithkotlin.repository.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository) : ViewModel(), Observable{

    val subscriber = subscriberRepository.subscriber

    private var isUpdateOrDeleted = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){

        val name = inputName.value!!
        val email = inputEmail.value!!

        insert(Subscriber(0, name, email))

        inputName.value = null
        inputEmail.value = null


    }

    fun clearAllOrDelete(){

        if (isUpdateOrDeleted){
            delete(subscriberToUpdateOrDelete)
        }else{
            deleteAll()
        }



    }

    fun insert(subscriber: Subscriber) : Job = viewModelScope.launch {
            subscriberRepository.insert(subscriber)
        }

    fun update(subscriber: Subscriber) : Job = viewModelScope.launch {
        subscriberRepository.update(subscriber)
    }

    fun delete(subscriber: Subscriber) : Job = viewModelScope.launch {
        subscriberRepository.delete(subscriber)

        inputName.value = null
        inputEmail.value = null

        isUpdateOrDeleted = false

        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun deleteAll() : Job = viewModelScope.launch {
        subscriberRepository.deleteAll()
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email

        isUpdateOrDeleted = true
        subscriberToUpdateOrDelete = subscriber

        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}