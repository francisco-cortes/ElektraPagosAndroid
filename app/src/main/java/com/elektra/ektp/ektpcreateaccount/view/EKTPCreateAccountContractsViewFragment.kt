package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountContractsViewBinding

class EKTPCreateAccountContractsViewFragment : Fragment() {

    private lateinit var binding: FragmentEktpCreateAccountContractsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_ektp_create_account_contracts_view, container, false)

        binding.contractBodyTexView.text = LoremIpsum(100).toString()

        binding.backAppbarButton.setOnClickListener{
            findNavController().popBackStack()
        }

        return binding.root
    }
}