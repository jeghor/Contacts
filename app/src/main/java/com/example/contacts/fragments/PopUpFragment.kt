package com.example.contacts.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.contacts.R
import com.example.contacts.databinding.FragmentPopUpBinding

class PopUpFragment : DialogFragment() {

    private lateinit var binding: FragmentPopUpBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopUpBinding.inflate(layoutInflater)
        binding.okBtn.setOnClickListener { requireDialog().onBackPressed()
        requireActivity().onBackPressed()
        }
        binding.cancelBtn.setOnClickListener { requireDialog().onBackPressed()
            requireActivity().onBackPressed() }

        return binding.root
    }
}