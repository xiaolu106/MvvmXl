package lwl.tianyao.businesslib.news

import java.lang.Exception
import java.util.ServiceLoader

object ServiceLoader {

    fun <S> load(service: Class<S>): S? {
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e: Exception) {
            null
        }
    }
}