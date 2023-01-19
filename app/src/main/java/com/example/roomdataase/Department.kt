package com.example.roomdataase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class Department(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name")
    val name: String
)