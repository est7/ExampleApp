package com.template.mviexample
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.template.mviexample.util.SingleLiveEvent
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Rule
import org.junit.Test

/**
 *
 * @author: est7
 * @date: 2022/12/14
 */

class SingleLiveEventTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @Test
    fun `GIVEN single live event, WHEN value not observed, THEN observe value`() {
        // Given
        val expectedValue = 5
        val singleLiveEvent = SingleLiveEvent<Int>().apply { postValue(expectedValue) }

        // When / Then
        assertEquals(expectedValue, singleLiveEvent.blockingObserve())
    }

    @Test
    fun `GIVEN single live event, WHEN value already observed, THEN observe nothing`() {
        // Given
        val expectedValue = 5
        val singleLiveEvent = SingleLiveEvent<Int>().apply { postValue(expectedValue) }
        assertEquals(expectedValue, singleLiveEvent.blockingObserve())

        // When / Then
        assertNull(singleLiveEvent.blockingObserve())
    }

    @Test
    fun `GIVEN single live event, WHEN no values to observe, THEN observe nothing`() {
        // Given
        val singleLiveEvent = SingleLiveEvent<Int>()

        // When / Then
        assertNull(singleLiveEvent.blockingObserve())
    }

    @Test
    fun `GIVEN single live event with value to observe, WHEN calling it, THEN observe nothing`() {
        // Given
        val singleLiveEvent = SingleLiveEvent<Int>().apply { postValue(5) }

        // When
        singleLiveEvent.call()

        // Then
        assertNull(singleLiveEvent.blockingObserve())
    }
}