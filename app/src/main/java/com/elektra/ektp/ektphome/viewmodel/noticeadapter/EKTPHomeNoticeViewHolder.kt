package com.elektra.ektp.ektphome.viewmodel.noticeadapter

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.R
import com.elektra.ektp.ektphome.model.EKTPHomeNoticeDataClass

class EKTPHomeNoticeViewHolder (view: View) : RecyclerView.ViewHolder(view){

    val noticeHeaderTextView = view.findViewById<TextView>(R.id.noticeHeaderTextView)
    val noticeBodyTextView = view.findViewById<TextView>(R.id.noticeBodyTextView)
    val noticeDateTextView =view.findViewById<TextView>(R.id.noticeDateTextView)

    fun render (homeNoticeModel: EKTPHomeNoticeDataClass){
        noticeHeaderTextView.text = homeNoticeModel.noticeHeader
        noticeBodyTextView.text = homeNoticeModel.noticeBody
        noticeDateTextView.text = homeNoticeModel.noticeDate
    }

}