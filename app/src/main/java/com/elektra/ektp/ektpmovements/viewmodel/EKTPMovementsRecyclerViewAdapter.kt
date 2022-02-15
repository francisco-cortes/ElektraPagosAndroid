package com.elektra.ektp.ektpmovements.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.databinding.ItemMovementBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel
import com.elektra.ektp.ektpmovements.view.EKTPMovementsDetailsActivity

class EKTPMovementsRecyclerViewAdapter(
    private val context: Context,
    private val data: List<EKTPMovementsModel>
) : RecyclerView.Adapter<EKTPMovementsRecyclerViewAdapter.MovementsViewHolder>() {

    //ViewHolder function to fill recycler view
    inner class MovementsViewHolder(val binding: ItemMovementBinding) : RecyclerView.ViewHolder(binding.root){
        //Function get de data model structure
        fun bind(item: EKTPMovementsModel){
            //Set databinding from data model into layout
            binding.movementsListItem = item
        }
        //---
    }
    //---

    //View holder to inflate view that contains the viewHolder recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsViewHolder {
        //inflater value context
        val inflater = LayoutInflater.from(parent.context)

        //Inflater layout child to recyclerview
        val listItemBinding = ItemMovementBinding.inflate(inflater, parent, false)

        //Return view
        return MovementsViewHolder(listItemBinding)
    }
    //---

    //Bind view holder to fill each cell on recycler view
    override fun onBindViewHolder(holder: MovementsViewHolder, position: Int) {
        //get position
        holder.bind(data[position])

        //On Click Listener for each item on recyclerview, clickable items
        holder.binding.root.setOnClickListener {
            //Create an intent to open details activity
            var moveIntent = Intent(context, EKTPMovementsDetailsActivity::class.java)
            //Get the item clicked on recyclerview
            val msg = data[position]
            //set the extras on intent from recycler view item
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

    //Function gets the data size
    override fun getItemCount(): Int = data.size

}