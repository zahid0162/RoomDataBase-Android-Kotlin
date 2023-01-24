package com.example.roomdataase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.roomdataase.Department
import com.example.roomdataase.Employee
import com.example.roomdataase.EmployeeWithDepartment
import com.example.roomdataase.data.UserDatabase
import com.example.roomdataase.repository.UserRepository
import com.example.roomdataase.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// UserViewModel provides users data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.

class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllEmployee: LiveData<List<Employee>>
    val readAllDepartment: LiveData<List<Department>>
    val employeeWithDepartment: LiveData<List<EmployeeWithDepartment>>
    val getByDepartmentName:LiveData<List<Employee>>
    val getDepartmentById:LiveData<Department>
    private val repository: UserRepository


    init {
        val db = Room.databaseBuilder(
            application.applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).build()
        val userDao=db.userDao
        repository= UserRepository(userDao)
        readAllEmployee = repository.readAllEmployees
        employeeWithDepartment=repository.employeeWithDepartment
        getByDepartmentName=repository.getByDepartmentName
        getDepartmentById=repository.getDepartmentById
        readAllDepartment=repository.readAllDepartments
    }

    fun add(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(employee)
        }
    }

    fun update(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(employee)
        }
    }

    fun delete(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(employee)
        }
    }



    fun getDepartmentByID(departmentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
             repository.getDepartmentById(departmentId)
        }
    }

    fun getByDepartmentName(name:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getByDepartmentName(name)
        }
    }

    fun deleteAllEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllEmployees()
        }
    }
    fun insertDepartment(department: Department){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDepartment(department)
        }
    }
}