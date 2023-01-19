package com.example.roomdataase

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdataase.databinding.FragmentUpdateBinding
import com.example.roomdataase.model.User
import com.example.roomdataase.viewModel.UserViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.addFirstNameEt.setText(args.currentUser.firstName)
        binding.addLastNameEt.setText(args.currentUser.lastName)
        binding.addAgeEt.setText(args.currentUser.salary.toString())
        binding.addDepIdEt.setText(args.currentUser.departmentId.toString())
        binding.addEmailEt.setText(args.currentUser.email)
        binding.addBtn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = Integer.parseInt(binding.addAgeEt.text.toString())

        if (inputCheck(firstName, lastName, binding.addAgeEt.text)) {
                val user = Employee(
                    0,
                    firstName,
                    lastName,
                    binding.addEmailEt.text.toString(),
                    binding.addDepIdEt.text.toString().toInt(),
                    age.toString().toDouble()
                )

                mUserViewModel.update(user)
                Toast.makeText(requireContext(), "Updated Successfully !", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Please fill all fields !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    @Deprecated("Deprecated in Java", ReplaceWith("inflater.inflate(R.menu.delete_menu, menu)"))
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.delete(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are you sure to remove ${args.currentUser.firstName} ?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}