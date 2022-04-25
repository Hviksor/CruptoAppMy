package com.example.cruptoappmy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cruptoappmy.pojo.CoinInfo
import com.example.cruptoappmy.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): CoinPriceInfoDao


    companion object {
        private var db: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return if (db == null) {
                db = Room.databaseBuilder(context, AppDatabase::class.java, "db").build()
                db as AppDatabase
            } else {
                db as AppDatabase
            }

        }
    }

}