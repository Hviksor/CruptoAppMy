package com.example.cruptoappmy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cruptoappmy.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "db"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                db as AppDatabase
            } else {
                db as AppDatabase
            }
        }
    }

}