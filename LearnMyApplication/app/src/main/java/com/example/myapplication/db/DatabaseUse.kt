package com.example.myapplication.db

import android.app.Application
import androidx.room.Room

class DatabaseUse : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // AppDatabaseをビルドする
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}