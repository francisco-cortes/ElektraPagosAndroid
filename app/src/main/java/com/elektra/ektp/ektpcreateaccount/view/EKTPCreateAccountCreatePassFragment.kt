package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountCreatePassBinding

class EKTPCreateAccountCreatePassFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountCreatePassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountCreatePassBinding>(inflater,
            R.layout.fragment_ektp_create_account_create_pass, container, false)

        return binding.root
    }

}