package com.elektra.ektp.ektplogin.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elektra.ektp.R

class CocoNuFragment : Fragment() {

    companion object {
        fun newInstance() = CocoNuFragment()
    }

    private lateinit var viewModel: CocoNuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.coco_nu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CocoNuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}