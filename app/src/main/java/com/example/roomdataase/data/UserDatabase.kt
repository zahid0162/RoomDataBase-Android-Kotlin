package com.example.roomdataase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdataase.Department
import com.example.roomdataase.Employee


@Database(
    entities = [Employee::class,Department::class],
    version = 1,
    exportSchema = true
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    var rdc: Callback = object : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            db.beginTransaction()
            db.execSQL(
                "INSERT INTO departments('name') VALUES(?)",
                arrayOf<Any?>("Mobile")
            )
            db.execSQL(
                "INSERT INTO departments('name') VALUES(?)",
                arrayOf<Any?>("Web")
            )
        }
    }
}