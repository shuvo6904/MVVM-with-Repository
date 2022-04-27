package com.example.mvvmwithkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithkotlin.R
import com.example.mvvmwithkotlin.database.Subscriber
import com.example.mvvmwithkotlin.databinding.SubscriberListItemBinding

class SubscriberRecyclerViewAdapter(private val subscriberList : List<Subscriber>,
                                    private val clickListener : (Subscriber) -> Unit) : RecyclerView.Adapter<SubscriberViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding : SubscriberListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.subscriber_list_item, parent, false)

        return SubscriberViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(subscriberList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }


}

class SubscriberViewHolder(private val binding : SubscriberListItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber : Subscriber, clickListener: (Subscriber) -> Unit){
        binding.emailTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.linearLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }

}