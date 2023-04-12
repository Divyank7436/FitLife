package com.example.workoutapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(private val context: Context) :
    SliderViewAdapter<SliderAdapter.ViewHolder>() {

    private val images = arrayOf(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6)

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.slider_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder?.imageView?.setImageResource(images[position])
    }

    override fun getCount(): Int {
        return images.size
    }

    inner class ViewHolder(view: View) : SliderViewAdapter.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }
}

