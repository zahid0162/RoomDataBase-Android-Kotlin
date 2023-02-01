package com.example.roomdataase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdataase.databinding.ActivityAddDepartmentBinding
import com.example.roomdataase.viewModel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddDepartment : AppCompatActivity() {
    lateinit var binding: ActivityAddDepartmentBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDepartmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        populateDataBase()
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        mUserViewModel.readAllDepartment.observe(this) { user ->
            val adapter = DepartmentListAdapter(user as MutableList<Department>) {
                val intent = Intent(Intent(this@AddDepartment, MainActivity::class.java))
                intent.putExtra("id", it.id)
                startActivity(intent)

            }
            recyclerView.adapter = adapter
            adapter.setData(user)
        }
        binding.apply {

            addBtn.setOnClickListener {
                mUserViewModel.insertDepartment(
                    Department(
                        depId.text.toString().toInt(),
                        depName.text.toString(),
                        ""
                    )
                )
                depId.setText("")
                depName.setText("")

            }
            addBtn2.visibility = View.GONE


        }
    }
    private fun populateDataBase() {
            (1..10).forEach { index ->
                mUserViewModel.insertDepartment(Department(name =  "Department${System.currentTimeMillis()}", value = ""))
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}