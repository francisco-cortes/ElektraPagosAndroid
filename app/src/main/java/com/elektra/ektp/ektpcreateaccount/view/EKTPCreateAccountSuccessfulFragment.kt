package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountSuccessfulBinding

class EKTPCreateAccountSuccessfulFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountSuccessfulBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountSuccessfulBinding>(inflater,
            R.layout.fragment_ektp_create_account_successful, container, false)

        return binding.root
    }

}