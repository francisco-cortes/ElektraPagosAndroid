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
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elektra.ektp.R
import com.elektra.ektp.databinding.FragmentEktpCreateAccountContractsBinding
import com.elektra.ektp.ektpcreateaccount.viewmodel.EKTPCreateAccountContractsViewModel
import com.elektra.ektp.ektppdfviewer.EKTPPDFViewerActivity

class EKTPCreateAccountContractsFragment : Fragment() {

    //Global databinding access variable
    private lateinit var binding: FragmentEktpCreateAccountContractsBinding
    //ViewModel access var
    private val contractsViewModel: EKTPCreateAccountContractsViewModel by viewModels()

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
        binding = DataBindingUtil.inflate<FragmentEktpCreateAccountContractsBinding>(inflater,
            R.layout.fragment_ektp_create_account_contracts, container, false)

        with(binding){
            termsContinueButton.isEnabled = false
                    acceptanceCheckbox.isChecked = false

                //onClickListener on TextView to open contract title
                term1TextView.setOnClickListener { view: View ->
                    //view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
                    val intent = Intent(requireContext(), EKTPPDFViewerActivity::class.java)
                    intent.putExtra("selected_contract", "mm@email.com")
                    startActivity(intent)
                }
            //----

            //onClickListener on TextView to open contract title
            term2TextView.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term3TextView.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term4TextView.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term5TextView.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term6TextView.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term7TextView.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
            }
                    //---

                    //onClickListener on TextView to open contract title
                    term8TextView.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountContractsViewFragment)
            }
                    //---

                    //onClickListener on appBar BackButton to popBackStack fragment
                    backAppbarButton.setOnClickListener { view: View ->
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
                    termsContinueButton.setOnClickListener {view: View ->
                view.findNavController().navigate(R.id.action_EKTPCreateAccountContractsFragment_to_EKTPCreateAccountCreatePassFragment)
            }
                //--

                return root
        }
    }

}