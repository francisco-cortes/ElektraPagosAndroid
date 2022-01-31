package com.elektra.ektp.ektpcreateaccount.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentCreateAccountBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountViewModel

class EKTPCreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding
    private val createAccountViewModel: EKTPCreateAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCreateAccountBinding>(inflater,
            R.layout.fragment_create_account, container, false)

        binding.insertName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (createAccountViewModel.checkValidInput(s.toString())){
                    binding.insertName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.invalidNameText.isVisible = false
                }
                else{
                    binding.insertName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = false
                    binding.invalidNameText.isVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (createAccountViewModel.checkValidInput(s.toString())){
                    binding.insertName.setBackgroundResource(R.drawable.rounded_rectangle_gray)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields()
                    binding.invalidNameText.isVisible = false
                }
                else{
                    binding.insertName.setBackgroundResource(R.drawable.validation_edit_text)
                    binding.button.isEnabled = createAccountViewModel.checkFilledFields()
                    binding.invalidNameText.isVisible = true
                }
            }

        })

        return binding.root
    }

}