package com.example.campus_eats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionHistoryAdapter(private val transactions: List<Transaction>) : RecyclerView.Adapter<TransactionHistoryAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantTextView: TextView = itemView.findViewById(R.id.textViewRestaurant)
        val amountTextView: TextView = itemView.findViewById(R.id.textViewAmount)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.restaurantTextView.text = transaction.restaurant
        holder.amountTextView.text = "$${transaction.amount}"
        holder.dateTextView.text = transaction.date
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}