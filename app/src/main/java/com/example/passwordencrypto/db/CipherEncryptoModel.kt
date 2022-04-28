package com.example.passwordencrypto.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Encrypted Table")

data class CipherEncryptoModel(
    @ColumnInfo(name = "data") var data: String,
    @ColumnInfo(name = "data") var key: Int,
    @ColumnInfo(name = "technique") var technique: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
