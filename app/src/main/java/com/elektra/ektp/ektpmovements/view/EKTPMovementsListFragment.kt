package com.elektra.ektp.ektpmovements.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpMovementsListBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel
import com.elektra.ektp.ektpmovements.viewmodel.EKTPMovementsRecyclerViewAdapter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class EKTPMovementsListFragment : Fragment() {

    //Global databinding access variable
    private lateinit var binding: FragmentEktpMovementsListBinding
    //Manager variable for recycler View Adapter
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Functions OnBackPressed for destroy activity on clicking
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
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_movements_list, container, false)

        //Filling data model list
        var data = listOf(
            EKTPMovementsModel("-$500",
                "Retiro de efectivo",
                "01/01/2022",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso",
                ""),
            EKTPMovementsModel("+$600",
                "Orden de Pago",
                "01/01/2022",
                "Cuenta ****1234",
                "Francisco Javier Cortes Torres",
                "Envío de dinero",
                "12345678",
                "Exitosa",
                "12345678RS ",
                true,
                "",
                ""),
            EKTPMovementsModel("-$700",
                "Retiro de efectivo",
                "01/01/2022",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso",
                ""),
            EKTPMovementsModel(
                "+$800",
                "Orden de Pago",
                "01/01/2022",
                "Cuenta ****1234",
                "Francisco Javier Cortes Torres",
                "Envío de dinero",
                "12345678",
                "Exitosa",
                "12345678RS ",
                true,
                "",
                ""),
            EKTPMovementsModel("-$900",
                "Retiro de efectivo",
                "01/01/2022",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso",
                ""),
            EKTPMovementsModel("-$100",
                "Retiro de efectivo",
                "01/01/2022",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso"),
            EKTPMovementsModel("+$500",
                "Recepción de depósito",
                "30/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                true, "Traspaso",
                "Rubén González Ramírez\n42291765235722183"),
            EKTPMovementsModel("-$600",
                "Retiro de efectivo",
                "30/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso",
                ""),
            EKTPMovementsModel("-$500",
                "Retiro de efectivo",
                "27/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso"),
            EKTPMovementsModel(
                "+$600",
                "Orden de Pago",
                "27/12/2021",
                "Cuenta ****1234",
                "Francisco Javier Cortes Torres",
                "Envío de dinero",
                "12345678",
                "Exitosa",
                "12345678RS ",
                true,
                "",
                ""),
            EKTPMovementsModel("-$700",
                "Retiro de efectivo",
                "27/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso",
                ""),
            EKTPMovementsModel("+$500",
                "Recepción de depósito",
                "23/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                true,
                "Traspaso",
                "Rubén González Ramírez\n42291765235722183"),
            EKTPMovementsModel("-$600",
                "Retiro de efectivo",
                "23/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso",
                ""),
            EKTPMovementsModel("+$700",
                "Recepción de depósito",
                "23/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                true,
                "Traspaso",
                "Rubén González Ramírez\n42291765235722183"),
            EKTPMovementsModel("-$800",
                "Retiro de efectivo",
                "23/12/2021",
                "Cuenta ****1234",
                "",
                "",
                "12345678",
                "Exitosa",
                "",
                false,
                "Traspaso",
                "")
        )
        //---
        val context = this.context
        //Set context to manager
        manager = LinearLayoutManager(context)
        if ((0..1).random() == 2){ //changed 0 for 2
            data = emptyList()
        }


        var months: Array<String> = context?.resources!!.getStringArray(R.array.months)

        if(data.isNullOrEmpty()){
            binding.emptyDataFrame.isVisible = true
        }
        else{
            parseDatesData(data, months)
            binding.emptyDataFrame.isVisible = false
            //Setting recyclerview Adapter
            binding.movementsRecyclerView.apply{
                //pass context and data list
                adapter = EKTPMovementsRecyclerViewAdapter(this.context,data)
                layoutManager = manager
            }
            //---
        }

        binding.movementsButton.setOnClickListener{
                view: View ->
            activity?.finish()
        }

        //OnClickListener for appbar back button
        binding.backAppbarButton.setOnClickListener { view: View ->
            activity?.finish()
        }
        //---

        return binding.root
    }

    private fun parseDatesData(data: List<EKTPMovementsModel>, months: Array<String>) {
        for(i in data.indices){
            var mesString = data[i].detailDate.substring(3..4)
            var mesInt = mesString.toInt()-1
            for(j in months.indices ){
                if (mesInt == j){
                    mesString = months[j]
                    data[i].detailDate = data[i].detailDate.substring(0..2) + mesString + data[i].detailDate.substring(5..9)
                }
            }
        }
    }

    //Companion object for create instance from MovementsList
    companion object {
        fun newInstance(): EKTPMovementsListFragment {
            return EKTPMovementsListFragment()
        }
    }
    //---

}