package com.elektra.ektp.ektpmovements.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpMovementsListBinding
import com.elektra.ektp.ektpmovements.model.EKTPMovementsModel
import com.elektra.ektp.ektpmovements.viewmodel.EKTPMovementsRecyclerViewAdapter

class EKTPMovementsListFragment : Fragment() {

    private lateinit var binding: FragmentEktpMovementsListBinding
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val data = listOf(
            EKTPMovementsModel("+500","Orden de Pago", "23/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "24/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+500","Orden de Pago", "25/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "26/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "27/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+500","Orden de Pago", "28/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "29/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+500","Orden de Pago", "30/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "31/12/2021","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "01/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+500","Orden de Pago", "02/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "03/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("+500","Orden de Pago", "04/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "05/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa"),
            EKTPMovementsModel("-500","Orden de Pago", "06/01/2022","Cuenta destino ****1234",
                "Francisco Javier Cortes Torres", "Envío de dinero", "12345678",
                "Transferencia exitosa")
        )

        manager = LinearLayoutManager(this.context)

        binding.movementsRecyclerView.apply{
            adapter = EKTPMovementsRecyclerViewAdapter(this.context,data)
            layoutManager = manager

        }

        binding.backAppbarButton.setOnClickListener { view: View ->
            activity?.finish()
        }

        return binding.root
    }

    companion object {
        fun newInstance(): EKTPMovementsListFragment {
            return EKTPMovementsListFragment()
        }
    }

}