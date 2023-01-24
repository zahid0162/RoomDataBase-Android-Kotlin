package com.example.roomdataase

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdataase.Employee
import com.example.roomdataase.R
import com.example.roomdataase.model.User
import com.example.roomdataase.viewModel.UserViewModel
import com.example.roomdataase.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {

        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEt.text
        if(inputCheck(firstName, lastName, age)) {
                val user = Employee(firstName =  firstName,lastName= lastName,email= binding.addEmailEt.text.toString(), departmentId = binding.addDepIdEt.text.toString().toInt(), salary =age.toString().toDouble())
                mUserViewModel.add(user)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}