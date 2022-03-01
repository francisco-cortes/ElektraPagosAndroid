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
            EKTPMovementsModel("+500","Orden de Pago", "23/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-600","Orden de Pago", "23/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+700","Orden de Pago", "23/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-800","Orden de Pago", "23/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "27/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+600","Orden de Pago", "27/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-700","Orden de Pago", "27/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+500","Orden de Pago", "30/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-600","Orden de Pago", "30/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "01/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+600","Orden de Pago", "01/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-700","Orden de Pago", "01/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+800","Orden de Pago", "01/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-900","Orden de Pago", "01/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-100","Orden de Pago", "01/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa")
        )
        //---

        //Set context to manager
        manager = LinearLayoutManager(this.context)
        if ((0..1).random() == 0){
            data = emptyList()
        }

        if(data.isNullOrEmpty()){
            binding.emptyDataFrame.isVisible = true
        }
        else{
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

    //Companion object for create instance from MovementsList
    companion object {
        fun newInstance(): EKTPMovementsListFragment {
            return EKTPMovementsListFragment()
        }
    }
    //---

}