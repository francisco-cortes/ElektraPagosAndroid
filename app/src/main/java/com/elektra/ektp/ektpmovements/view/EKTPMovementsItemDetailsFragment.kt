package com.elektra.ektp.ektpmovements.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpMovementsItemDetailsBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel

class EKTPMovementsItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEktpMovementsItemDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEktpMovementsItemDetailsBinding.inflate(inflater,
        container, false)

        //Recovery intent
        val bundle = this.arguments
        //Data recovery from intent extras
        val moveAmount = bundle?.getString("detailAmount").toString()
        val moveTitle = bundle?.getString("detailTitle").toString()
        val moveDate = bundle?.getString("detailDate").toString()
        val moveAccount = bundle?.getString("detailAccount").toString()
        val moveReceivedName = bundle?.getString("detailReceivedName").toString()
        val moveConcept = bundle?.getString("detailConcept").toString()
        val moveFolio = bundle?.getString("detailFolio").toString()
        val moveStatus = bundle?.getString("detailStatus").toString()
        val moveMTCN = bundle?.getString("detailMTCN").toString()
        val moveOperationType = bundle?.getString("detailOperationType").toString()
        val moveWithdrewName = bundle?.getString("detailWithdrewName").toString()
        val moveCents = bundle?.getString("detailCents").toString()

        val dataList = EKTPMovementsModel(
            moveAmount,
            moveTitle,
            moveDate,
            moveAccount,
            moveReceivedName,
            moveConcept,
            moveFolio,
            moveStatus,
            moveMTCN,
            false,
            moveOperationType,
            moveWithdrewName
        )
        //---

        with(binding){
            cashWithDrawalLinearLayout.isGone = moveTitle != "Retiro de efectivo"
            payOrderLinearLayout.isGone = moveTitle != "Orden de Pago"
            receiveDepositLinearLayout.isGone = moveTitle != "Recepci??n de dep??sito"

            //Assignment from intent data into layout
            movementsDetailsItem = dataList
            amountDetailCentsCardView.text = moveCents

            //OnClickListener for call shareDetails from card view
            shareButtonDetailsCardView.setOnClickListener {
                findNavController().navigate(R.id.action_EKTPMovementsItemDetailsFragment_to_EKTPDetailsToShareFragment2, bundle)
            }
            //---

            //OnClickListener for destroy activity and back to earlier activity
            backAppbarButton.setOnClickListener {
                activity?.finish()
            }
            //---
        }

        return binding.root
    }
}