package com.example.hellodatapersistence8

import androidx.room.*


@Dao
interface ProgrammerDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertProgrammer(programmer: Programmer)

    @Delete
    fun deleteProgrammers(vararg programmer: Programmer)

    @Query("delete from programmers")
    fun deleteAllProgrammers()

    @Query("select * from programmers")
    fun getAllProgrammers(): List<Programmer>

    @Query("select * from programmers where name like :name")
    fun searchProgrammer(name: String): List<Programmer>
}