package com.example.roomdataase

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdataase.databinding.FragmentListBinding
import com.example.roomdataase.viewModel.UserViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val id=requireActivity().intent.getIntExtra("id",1)
        println(id)
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.getByDepartmentName(id)
//        mUserViewModel.readAllEmployee.observe(viewLifecycleOwner, Observer { user ->
//            adapter.setData(user)
//        })
        mUserViewModel.getByDepartmentName.observe(viewLifecycleOwner){
            println("Position: $id")
            adapter.setData(it)
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    @Deprecated("Deprecated in Java", ReplaceWith("inflater.inflate(R.menu.delete_menu, menu)"))
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteAllEmployees()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT)
                .show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything ?")
        builder.setMessage("Are you sure to remove everything ?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // <- whenever we destroy our fragment, _binding is set to null. Hence it will avoid memory leaks.
    }

}