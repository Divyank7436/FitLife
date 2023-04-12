package com.example.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val items : ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        val llHistoryItemMain = binding.llHistory
        val tvPosition = binding.tvPosition
        val tvDate = binding.tvDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String  = items.get(position)
        holder.tvPosition.text = (position+1).toString()
        holder.tvDate.text = date

        if(position%2==0){
            holder.llHistoryItemMain.setBackgroundColor(Color.parseColor("#312638"))

        }
        else{
            holder.llHistoryItemMain.setBackgroundColor(Color.parseColor("#1A2421"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}