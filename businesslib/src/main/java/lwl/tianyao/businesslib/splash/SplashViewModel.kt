package lwl.tianyao.businesslib.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.ServiceLoader

class SplashViewModel:ViewModel() {
    private val Tag = "SplashViewModel"
    private val composableMap = HashMap<Int, ISplashComposableTask>()
    open val mCurrentSplashTaskIndex = mutableStateOf(0)

    init {

        ServiceLoader.load(ISplashComposableTask::class.java).forEach {
            if (composableMap[it.index] != null) {
                throw DuplicateIndexException(composableMap[it.index]!!)
            }
            composableMap[it.index] = it
        }
    }

    private fun getComposable(index: Int): ISplashComposableTask? {
        return composableMap[index]
    }

    @Composable
    fun executeCurrentTask() {
        if (getComposable(mCurrentSplashTaskIndex.value) != null) {
            Log.d(
                Tag,
                "Execute No.${mCurrentSplashTaskIndex.value} task ${
                    getComposable(mCurrentSplashTaskIndex.value).toString()
                }"
            )
            getComposable(mCurrentSplashTaskIndex.value)!!.SplashComposableItem()
        }
    }

    @Composable
    fun findNextTask() {
        LaunchedEffect(key1 = mCurrentSplashTaskIndex.value, block = {
            Log.d(Tag, "Find out next available task.")
            for (i in (mCurrentSplashTaskIndex.value + 1)..100) {
                Log.d(Tag, "Try task index $i")
                if (getComposable(i) != null) {
                    Log.d(Tag, "Got next available task $i ${getComposable(i).toString()}")
                    mCurrentSplashTaskIndex.value = i
                    break
                }
            }
        })
    }

    fun exitApp() {
        System.exit(0)
    }
}