package com.furkan.mobilliumcase.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furkan.mobilliumcase.R
import com.furkan.mobilliumcase.data.model.MoviesResult

class VerticalAdapter(
    var dataSet: ArrayList<MoviesResult>? = null,
    val itemClickDetail: ((MoviesResult) -> Unit)?

) :
    RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = ViewHolder(
            inflater.inflate(
                R.layout.item_vertical,
                parent,
                false
            )
        )

        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataSet != null && 0 <= position && position < dataSet!!.size) {
            val data = dataSet!!.get(position)


            Glide.with(holder.image)
                .load("http://image.tmdb.org/t/p/w500" + data.backdropPath)
                .centerCrop()
                .into(holder.image)

            holder.name.text = data.title
            holder.desc.text = data.overview
            holder.date.text = data.releaseDate

            holder.container.setOnClickListener {
                itemClickDetail?.let { it1 -> it1(data) }
            }

        }
    }


    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
        val date: TextView = view.findViewById(R.id.date)
        val container: ConstraintLayout = view.findViewById(R.id.container)

    }
}