package com.example.moneylist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationennew.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var addButton: Button
    private lateinit var amountListView: ListView

    private val amountList = mutableListOf<Money>()
    private lateinit var adapter: ArrayAdapter<Money>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        addButton = findViewById(R.id.addButton)
        amountListView = findViewById(R.id.amountListView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, amountList)
        amountListView.adapter = adapter

        addButton.setOnClickListener {
            val amount = amountEditText.text.toString()
            if (amount.isNotEmpty()) {
                val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                amountList.add(Money(amount, currentDate))
                adapter.notifyDataSetChanged()
                amountEditText.text.clear()
            }
        }
    }
}
data class Money(val amount: String, val date: String)
