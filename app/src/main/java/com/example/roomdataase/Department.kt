package com.example.roomdataase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class Department(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "name")
    val name: String
)