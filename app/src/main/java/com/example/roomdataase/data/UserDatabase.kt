package com.example.roomdataase.data

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdataase.Department
import com.example.roomdataase.Employee


@Database(
    entities = [Employee::class,Department::class],
    version = 2,
    exportSchema = true
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
//    @RenameColumn(tableName = "User", fromColumnName = "created", toColumnName = "createdAt")
//    class Migration1To: AutoMigrationSpec
//
    companion object {
        val migration1To2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS Project (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Name` TEXT NOT NULL)")
            }
        }
    }

//    companion object {
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getDatabase(context: Context): UserDatabase{
//            val tempInstance = INSTANCE
//
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java,
//                    "user_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }

}