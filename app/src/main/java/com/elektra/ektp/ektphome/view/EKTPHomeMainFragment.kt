package com.elektra.ektp.ektphome.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpHomeMainBinding
import com.elektra.ektp.ektphome.viewmodel.EKTPHomeMainViewModel
import com.elektra.ektp.ektpmovements.view.EKTPMovementsActivity
import com.elektra.ektp.ektpreceivemoney.view.EKTPReceiveMoneyActivity

class EKTPHomeMainFragment : Fragment() {

    private lateinit var binding: FragmentEktpHomeMainBinding

    private val viewModel = EKTPHomeMainViewModel()//instance for fragment viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpHomeMainBinding>(inflater,R.layout.fragment_ektp_home_main,container,false)
        //layout controls
        with(binding){
            userNameTextView.text = "${viewModel.getUserHomeMain()} !" // set the user name next to "hi" text

            receiveMoneyImageButton.setOnClickListener {
                openActivity(EKTPReceiveMoneyActivity())// open Receive Money Activity
            }
            balanceMovementsImageButton.setOnClickListener {
                openActivity(EKTPMovementsActivity())// open Movements activity
            }
        }
        //---
        return binding.root
    }
    // function to open another activity trough intent
    private fun openActivity(activityName: Activity){
        val intent = Intent(activity, activityName::class.java)
        val context = view?.context
        context?.startActivity(intent)
    }
    //---
}