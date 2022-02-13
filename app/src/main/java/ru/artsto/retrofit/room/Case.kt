package ru.artsto.retrofit.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//дело
@Entity
data class Case(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") //является не обязательным
    val id:Long=0,

    @ColumnInfo(name = "name")
    val name: String
)