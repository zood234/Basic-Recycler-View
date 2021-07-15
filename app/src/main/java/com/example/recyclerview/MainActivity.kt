package com.example.recyclerview


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the LayoutManager that this RecyclerView will use.
        recycler_view_items.layoutManager = LinearLayoutManager(this)

        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = ItemAdapter(this, getItemsList())

        // adapter instance is set to the recyclerview to inflate the items.
        recycler_view_items.adapter = itemAdapter
    }

    /**
     * Function is used to get the Items List which is added in the list.
     */
    private fun getItemsList(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 1..15) {
            list.add("Item $i")
        }

        return list
    }
}