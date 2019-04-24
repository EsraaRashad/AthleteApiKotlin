package com.example.esraarashad.fetchjsoninkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide



class AthleteItemAdapter(val context :Context , var AthleteList: List<Athletes>,val clickListener: (Athletes) -> Unit) : RecyclerView.Adapter<AthleteItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.athlete_item_layout ,p0,false))
    }

    override fun getItemCount(): Int {
        return AthleteList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(context, AthleteList[p1],clickListener)

    }


    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

        var athletename = view.findViewById<TextView>(R.id.txtPostTitle)
        var brief = view.findViewById<TextView>(R.id.txtPostBody)
        var img = view.findViewById<ImageView>(R.id.imgPost)

        fun bind(context: Context, athletes: Athletes, clickListener: (Athletes) -> Unit) {
            athletename?.text = athletes.name
            brief?.text = athletes.brief
            if (athletes.image != "") {

                Glide.with(context)
                        .load(athletes.image)
                        .into(img);

            } else {
                img.visibility = View.GONE
                val params = athletename.getLayoutParams() as RelativeLayout.LayoutParams
                params.width = 600
                params.marginStart = 60
                athletename.setLayoutParams(params)
                val params1 = brief.getLayoutParams() as RelativeLayout.LayoutParams
                params1.width = 800
                params1.marginStart = 60
                brief.setLayoutParams(params1)
            }
            itemView.setOnClickListener { clickListener(athletes) }

        }
    }
}