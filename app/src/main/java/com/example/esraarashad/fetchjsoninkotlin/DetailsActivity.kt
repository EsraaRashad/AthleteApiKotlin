package com.example.esraarashad.fetchjsoninkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*



class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = getIntent()

        val movie :Athletes =intent.getSerializableExtra("Athlete") as Athletes

        details_athlete_name?.text= movie.name
        details_athlete_brief?.text=movie.brief
        if (movie.image != "") {
            Glide.with(this).load(movie.image).into(details_athlete_image)
        }
        else {
            details_athlete_image.visibility=View.GONE
            val params1 = details_athlete_name.getLayoutParams() as RelativeLayout.LayoutParams

            params1.topMargin =200
            details_athlete_name.setLayoutParams(params1)


            val params = details_athlete_brief.getLayoutParams() as RelativeLayout.LayoutParams
            params.topMargin =500
            details_athlete_brief.setLayoutParams(params)
        }
    }
}
