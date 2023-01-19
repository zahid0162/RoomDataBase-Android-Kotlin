package com.example.roomdataase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdataase.Department
import com.example.roomdataase.Employee
import com.example.roomdataase.EmployeeWithDepartment


@Dao
interface UserDao {


    @Insert
    fun insert(employee: Employee)

    @Insert
    fun insertDepartment(department: Department)

    @Update
    fun update(employee: Employee)

    @Delete
    fun delete(employee: Employee)

    @Query("SELECT * FROM employees")
    fun getAll(): LiveData<List<Employee>>

    @Query("SELECT * FROM departments")
    fun getAllDepartments(): LiveData<List<Department>>

    @Query("SELECT * FROM employees WHERE id = :employeeId")
    fun getById(employeeId: Int): Employee

    @Query("SELECT * FROM departments WHERE id = :departmentId")
    fun getDepartmentById(departmentId: Int): Department

    @Query("SELECT * FROM employees INNER JOIN departments ON employees.department_id = departments.id WHERE departments.name = :departmentName")
    fun getByDepartment(departmentName: String): List<Employee>

    @Query("SELECT * FROM employees WHERE salary > :minSalary")
    fun getByMinSalary(minSalary: Double): List<Employee>

    @Query("SELECT * FROM employees WHERE firstName LIKE :name OR lastName LIKE :name")
    fun getByName(name: String): List<Employee>

    @Query("SELECT * FROM employees INNER JOIN departments ON employees.department_id = departments.id")
    fun getAllWithDepartment(): LiveData<List<EmployeeWithDepartment>>

    @Query("delete  from employees")
    fun deleteAllEmployees()
}