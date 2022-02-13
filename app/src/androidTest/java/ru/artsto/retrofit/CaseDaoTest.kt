package ru.artsto.retrofit

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.artsto.retrofit.room.AppDatabase
import ru.artsto.retrofit.room.Case
import ru.artsto.retrofit.room.CaseDao
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CaseDaoTest {
    private lateinit var caseDao: CaseDao
    private lateinit var db: AppDatabase
    private lateinit var appContext: Context

    @Before
    fun createDb(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room
            .inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        caseDao = db.caseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insetCaseTest(){
        val case1 = TestUtil.createCase()
        caseDao.insertCase(case = case1).test()

        caseDao.getCaseById(case1.id).test().assertValue {case2->
            try {
                Assert.assertEquals("должны быть одинаковыми",case1.name,case2.name)
                true
            }catch (e:Exception){
                false
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeCasesAndReadCasesTest(){
        //создаем 5 дел
        val caseActual = TestUtil.createCases(5)
        //помещаем в хранилище
        caseDao.insertCases(cases = caseActual).test()

        //берем из хранилища и сверяем
        caseDao.getAllCases().test().assertValue {
                list-> TestUtil.arrayEquals(list.toTypedArray(), caseActual)
        }
    }
}

object TestUtil{

    fun arrayEquals(list: Array<Case>, caseActual: Array<Case>): Boolean {
        return try {
            Assert.assertArrayEquals("массивы должные быть одинаковыми",list,caseActual)
            true
        }catch (e:Exception){
            false
        }
    }

    fun createCase(): Case {
        return Case(id = 100L,name = "дело №1")
    }

    fun createCases(count:Int): Array<Case> {
        val list = mutableListOf<Case>()
        (1..count).forEach {
            list.add(Case(id = it.toLong(),name = "дело №$it"))
        }
        return list.toTypedArray()
    }
}