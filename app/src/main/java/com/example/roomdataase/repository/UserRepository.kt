
package com.example.roomdataase.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdataase.Department
import com.example.roomdataase.Employee
import com.example.roomdataase.EmployeeWithDepartment
import com.example.roomdataase.data.UserDao
import com.example.roomdataase.model.User


class UserRepository(private val userDao: UserDao) {
    val readAllEmployees: LiveData<List<Employee>> = userDao.getAll()
    val readAllDepartments: LiveData<List<Department>> = userDao.getAllDepartments()
    val employeeWithDepartment:LiveData<List<EmployeeWithDepartment>> = userDao.getAllWithDepartment()
    val getByDepartmentName=MutableLiveData<List<Employee>>()
    val getDepartmentById=MutableLiveData<Department>()
    fun insert(employee: Employee) {
        userDao.insert(employee)
    }

    fun update(employee: Employee) {
        userDao.update(employee)
    }

    fun delete(employee: Employee) {
        userDao.delete(employee)
    }
    fun getByDepartmentName(departmentName: String){
        getByDepartmentName.postValue(userDao.getByDepartment(departmentName))
    }
    fun getDepartmentById(departmentId: Int){
        getDepartmentById.postValue(userDao.getDepartmentById(departmentId))
    }
    fun  getByMinSalary(minSalary: Double): List<Employee>{
        return userDao.getByMinSalary(minSalary)
    }
    fun getByName(name: String): List<Employee>{
        return userDao.getByName(name)
    }

    fun deleteAllEmployees() {
        userDao.deleteAllEmployees()
    }
    fun insertDepartment(department: Department){
        userDao.insertDepartment(department)
    }
}