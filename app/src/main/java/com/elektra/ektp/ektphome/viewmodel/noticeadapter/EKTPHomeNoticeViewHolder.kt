package com.elektra.ektp.ektphome.viewmodel.noticeadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.databinding.ItemNoticeBinding
import com.elektra.ektp.ektphome.model.EKTPHomeNoticeDataClass

class EKTPHomeNoticeViewHolder (view: View) : RecyclerView.ViewHolder(view){

    val binding = ItemNoticeBinding.bind(view)

    fun render (homeNoticeModel: EKTPHomeNoticeDataClass){
        with(binding){
            noticeHeaderTextView.text = homeNoticeModel.noticeHeader
            noticeBodyTextView.text = homeNoticeModel.noticeBody
            noticeDateTextView.text = homeNoticeModel.noticeDate
        }
    }
}