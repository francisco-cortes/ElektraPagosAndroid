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

    companion object {
        fun newInstance() = EKTPReceiveMoneyMTCNFragment()
    }

    private lateinit var viewModel: EKTPReceiveMoneyMTCNViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ektp_receive_money_mtcn_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EKTPReceiveMoneyMTCNViewModel::class.java)
        // TODO: Use the ViewModel
    }

}