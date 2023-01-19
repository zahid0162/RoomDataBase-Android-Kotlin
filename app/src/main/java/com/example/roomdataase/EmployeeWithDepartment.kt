package com.example.roomdataase

import androidx.room.Embedded
import androidx.room.Relation

data class EmployeeWithDepartment (
    @Relation(
        parentColumn = "id",
        entityColumn = "department_id"
    )
    val employee: List<Employee>,
    @Embedded
    val department: Department
)

