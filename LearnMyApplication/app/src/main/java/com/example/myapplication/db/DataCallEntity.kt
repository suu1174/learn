package com.example.myapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_control")
data class DataCallEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "item_date")
    val itemDate: String,

    @ColumnInfo(name = "category")
    val category: Int,

    @ColumnInfo(name = "freeText")
    val freeText: String,

    @ColumnInfo(name = "plus_minus")
    val plusMinus: Int,

    @ColumnInfo(name = "money")
    val money: Int,

    @ColumnInfo(name = "dateYear")
    val dateYear: Int,

    @ColumnInfo(name = "dateMonth")
    val dateMonth: Int,

    @ColumnInfo(name = "dateDay")
    val dateDay: Int,




)