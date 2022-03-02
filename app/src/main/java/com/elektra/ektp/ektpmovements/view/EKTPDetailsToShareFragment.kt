package com.elektra.ektp.ektpmovements.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.databinding.FragmentEktpDetailsToShareBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel

class EKTPDetailsToShareFragment : Fragment() {

    private lateinit var binding: FragmentEktpDetailsToShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Overriding obBackPressed to popBackStack fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEktpDetailsToShareBinding.inflate(inflater, container, false)

        val bundle = this.arguments
        //Data recovery from intent extras
        val moveName = bundle?.getString("detailName").toString()
        val moveDate = bundle?.getString("detailDate").toString()
        val moveTitle = bundle?.getString("detailTitle").toString()
        val moveConcept = bundle?.getString("detailConcept").toString()
        val moveAmount = bundle?.getString("detailAmount").toString()
        val moveStatus = bundle?.getString("detailStatus").toString()
        val moveFolio = bundle?.getString("detailFolio").toString()
        val moveAccount = bundle?.getString("detailAccount").toString()
        val moveMTCN = bundle?.getString("detailMTCN").toString()
        val dataList = EKTPMovementsModel(
            moveAmount,
            moveTitle,
            moveDate,
            moveAccount,
            moveName,
            moveConcept,
            moveFolio,
            moveStatus,
            moveMTCN
        )
        //---

        binding.movementsDetailsItem = dataList

        return binding.root
    }

}