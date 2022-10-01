package lwl.tianyao.businesslib.splash

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.alibaba.android.arouter.launcher.ARouter
import com.google.auto.service.AutoService
import lwl.tianyao.businesslib.news.NewsActivity


@AutoService(ISplashComposableTask::class)
class StartMainActivityComposable : ISplashComposableTask {
    override val content: @Composable (viewModel: SplashViewModel) -> Unit ={
//        LocalContext.current.startActivity(
//            Intent(
//                LocalContext.current,
//                NewsActivity::class.java
//            )
//        )
        ARouter.getInstance().inject(this)
//        ARouter.getInstance().build("/main/MainActivity").navigation()
        ARouter.getInstance().build(ARouterConfig.NEWSACTIVITY).navigation()
//        ARouter.getInstance().build(ARoute).navigation()

    }
    override val index: Int
        get() = 2

}
