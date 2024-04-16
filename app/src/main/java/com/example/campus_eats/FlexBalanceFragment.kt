package com.example.campus_eats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class FlexBalanceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flex_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView for transaction history and populate it with data
        val recyclerViewTransactionHistory = view.findViewById<RecyclerView>(R.id.recyclerViewTransactionHistory)
        val transactionHistoryAdapter = TransactionHistoryAdapter(getTransactionHistory())
        recyclerViewTransactionHistory.adapter = transactionHistoryAdapter

        // Retrieve and display overall balance
        val textViewBalanceValue = view.findViewById<TextView>(R.id.textViewBalanceValue)
        val overallBalance = getOverallBalance()
        textViewBalanceValue.text = "Your balance: $$overallBalance"
    }

    private fun getOverallBalance(): Int {
        // Simulated method to retrieve overall balance from a data source
        return 500
    }

    private fun getTransactionHistory(): List<Transaction> {
        // Simulated method to retrieve transaction history from a data source
        // Replace with actual implementation to fetch transaction history from a database or API
        return listOf(
            Transaction("Chick-fil-A", 10.0, "2024-04-16"),
            Transaction("Starbucks", 5.0, "2024-04-15"),
            Transaction("Subway", 8.0, "2024-04-14")
        )
    }
}