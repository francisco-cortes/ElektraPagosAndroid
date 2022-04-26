package com.elektra.ektp.ektpcreateaccount.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountContractsBinding
import com.elektra.ektp.ektppdfviewer.EKTPPDFViewerActivity
import com.elektra.ektp.ektpsharedpreferences.EKTPUserApplication

class EKTPCreateAccountContractsFragment : Fragment() {

    //Global databinding access variable
    private lateinit var binding: FragmentEktpCreateAccountContractsBinding

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
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ektp_create_account_contracts, container, false)

        with(binding){
            termsContinueButton.isEnabled = false
                    acceptanceCheckbox.isChecked = false

                //onClickListener on TextView to open contract title
                term1TextView.setOnClickListener {
                    //view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
                    intentOpener("ejemplo_contrato_banca_digital.pdf")
                }
            //----

            //onClickListener on TextView to open contract title
            term2TextView.setOnClickListener {
                intentOpener("ejemplo_contrato_banca_digital.pdf")
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term3TextView.setOnClickListener {
                        intentOpener("ejemplo_contrato_banca_digital.pdf")
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term4TextView.setOnClickListener {
                        intentOpener("ejemplo_contrato_banca_digital.pdf")
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term5TextView.setOnClickListener {
                        intentOpener("ejemplo_contrato_banca_digital.pdf")
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term6TextView.setOnClickListener {
                        intentOpener("ejemplo_contrato_banca_digital.pdf")
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term7TextView.setOnClickListener {
                        intentOpener("ejemplo_contrato_banca_digital.pdf")
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term8TextView.setOnClickListener {
                        intentOpener("contratoenPDF.pdf")
            }
                    //---

                    //onClickListener on appBar BackButton to popBackStack fragment
                    backAppbarButton.setOnClickListener {
                findNavController().popBackStack()
            }
                    //---

                    //OnClickListener to check the user acceptance of contracts
                    acceptanceCheckbox.setOnClickListener { view: View ->
                if (view is CheckBox) {
                    val checked: Boolean = view.isChecked

                    when (view.id) {
                        R.id.acceptanceCheckbox -> {
                            termsContinueButton.isEnabled = checked
                        }
                    }
                }
            }
                    //--

                    //onClickListener on termsButton to listen for forward advance in create account
                    termsContinueButton.setOnClickListener {
                        EKTPUserApplication.preferences.saveLocalStatus("Registrado")
                //view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountCreatePassFragment)
                        fragmentReplacer(EKTPCreateAccountCreatePassFragment())
            }
                //--

                return root
        }
    }

    private fun intentOpener(selectedContract: String){
        val intent = Intent(requireContext(), EKTPPDFViewerActivity::class.java)
        intent.putExtra("selected_contract", selectedContract)
        startActivity(intent)
    }
    private fun fragmentReplacer(fragment: Fragment){
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.CreateAccountNavigatorHost,fragment)
            .commitNow()//open the biometric login fragment
    }

}