package com.example.moneylist

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationennew.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var addButton: Button
    private lateinit var amountListView: ListView

    private val amountList = mutableListOf<Money>()
    private lateinit var adapter: MoneyAdapter
    private val sharedPreferences by lazy {
        getSharedPreferences("MoneyListPreferences", MODE_PRIVATE)
    }
    private val gson by lazy {
        Gson()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        addButton = findViewById(R.id.addButton)
        amountListView = findViewById(R.id.amountListView)

        loadAmountList()

        adapter = MoneyAdapter(this, amountList)
        amountListView.adapter = adapter

        addButton.setOnClickListener {
            val amount = amountEditText.text.toString()
            if (amount.isNotEmpty()) {
                val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                amountList.add(Money(amount, currentDate))
                adapter.notifyDataSetChanged()
                saveAmountList()
                amountEditText.text.clear()
            }
        }
    }

    private fun loadAmountList() {
        val json = sharedPreferences.getString("amountList", null)
        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<Money>>() {}.type
            val list: MutableList<Money> = gson.fromJson(json, type)
            amountList.addAll(list)
        }
    }

    private fun saveAmountList() {
        val json = gson.toJson(amountList)
        sharedPreferences.edit().putString("amountList", json).apply()
    }
}

data class Money(val amount: String, val date: String)
