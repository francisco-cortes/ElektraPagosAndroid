package com.elektra.ektp.ektphome.view

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpHomeMainBinding>(inflater,R.layout.fragment_ektp_home_main,container,false)
        binding.receiveMoneyImageButton.setOnClickListener {
            val intent = Intent(activity, EKTPReceiveMoneyActivity::class.java)
            val context = view?.context
            context?.startActivity(intent)
        }
        //binding.userNameTextView.text = EKTPHomeMainViewModel().getUserHomeMain() + "!"

        binding.balanceMovementsImageButton.setOnClickListener {view: View->
            val intent = Intent(activity, EKTPMovementsActivity::class.java)
            val context = view.context
            context.startActivity(intent)
        }

        return binding.root
    }
}