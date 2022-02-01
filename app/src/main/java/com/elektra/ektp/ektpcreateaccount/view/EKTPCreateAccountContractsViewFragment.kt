package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountContractsViewBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountContractsViewModel

class EKTPCreateAccountContractsViewFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountContractsViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountContractsViewBinding>(inflater,
        R.layout.fragment_ektp_create_account_contracts_view, container, false)

        binding.contractBodyTexView.text = LoremIpsum(100).toString()

        binding.backAppbarButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsViewFragment_to_EKTPCreateAccountContractsFragment)
        }

        return binding.root
    }
}