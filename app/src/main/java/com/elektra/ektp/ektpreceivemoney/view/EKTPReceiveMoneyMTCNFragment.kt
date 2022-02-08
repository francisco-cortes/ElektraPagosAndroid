package com.elektra.ektp.ektpreceivemoney.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elektra.ektp.R
import com.elektra.ektp.ektpreceivemoney.viewmodel.EKTPReceiveMoneyMTCNViewModel

class EKTPReceiveMoneyMTCNFragment : Fragment() {

    private lateinit var binding: EKTPReceiveMoneyMTCNFragment
    private var toolTipShow = false
    private val viewModel = EKTPReceiveMoneyMTCNViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ektp_receive_money_mtcn_fragment, container, false)
    }
}