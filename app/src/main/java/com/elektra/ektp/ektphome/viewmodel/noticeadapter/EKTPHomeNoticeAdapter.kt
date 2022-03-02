package com.elektra.ektp.ektphome.viewmodel.noticeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.R
import com.elektra.ektp.ektphome.model.EKTPHomeNoticeDataClass

class EKTPHomeNoticeAdapter(private val noticesList:List<EKTPHomeNoticeDataClass>) : RecyclerView.Adapter<EKTPHomeNoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EKTPHomeNoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EKTPHomeNoticeViewHolder(layoutInflater.inflate(R.layout.item_notice,parent,false))
    }

    override fun onBindViewHolder(holder: EKTPHomeNoticeViewHolder, position: Int) {
        val item = noticesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = noticesList.size
}