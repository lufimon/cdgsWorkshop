package th.co.cdgs.workshop1.local.data

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "person_db").build()
    }

    abstract fun personDao(): PersonDao
}