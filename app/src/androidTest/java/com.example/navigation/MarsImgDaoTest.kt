package com.example.navigation

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.affirmations.data.database.DbMarsImg
import com.example.affirmations.data.database.MarsImgDao
import com.example.affirmations.data.database.MarsImgDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MarsImgDaoTest {
    private lateinit var marsImgDao: MarsImgDao
    private lateinit var db: MarsImgDatabase

    private val marsImg1 = DbMarsImg(id = "1", imgSrc = "http://example.com/img1.jpg")
    private val marsImg2 = DbMarsImg(id = "2", imgSrc = "http://example.com/img2.jpg")

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, MarsImgDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        marsImgDao = db.MarsImgDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAllItems() = runBlocking {

        marsImgDao.insert(marsImg1)


        marsImgDao.insert(marsImg2)


        val allItems = marsImgDao.getAllItems().first()


        assertEquals(2, allItems.size)
        assertEquals(marsImg1, allItems[0])
        assertEquals(marsImg2, allItems[1])
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetItem() = runBlocking {

        marsImgDao.insert(marsImg1)


        val item = marsImgDao.getItem(marsImg1.id).first()


        assertEquals(marsImg1, item)
    }


}
