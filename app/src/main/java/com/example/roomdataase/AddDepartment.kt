package com.example.roomdataase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdataase.databinding.ActivityAddDepartmentBinding
import com.example.roomdataase.viewModel.UserViewModel

class AddDepartment : AppCompatActivity() {
    lateinit var binding: ActivityAddDepartmentBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddDepartmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val adapter = DepartmentListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mUserViewModel.readAllDepartment.observe(this) { user ->
            adapter.setData(user)
        }
        binding.apply {

            addBtn.setOnClickListener {
                mUserViewModel.insertDepartment(Department(depId.text.toString().toInt(),depName.text.toString()))
                depId.setText("")
                depName.setText("")

                mUserViewModel.readAllDepartment.observe(this@AddDepartment) { user ->
                    adapter.setData(user)
                }
            }
            addBtn2.setOnClickListener {
                startActivity(Intent(this@AddDepartment,MainActivity::class.java))
            }

        }
    }
}