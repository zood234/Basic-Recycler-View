package com.example.recyclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappwithapi.NewsApi
import com.example.newsappwithapi.dataWeb.NewsResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView()

    }

fun getApiData(): ArrayList<String>{
    var list = ArrayList<String>()

    //This one gets added
    list.add("THIS STUFF")

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(NewsApi::class.java)
    val call = service.getNews()
    call.enqueue(object : Callback<NewsResponse> {

        //I Thinks this part is the problem  for some for some reason recyclerView() just skips it
        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>, ) {


            if (response.code() == 200) {
                val newsResponse = response.body()!!

                list.add("This WAS NOT ADDED")
                list.add(newsResponse.results[2].title)
                list.add(newsResponse.results[3].title)

                val stringBuilder = "Section is : " + newsResponse.section +
                        "\n" + list[1] +
                        "\n" + newsResponse.results[1].title +
                        "\n" + newsResponse.results[2].title +
                        "\n" + newsResponse.results[3].title +
                        "\n" + newsResponse.results[4].title + "bjhkh ddgg"
                textView.text = stringBuilder
            }
        }
        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
        }
    })
    return list

}


    fun recyclerView(){
        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = ItemAdapter(this, getApiData())

        recycler_view_items.layoutManager = LinearLayoutManager(this)

        // adapter instance is set to the recyclerview to inflate the items.
        recycler_view_items.adapter = itemAdapter
    }



}