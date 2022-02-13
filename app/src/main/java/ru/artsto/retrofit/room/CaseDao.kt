package ru.artsto.retrofit.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CaseDao {
    @Insert
    fun insertCase(case: Case): Completable

    @Insert
    fun insertCases(vararg cases: Case): Completable

    @Delete
    fun deleteCase(case: Case):Completable

    @Update
    fun updateCase(case: Case):Completable

    @Query("SELECT * From `case` WHERE id = :id")
    fun getCaseById(id:Long):Single<Case>

    @Query("SELECT * From `case`")
    fun getAllCases():Single<List<Case>>

}

/*
@Dao
interface CaseDao {
    @Insert
    suspend fun insertCase(case: Case):Long

    @Delete
    suspend fun deleteCase(case: Case)

    @Update
    suspend fun updateCase(case: Case)

    @Query("SELECT * From `case` WHERE id = :id")
    suspend fun getCaseById(id: Long):List<Case>
}
*/