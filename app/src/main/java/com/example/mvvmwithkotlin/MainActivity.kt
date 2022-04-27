package com.example.mvvmwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmwithkotlin.adapter.SubscriberRecyclerViewAdapter
import com.example.mvvmwithkotlin.database.Subscriber
import com.example.mvvmwithkotlin.database.SubscriberDatabase
import com.example.mvvmwithkotlin.databinding.ActivityMainBinding
import com.example.mvvmwithkotlin.repository.SubscriberRepository
import com.example.mvvmwithkotlin.viewmodel.SubscriberViewModel
import com.example.mvvmwithkotlin.viewmodel.SubscriberViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getDatabaseInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
    }

    private fun displaySubscriberList(){
        subscriberViewModel.subscriber.observe(this, Observer {

            Log.d("LIVEDATA", it.toString())
            binding.recyclerView.adapter = SubscriberRecyclerViewAdapter(it, {selectedItem : Subscriber -> listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(subscriber : Subscriber){
        Toast.makeText(this, "Selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}