package com.elektra.ektp.ektpforgottenpass.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpForgottenPassSuccessfulBinding

class EKTPForgottenPassSuccessfulFragment : Fragment() {

    private lateinit var binding: FragmentEktpForgottenPassSuccessfulBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_ektp_forgotten_pass_successful, container, false)

        return binding.root
    }

}