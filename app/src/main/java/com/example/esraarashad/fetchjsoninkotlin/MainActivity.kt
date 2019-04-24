package com.example.esraarashad.fetchjsoninkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_list_athletes.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        loadData()
    }

    private fun athleteItemClicked(athlete: Athletes) {

        val intent = Intent(this@MainActivity, DetailsActivity::class.java)


        intent.putExtra("Athlete", athlete)
        startActivity(intent)

    }

    fun loadData() {

        var retrofit=Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val athleteApi = retrofit.create(INetworkApi::class.java)
        var call :Call<AthleteList> =athleteApi.getAthletes()
        call.enqueue(object : Callback<AthleteList> {
            override fun onFailure(call: Call<AthleteList>, t: Throwable) {
                println("Error")
            }

            override fun onResponse(call: Call<AthleteList>, response: Response<AthleteList>) {
                var athletesList = response.body()?.athletes!!


                val rAdapter = AthleteItemAdapter(this@MainActivity,athletesList , { athlete : Athletes -> athleteItemClicked(athlete) })

                rv_list_athletes.adapter = rAdapter;
            }

        })
    }
}
