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

    inner class MovementsViewHolder(val binding: ItemMovementBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: EKTPMovementsModel){
            binding.movementsListItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = ItemMovementBinding.inflate(inflater, parent, false)

        return MovementsViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MovementsViewHolder, position: Int) {
        holder.bind(data[position])
        holder.binding.root.setOnClickListener {
            var moveIntent = Intent(context, EKTPMovementsDetailsActivity::class.java)
            val msg = data[position]
            moveIntent.putExtra("detailDate", msg.detailDate)
            moveIntent.putExtra("detailTitle", msg.detailTitle)
            moveIntent.putExtra("detailConcept", msg.detailConcept)
            moveIntent.putExtra("detailAmount", msg.detailAmount)
            moveIntent.putExtra("detailStatus", msg.detailStatus)
            moveIntent.putExtra("detailName", msg.detailName)
            moveIntent.putExtra("detailFolio", msg.detailFolio)
            moveIntent.putExtra("detailAccount", msg.detailAccount)
            context.startActivity(moveIntent)
            Toast.makeText(holder.binding.root.context, "Elemento ${position + 1} seleccionado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = data.size

}