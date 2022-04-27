package com.example.mvvmwithkotlin.repository

import com.example.mvvmwithkotlin.database.Subscriber
import com.example.mvvmwithkotlin.database.SubscriberDAO

class SubscriberRepository(private val subscriberDAO: SubscriberDAO) {

    val subscriber = subscriberDAO.getAllSubscriber()

    suspend fun insert(subscriber: Subscriber){
        subscriberDAO.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        subscriberDAO.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        subscriberDAO.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(){
        subscriberDAO.deleteAllSubscriber()
    }




}