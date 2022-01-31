package com.elektra.ektp.ektpcreateaccount.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountContractsBinding

class EKTPCreateAccountContractsFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountContractsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountContractsBinding>(inflater,
            R.layout.fragment_ektp_create_account_contracts, container, false)

        binding.termsContinueButton.isEnabled = false

        binding.term1TextView.setOnClickListener { view: View ->

        }

        binding.term2TextView.setOnClickListener { view: View ->

        }

        binding.term3TextView.setOnClickListener { view: View ->

        }

        binding.term4TextView.setOnClickListener { view: View ->

        }

        binding.term5TextView.setOnClickListener { view: View ->

        }

        binding.term6TextView.setOnClickListener { view: View ->

        }

        binding.term7TextView.setOnClickListener { view: View ->

        }

        binding.term8TextView.setOnClickListener { view: View ->

        }

        binding.backAppbarButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKPTCreateAccountRegisterFormFragment)
        }

        return binding.root
    }

}