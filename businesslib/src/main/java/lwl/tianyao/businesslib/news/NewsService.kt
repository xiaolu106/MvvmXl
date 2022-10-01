package lwl.tianyao.businesslib.news

import androidx.compose.runtime.Composable

interface NewsService {
    fun getNewsScreen(): @Composable () -> Unit
}