package com.marmaris.schoolapp.lessons

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marmaris.schoolapp.LiveDataTestUtil
import com.marmaris.schoolapp.capture
import com.marmaris.schoolapp.data.lessons.Lesson
import com.marmaris.schoolapp.data.lessons.LessonsDs
import com.marmaris.schoolapp.data.lessons.LessonsRepo
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.*

class LessonsViewModelImpTest {

    @get:Rule
    var mInstantExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var mLessonsRepo: LessonsRepo
    @Mock
    private lateinit var mContext: Application
    @Captor
    private lateinit var mGetLessonsCallbackCaptor: ArgumentCaptor<LessonsDs.GetLessonsCallback>
    private lateinit var mViewModel: LessonsViewModel
    private lateinit var mLessons: ArrayList<Lesson>

    @Before
    fun setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)
        setupContext()

        mViewModel = LessonsViewModelImp(mLessonsRepo)

        mLessons = ArrayList()
        val lesson1 = Lesson()
        lesson1.title = "Lesson1"
        mLessons.add(lesson1)

        val lesson2 = Lesson()
        lesson2.title = "Lesson2"
        mLessons.add(lesson2)

        val lesson3 = Lesson()
        lesson3.title = "Lesson3"
        mLessons.add(lesson3)

    }

    private fun setupContext() {
        Mockito.`when`<Context>(mContext.applicationContext).thenReturn(mContext)
        //Mockito.`when`(mContext.getString(R.string.successfully_saved_task_message)).thenReturn("EDIT_RESULT_OK")
        Mockito.`when`(mContext.resources).thenReturn(Mockito.mock(Resources::class.java))
    }

    @Test
    fun loadLessons() {
        mViewModel.loadLessons()
        Mockito.verify<LessonsRepo>(mLessonsRepo).getLessons(capture(mGetLessonsCallbackCaptor))
        assertTrue(LiveDataTestUtil.getValue(mViewModel.isLoading()))

        mGetLessonsCallbackCaptor.value.onResponse(mLessons, null)
        assertFalse(LiveDataTestUtil.getValue(mViewModel.isLoading()))

        // And data loaded
        assertFalse(LiveDataTestUtil.getValue(mViewModel.getLessons()).isEmpty())
        assertTrue(LiveDataTestUtil.getValue(mViewModel.getLessons()).size == 3)
    }

}