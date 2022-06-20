package com.daniildyudin.mentors.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniildyudin.mentors.Models.User
import com.daniildyudin.mentors.R
import com.daniildyudin.mentors.databinding.CardItemBinding

class CardAdapter(val listener: Listener): RecyclerView.Adapter<CardAdapter.CardHolder>( ) {

    var usersList = ArrayList<User>()

    class CardHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = CardItemBinding.bind(item)

        fun bind(user: User, listener: Listener) = with(binding) {
            tvCardName.text = user.name
            tvCardAbout.text = user.about
            tvCardCategory.text = user.category
            itemView.setOnClickListener {
                listener.onClickItem(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(usersList[position], listener)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun addCard(user: User) {
        usersList.add(user)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClickItem(user: User) {

        }
    }
}