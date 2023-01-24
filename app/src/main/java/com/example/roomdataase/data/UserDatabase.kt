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
    version = 1,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2),
//        AutoMigration(from = 2, to = 3, spec = UserDatabase.Migration2To3::class),
//    ],
    exportSchema = true
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
//    @RenameColumn(tableName = "User", fromColumnName = "created", toColumnName = "createdAt")
//    class Migration2To3: AutoMigrationSpec
//
//    companion object {
//        val migration3To4 = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE IF NOT EXISTS school (name CHAR NOT NULL PRIMARY KEY)")
//            }
//        }
//    }

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