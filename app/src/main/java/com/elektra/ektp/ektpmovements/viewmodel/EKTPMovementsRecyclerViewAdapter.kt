package com.elektra.ektp.ektpmovements.viewmodel

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.databinding.ItemMovementBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel

class EKTPMovementsRecyclerViewAdapter(
    private val context: Context,
    private val data: List<EKTPMovementsModel>
) : RecyclerView.Adapter<EKTPMovementsRecyclerViewAdapter.MovementsViewHolder>() {

    inner class MovementsViewHolder(val binding: ItemMovementBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: EKTPMovementsModel){
            binding.movementsListItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsViewHolder {

    }

    override fun onBindViewHolder(holder: MovementsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        
    }

}