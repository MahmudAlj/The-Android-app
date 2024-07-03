package com.example.moneylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplicationennew.R

class MoneyAdapter(private val context: Context, private val dataSource: List<Money>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.list_item_money, parent, false)
        val amountTextView = view.findViewById<TextView>(R.id.amountTextView)
        val dateTextView = view.findViewById<TextView>(R.id.dateTextView)
        val money = getItem(position) as Money
        amountTextView.text = money.amount
        dateTextView.text = money.date
        return view
    }
}
