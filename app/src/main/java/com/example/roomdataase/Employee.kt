package com.example.roomdataase

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "employees", foreignKeys = [ForeignKey(entity = Department::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("department_id"),
    onDelete = ForeignKey.CASCADE)]
)
data class Employee(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "department_id")
    val departmentId: Int,
    @ColumnInfo(name = "salary")
    val salary: Double
) : Serializable

data class DepartmentWithAllEmployees(

    @Embedded val dep: Department,
    @Relation(
        parentColumn = "id",
        entityColumn = "department_id"
    )
    val dogs: List<Employee>
)