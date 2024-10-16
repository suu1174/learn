package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [DataCallEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataCallDao(): DataCallDao
}

@Dao
interface DataCallDao {
    // データの取得メソッド
    @Query("SELECT * FROM data_control")
    fun loadAllDataCall(): List<DataCallEntity>

    // データの取得メソッド年月指定
    @Query("SELECT * FROM data_control WHERE dateYear = :keyYear AND dateMonth = :keyMonth")
    fun loadYearMonthDataCall(keyYear: Int, keyMonth: Int): List<DataCallEntity>

    // 挿入メソッド
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDataCall(dataCallEntity: DataCallEntity)

    // 指定されたデータを削除
    @Query("DELETE FROM data_control WHERE id = :id")
    fun dataChooseDelete(id: Int)
}