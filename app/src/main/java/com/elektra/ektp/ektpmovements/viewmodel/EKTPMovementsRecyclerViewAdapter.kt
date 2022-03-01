package com.elektra.ektp.ektpmovements.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.R
import com.elektra.ektp.databinding.DateSectionMovementBinding
import com.elektra.ektp.databinding.ItemMovementBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel
import com.elektra.ektp.ektpmovements.view.EKTPMovementsDetailsActivity

class EKTPMovementsRecyclerViewAdapter(
    private val context: Context,
    private var data: List<EKTPMovementsModel>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var months: Array<String> = context.resources.getStringArray(R.array.months)

    //ViewHolder function to fill recycler view
    inner class ItemMovementsViewHolder(val binding: ItemMovementBinding) : RecyclerView.ViewHolder(binding.root){
        //Function get de data model structure
        fun bind(item: EKTPMovementsModel){
            //Set databinding from data model into layout
            binding.movementsListItem = item
        }
        //---
    }
    //---

    //ViewHolder function to fill recycler view
    inner class DateMovementsViewHolder(val binding: DateSectionMovementBinding) : RecyclerView.ViewHolder(binding.root){
        //Function get de data model structure
        fun bind(item: EKTPMovementsModel){
            //Set databinding from data model into layout
            binding.movementsListItem = item
        }
        //---
    }
    //---

    //View holder to inflate view that contains the viewHolder recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //inflater value context
        val inflater = LayoutInflater.from(parent.context)
        //Inflater layout child to recyclerview
        val itemMovement = ItemMovementBinding.inflate(inflater, parent, false)
        val dateSectionMovement = DateSectionMovementBinding.inflate(inflater, parent,false)

        return when (viewType) {
            0 -> DateMovementsViewHolder(dateSectionMovement)
            else -> {ItemMovementsViewHolder(itemMovement)}
        }
    }
    //---

    //Bind view holder to fill each cell on recycler view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.isSelected = false
        /*var dateData = data[position].detailDate
        day = dateData.substring(0..1)
        month = dateData.substring(3..4).toInt()-1
        var mesString = ""
        for(i in months.indices){
            if (i == month){
                mesString = months[month]
            }
        }
        dateData = "$day de $mesString"
        data[position].detailDate = dateData*/
        if (position != RecyclerView.NO_POSITION){
            holder.itemView.isSelected = true
            if (holder.itemViewType == 0){
                val dateViewHolder: DateMovementsViewHolder = holder as DateMovementsViewHolder
                dateViewHolder.binding.dividerView.isVisible = true
                dateViewHolder.bind(data[position])
                bindingListener(holder.itemView, position)
            }
            else{
                val itemViewHolder: ItemMovementsViewHolder = holder as ItemMovementsViewHolder
                itemViewHolder.bind(data[position])
                bindingListener(holder.itemView, position)
            }
        }

    }

    //Function gets the data size
    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int{
        val dateType: Int = if (position == 0){
            0
        } else{
            if (data[position].detailDate != data[position - 1].detailDate){
                0
            } else{
                1
            }
        }

        return dateType
    }

    private fun bindingListener(holder: View, position: Int){
        //On Click Listener for each item on recyclerview, clickable items
        holder.setOnClickListener{
            //Create an intent to open details activity
            val moveIntent = Intent(context, EKTPMovementsDetailsActivity::class.java)
            //Get the item clicked on recyclerview
            var msg = data[position]
            //set the extras on intent from recycler view item
            var day = msg.detailDate.substring(0..2)
            var month = msg.detailDate.substring(3..4)
            var monthInt = month.toInt()
            var yearString = msg.detailDate.substring(5..9)
            var monthString = ""
            for(i in months.indices){
                if (i == monthInt-1){
                    monthString = months[i].substring(0..2)
                }
            }
            msg.detailDate = "$day$monthString$yearString"

            moveIntent.putExtra("detailDate", msg.detailDate)
            moveIntent.putExtra("detailTitle", msg.detailTitle)
            moveIntent.putExtra("detailConcept", msg.detailConcept)
            moveIntent.putExtra("detailAmount", msg.detailAmount)
            moveIntent.putExtra("detailStatus", msg.detailStatus)
            moveIntent.putExtra("detailName", msg.detailName)
            moveIntent.putExtra("detailFolio", msg.detailFolio)
            moveIntent.putExtra("detailAccount", msg.detailAccount)
            //launch intent
            context.startActivity(moveIntent)
        }
        //---
    }

}