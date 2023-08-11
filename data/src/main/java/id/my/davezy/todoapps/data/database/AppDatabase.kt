package id.my.davezy.todoapps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.my.davezy.todoapps.data.dao.ChecklistDao
import id.my.davezy.todoapps.data.entities.ChecklistEntity

@Database(entities = [ChecklistEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun checklistDao(): ChecklistDao
}