package ch.magdenbt.a1ccaclient

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() = runTest {

        val a = 1..10
        val mf = MutableStateFlow(a.asFlow().toList())

        mf.value.forEach {System.out.println(it)}


        mf.value.forEach {System.out.println(it)}
    }

    @Test
    fun test() {
        val a = 22

        assertEquals(4, 2 + 2)
    }
}