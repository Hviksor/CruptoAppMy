package com.example.cruptoappmy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cruptoappmy.data.network.model.CoinInfoDto

@Database(entities = [CoinInfoDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): CoinPriceInfoDao


    companion object {
        private var INSTANCE: AppDatabase? = null
        private var LOCK = Any()
        private const val DB_NAME = "db"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                INSTANCE = db
                return db
            }


        }
    }

}