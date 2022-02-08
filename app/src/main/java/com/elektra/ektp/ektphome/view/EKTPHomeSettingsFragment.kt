package com.elektra.ektp.ektphome.view

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

class EKTPHomeSettingsFragment : Fragment() {

    private lateinit var binding: FragmentEktpHomeSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate<FragmentEktpHomeSettingsBinding>(inflater,R.layout.fragment_ektp_home_settings, container, false)

        binding.helpFrame.setOnClickListener { view : View ->
            val intent = Intent(activity, EKTPHomeSettingsHelpActivity::class.java)
            val context = view.context
            context.startActivity(intent)
        }

        binding.changePassFrame.setOnClickListener {view: View ->
            val intent = Intent(activity, EKTPForgottenPassActivity::class.java)
            val context = view.context
            context.startActivity(intent)
        }
        return binding.root
    }
}