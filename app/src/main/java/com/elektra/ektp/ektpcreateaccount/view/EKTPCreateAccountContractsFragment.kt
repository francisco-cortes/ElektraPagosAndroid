package com.elektra.ektp.ektpcreateaccount.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountContractsBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountContractsViewModel

class EKTPCreateAccountContractsFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountContractsBinding
    //private val contractsViewModel: EKTPCreateAccountContractsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountContractsBinding>(inflater,
            R.layout.fragment_ektp_create_account_contracts, container, false)

        binding.termsContinueButton.isEnabled = false

        binding.term1TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.term2TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.term3TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.term4TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.term5TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.term6TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.term7TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.term8TextView.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
        }

        binding.backAppbarButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKPTCreateAccountRegisterFormFragment)
        }

        binding.acceptanceCheckbox.setOnClickListener { view: View ->
            if (view is CheckBox) {
                val checked: Boolean = view.isChecked

                when (view.id) {
                    R.id.acceptanceCheckbox -> {
                        binding.termsContinueButton.isEnabled = checked
                    }
                }
            }
        }

        binding.termsContinueButton.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountCreatePassFragment)
        }

        return binding.root
    }

}