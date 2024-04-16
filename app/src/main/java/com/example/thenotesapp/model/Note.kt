package com.example.thenotesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "notes")
@Parcelize//parcelize is used to transfer difficult data like this converts complex data objects into simple data which can be easily transfered
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val noteTitle:String,
    val noteDesc:String
):Parcelable
