package com.elektra.ektp.ektpcreateaccount.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountSuccessfulBinding
import com.elektra.ektp.ektplogin.view.EKTPLoginActivity

class EKTPCreateAccountSuccessfulFragment : Fragment() {

    //Global databinding access variable
    private lateinit var binding: FragmentEktpCreateAccountSuccessfulBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                activity?.onBackPressed()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountSuccessfulBinding>(inflater,
            R.layout.fragment_ektp_create_account_successful, container, false)

        //The account has been created successfully, user can return to home
        binding.buttonSuccessful.setOnClickListener {view: View ->
            activity?.finish()
            val intent = Intent(activity, EKTPLoginActivity::class.java)
            val context = view?.context
            context?.startActivity(intent)
        }

        return binding.root
    }

}