package com.nordpass.tt.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nordpass.tt.storage.todo.TodoDao
import com.nordpass.tt.storage.todo.TodoEntity

@Database(
    version = 3,
    entities = [TodoEntity::class]
)
internal abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        fun create(context: Context): TodoDatabase =
            Room.databaseBuilder(context, TodoDatabase::class.java, "db")
                .fallbackToDestructiveMigration()
                .addMigrations(Migrations.MIGRATION_1_2)
                .build()
    }

    object Migrations {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE ${TodoEntity.TABLE_NAME} ADD COLUMN ${TodoEntity.CREATED_AT_COLUMN_NAME} TEXT NOT NULL DEFAULT ''"
                )
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE ${TodoEntity.TABLE_NAME} ADD COLUMN ${TodoEntity.UPDATED_AT_COLUMN_NAME} INTEGER DEFAULT NULL"
                )
                database.execSQL(
                    "ALTER TABLE ${TodoEntity.TABLE_NAME} ADD COLUMN ${TodoEntity.USER_ID_COLUMN_NAME} INTEGER DEFAULT NULL"
                )
            }
        }
    }
}