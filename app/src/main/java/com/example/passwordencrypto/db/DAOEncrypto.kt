package com.example.passwordencrypto.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAOEncrypto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDataToDb(cipherEncryptoModel: CipherEncryptoModel)

    @Query("delete from `encrypted table` ")
    fun deleteAllData()

    @Query("select * from `encrypted table` order by id desc ")
    fun getRoutineData(): LiveData<List<CipherEncryptoModel>>

    @Delete
    fun deleteData(cipherEncryptoModel: CipherEncryptoModel)

}