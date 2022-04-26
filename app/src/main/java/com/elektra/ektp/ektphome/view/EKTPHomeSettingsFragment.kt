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
import com.elektra.ektp.databinding.FragmentEktpHomeSettingsBinding
import com.elektra.ektp.ektpforgottenpass.view.EKTPForgottenPassActivity
import com.elektra.ektp.ektplogin.view.EKTPLoginActivity

class EKTPHomeSettingsFragment : Fragment() {

    private lateinit var binding: FragmentEktpHomeSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_ektp_home_settings, container, false)
        //layout control
        with(binding){
            helpFrame.setOnClickListener {
                openActivity(EKTPHomeSettingsHelpActivity())//opens help
            }

            changePassFrame.setOnClickListener {
                openActivity(EKTPForgottenPassActivity())//open forgotten pass activity
            }

            logOutTextView.setOnClickListener{
                openActivity(EKTPLoginActivity())
                activity?.finish()
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